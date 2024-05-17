package fontys.ind.business.impl;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRating_existingRating_updatesRating() {
        CreateRatingRequest request = new CreateRatingRequest(4, "Great!", 5, 3);
        RatingEntity existingRating = new RatingEntity();
        existingRating.setId(1L);

        when(ratingRepository.findByTrainerUserIdAndClientUserId(request.getTrainerId(), request.getClientId())).thenReturn(existingRating);
        when(ratingRepository.save(any(RatingEntity.class))).thenReturn(existingRating);

        CreateRatingResponse response = ratingService.createRating(request);

        assertNotNull(response);
        verify(ratingRepository).save(existingRating);
        assertEquals(request.getRating(), existingRating.getRating());
        assertEquals(request.getComment(), existingRating.getComment());
    }

    @Test
    void createRating_newRating_savesRating() {
        CreateRatingRequest request = new CreateRatingRequest(4, "Great!", 5, 3);
        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setUserId(5);
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUserId(3);

        when(ratingRepository.findByTrainerUserIdAndClientUserId(request.getTrainerId(), request.getClientId())).thenReturn(null);
        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainerEntity));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.of(clientEntity));
        when(ratingRepository.save(any(RatingEntity.class))).thenReturn(new RatingEntity(1L, 4, "Great!", trainerEntity, clientEntity));

        CreateRatingResponse response = ratingService.createRating(request);

        assertNotNull(response);
        verify(ratingRepository).save(any(RatingEntity.class));
    }

    @Test
    void createRating_trainerNotFound_throwsException() {
        CreateRatingRequest request = new CreateRatingRequest(3, "Mediocre", 5, 3);
        when(ratingRepository.findByTrainerUserIdAndClientUserId(request.getTrainerId(), request.getClientId())).thenReturn(null);
        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ratingService.createRating(request));
    }

    @Test
    void createRating_clientNotFound_throwsException() {
        CreateRatingRequest request = new CreateRatingRequest(1, "Poor", 5, 3);
        TrainerEntity trainerEntity = new TrainerEntity();
        when(ratingRepository.findByTrainerUserIdAndClientUserId(request.getTrainerId(), request.getClientId())).thenReturn(null);
        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainerEntity));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ratingService.createRating(request));
    }

    @Test
    void getTrainerRatings_returnsRatings() {
        int trainerId = 1;
        RatingEntity ratingEntity = new RatingEntity();
        GetRatingResponse getRatingResponse = new GetRatingResponse();
        when(ratingRepository.findAllByTrainerUserId(trainerId)).thenReturn(List.of(ratingEntity));
        when(ratingMapper.fromEntityToResponse(ratingEntity)).thenReturn(getRatingResponse);

        GetRatingsResponse response = ratingService.getTrainerRatings(trainerId);

        assertNotNull(response);
        assertEquals(1, response.getRatings().size());
        assertEquals(getRatingResponse, response.getRatings().get(0));
    }

    @Test
    void getTrainerRatings_noRatings_returnsEmpty() {
        int trainerId = 1;
        when(ratingRepository.findAllByTrainerUserId(trainerId)).thenReturn(List.of());

        GetRatingsResponse response = ratingService.getTrainerRatings(trainerId);

        assertNotNull(response);
        assertTrue(response.getRatings().isEmpty());
    }
}
