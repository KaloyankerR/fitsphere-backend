package fontys.ind.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.persistence.entity.WorkoutEntity;

@Mapper(componentModel = "spring", uses = {TrainerMapper.class})
public interface WorkoutMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(source = "trainer", target = "trainer")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    GetWorkoutResponse fromEntityToResponse(WorkoutEntity entity);

}
