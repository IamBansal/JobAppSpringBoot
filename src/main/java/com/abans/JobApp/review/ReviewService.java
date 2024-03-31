package com.abans.JobApp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long id, Long companyId);
    boolean updateReview(Long companyId, Long id, Review updatedReview);
    boolean deleteReview(Long companyId, Long id);
}
