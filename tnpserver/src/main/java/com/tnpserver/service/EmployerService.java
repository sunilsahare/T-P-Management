package com.tnpserver.service;

import com.tnpserver.entity.Employer;
import com.tnpserver.pojo.JobListing;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployerService {

    public JobListing addJob(JobListing job);

    public JobListing upadateJob(JobListing job);

    public void removeJob(Long jobId);
    public JobListing getJob(Long jobId);
    public List<JobListing> getAllJobByUser();

    Employer getEmployerById(Long employerId);

    Employer getEmployerByUserId(Long userId);

    com.tnpserver.pojo.Employer updateEmployer(com.tnpserver.pojo.Employer employer, Long userId);
}
