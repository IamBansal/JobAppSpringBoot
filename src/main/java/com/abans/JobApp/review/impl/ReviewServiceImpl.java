package com.abans.JobApp.review.impl;

import com.abans.JobApp.company.Company;
import com.abans.JobApp.company.CompanyService;
import com.abans.JobApp.review.Review;
import com.abans.JobApp.review.ReviewRepository;
import com.abans.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long id, Long companyId) {
        return reviewRepository.findByCompanyId(companyId).stream().filter(review -> review.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long id, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null){
            Review review = getReviewById(id, companyId);
            if (review != null) {
                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                reviewRepository.save(review);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long id) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(id)){
            Review review = getReviewById(id, companyId);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId, company);
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
