package com.tnpserver.repo;

import com.tnpserver.entity.Employer;
import com.tnpserver.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {


    @Query("SELECT e FROM JobListing e WHERE e.employer.id = :empId")
    List<JobListing> findJobListingByEmployerId(@Param("empId") Long empId);

}
