package fontys.ind.business.mappers;

import fontys.ind.domain.response.workout.GetWorkoutInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.persistence.entity.WorkoutEntity;

@Mapper(componentModel = "spring", uses = {TrainerMapper.class, RatingMapper.class})
public interface WorkoutMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(source = "title", target = "title")
    // @Mapping(source = "description", target = "description")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    // @Mapping(source = "trainer", target = "trainer")
    GetWorkoutResponse fromEntityToResponse(WorkoutEntity entity);

//    @Mapping(target = "workoutId", source = "id")
//    @Mapping(source = "title", target = "title")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "startTime", target = "startTime")
//    @Mapping(source = "endTime", target = "endTime")
//    @Mapping(source = "trainer.userId", target = "trainerId")
//    @Mapping(source = "trainer.firstName", target = "trainerFirstName")
//    @Mapping(source = "trainer.lastName", target = "trainerLastName")
//    GetWorkoutInfoResponse fromEntityToInfoResponse(WorkoutEntity entity);
}
