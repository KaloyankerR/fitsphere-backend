package s3.ind.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import s3.ind.domain.response.workout.GetWorkoutResponse;
import s3.ind.persistence.entity.WorkoutEntity;

@Mapper(componentModel = "spring", uses = {TrainerMapper.class})
public interface WorkoutMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(source = "trainer", target = "trainer")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    GetWorkoutResponse fromEntityToResponse(WorkoutEntity entity);

}
