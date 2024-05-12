package fontys.ind.business;

import fontys.ind.domain.request.rating.CreateRatingRequest;
import fontys.ind.domain.response.rating.CreateRatingResponse;
import fontys.ind.domain.response.rating.GetRatingsResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;

public interface RatingService {
    CreateRatingResponse createRating(CreateRatingRequest request);
    GetRatingsResponse getTrainerRatings(Integer id);
}
