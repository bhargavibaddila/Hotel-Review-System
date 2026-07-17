package com.hotelreview.controller;

import com.hotelreview.model.Hotel;
import com.hotelreview.model.Review;
import com.hotelreview.repository.HotelRepository;
import com.hotelreview.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;

    public ReviewController(ReviewRepository reviewRepository, HotelRepository hotelRepository) {
        this.reviewRepository = reviewRepository;
        this.hotelRepository = hotelRepository;
    }

    // GET all reviews for a hotel
    @GetMapping("/hotels/{hotelId}/reviews")
    public ResponseEntity<List<Review>> getReviewsForHotel(@PathVariable Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewRepository.findByHotel_IdOrderByCreatedAtDesc(hotelId));
    }

    // CREATE a review for a hotel
    @PostMapping("/hotels/{hotelId}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long hotelId, @Valid @RequestBody Review review) {
        return hotelRepository.findById(hotelId)
                .map(hotel -> {
                    review.setHotel(hotel);
                    Review saved = reviewRepository.save(review);
                    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a review
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.ok(Map.of("message", "Review deleted successfully"));
    }

    // GET all reviews (across all hotels) - useful for an admin/dashboard view
    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
