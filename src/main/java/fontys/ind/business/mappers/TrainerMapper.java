package fontys.ind.business.mappers;

import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.persistence.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.TrainerEntity;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class, RatingMapper.class})
public interface TrainerMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "igLink", source = "igLink")
    TrainerEntity fromRequestToEntity(CreateTrainerRequest request);

    @Mapping(target = "id", source = "entity.userId")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "bio", source = "entity.bio")
    @Mapping(target = "igLink", source = "entity.igLink")
    @Mapping(target = "appointmentList", source = "appointments")
    @Mapping(target = "rating", source = "ratings", qualifiedByName = "calculateMedianRating")
    GetTrainerResponse fromEntityToResponse(TrainerEntity entity);

    @Named("calculateMedianRating")
    public static double calculateMedianRating(List<RatingEntity> ratings) {
        List<Integer> sortedRatings = ratings.stream()
                .map(RatingEntity::getRating)
                .sorted()
                .toList();

        int size = sortedRatings.size();
        if (size == 0) {
            // throw new IllegalArgumentException("The list of ratings is empty");
            return 0;
        }

        if (size % 2 == 1) {
            // Odd number of ratings
            return sortedRatings.get(size / 2);
        } else {
            // Even number of ratings
            return (sortedRatings.get(size / 2 - 1) + sortedRatings.get(size / 2)) / 2.0;
        }
    }
}
