package com.abans.JobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping
    public ResponseEntity<String> postReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean added = reviewService.addReview(companyId, review);
        if(added) return new ResponseEntity<>("Review Posted Successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id){
        Review review = reviewService.getReviewById(id, companyId);
        if(review != null) return ResponseEntity.ok(review);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Review updatedReview){
        boolean updated = reviewService.updateReview(companyId, id, updatedReview);
        if(updated) return ResponseEntity.ok("Review updated successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id){
        boolean deleted = reviewService.deleteReview(companyId, id);
        if(deleted) return ResponseEntity.ok("Review deleted successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
