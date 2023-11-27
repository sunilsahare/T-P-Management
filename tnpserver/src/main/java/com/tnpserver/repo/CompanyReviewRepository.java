package com.tnpserver.repo;

import com.tnpserver.entity.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {
}
