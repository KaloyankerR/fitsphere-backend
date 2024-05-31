package fontys.ind.business.mappers;

import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.persistence.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface RatingMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "trainer.userId", target = "trainerId")
    @Mapping(source = "client", target = "client")
    GetRatingResponse fromEntityToResponse(RatingEntity entity);
}
