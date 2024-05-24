package fontys.ind.controller;

import fontys.ind.business.RatingService;
import fontys.ind.domain.request.rating.CreateRatingRequest;
import fontys.ind.domain.response.rating.CreateRatingResponse;
import fontys.ind.domain.response.rating.GetRatingsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class RatingController {
    private RatingService ratingService;

    @GetMapping("{id}")
    public ResponseEntity<GetRatingsResponse> getRatingsByTrainer(@PathVariable(value = "id") final Integer id) {
        return ResponseEntity.ok(ratingService.getTrainerRatings(id));
    }
    
    @PostMapping()
    public ResponseEntity<CreateRatingResponse> createRating(@RequestBody @Valid CreateRatingRequest request) {
        CreateRatingResponse response = ratingService.createRating(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
