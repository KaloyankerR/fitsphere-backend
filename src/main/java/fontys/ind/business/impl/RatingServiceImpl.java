package fontys.ind.business.impl;

import fontys.ind.business.RatingService;
import fontys.ind.business.mappers.RatingMapper;
import fontys.ind.domain.request.rating.CreateRatingRequest;
import fontys.ind.domain.response.rating.CreateRatingResponse;
import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.domain.response.rating.GetRatingsResponse;
import fontys.ind.persistence.ClientRepository;
import fontys.ind.persistence.RatingRepository;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.RatingEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final RatingMapper ratingMapper;

    @Override
    @Transactional
    public CreateRatingResponse createRating(CreateRatingRequest request) {
        RatingEntity existingRating = ratingRepository.findByTrainerUserIdAndClientUserId(request.getTrainerId(), request.getClientId());

        if (existingRating != null) {
            // Update the existing rating
            existingRating.setRating(request.getRating());
            existingRating.setComment(request.getComment());
        } else {
            Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(Long.valueOf(request.getTrainerId()));
            TrainerEntity trainerEntity = trainerEntityOptional.orElseThrow(() ->
                    new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + " not found."));

            Optional<ClientEntity> clientEntityOptional = clientRepository.findById(Long.valueOf(request.getClientId()));
            ClientEntity clientEntity = clientEntityOptional.orElseThrow(() ->
                    new EntityNotFoundException("Client with ID " + request.getClientId() + " not found."));

            existingRating = RatingEntity.builder()
                    .trainer(trainerEntity)
                    .client(clientEntity)
                    .rating(request.getRating())
                    .comment(request.getComment())
                    .build();
        }

        RatingEntity ratingEntity = ratingRepository.save(existingRating);
        return CreateRatingResponse.builder()
                .id(Math.toIntExact(ratingEntity.getId()))
                .build();
    }

    @Override
    @Transactional
    public GetRatingsResponse getTrainerRatings(Integer id) {
        List<RatingEntity> ratingEntities = ratingRepository.findAllByTrainerUserId(id);

        List<GetRatingResponse> ratings = ratingEntities.stream()
                .map(ratingMapper::fromEntityToResponse)
                .toList();

        return GetRatingsResponse.builder()
                .ratings(ratings)
                .build();
    }
}
