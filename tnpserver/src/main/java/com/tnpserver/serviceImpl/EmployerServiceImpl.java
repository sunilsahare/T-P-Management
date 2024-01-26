package com.tnpserver.serviceImpl;

import com.tnpserver.constants.ErrorCode;
import com.tnpserver.constants.RoleEnum;
import com.tnpserver.entity.Employer;
import com.tnpserver.entity.User;
import com.tnpserver.exception.BusinessException;
import com.tnpserver.helper.EntityPojoMapper;
import com.tnpserver.pojo.JobListing;
import com.tnpserver.repo.EmployerRepository;
import com.tnpserver.repo.JobListingRepository;
import com.tnpserver.service.EmployerService;
import com.tnpserver.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl extends UserServiceImpl implements EmployerService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployerServiceImpl.class);

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobListingRepository jobListingRepository;


    @Transactional
    @Override
    public JobListing addJob(JobListing job) {
        // TODO: remove PojoHelper and UserHelper class and use the EntityPojoMapper

        EntityPojoMapper<com.tnpserver.entity.JobListing, JobListing> employerMapper = jobListingMapper();
        com.tnpserver.entity.JobListing jobListingEntity = employerMapper.mapPojoToEntity(job);

        String currentUserRole = SessionUtil.getCurrentUserRole();

        if (!currentUserRole.equals(RoleEnum.ROLE_EMPLOYER.getRole()) || !currentUserRole.equals(RoleEnum.ROLE_ADMIN.getRole())) {
            new BusinessException(ErrorCode.JOB_DETAILS_NOT_ALLOWED_TO_ADD.getError());
        }

        User loggedInUser = getUserByUsername(SessionUtil.getCurrentUsername());
        Employer employer = getEmployerByUserId(loggedInUser.getUserId());
        jobListingEntity.setEmployer(employer);

        // Add the validation as per requirement
        com.tnpserver.entity.JobListing saved = jobListingRepository.save(jobListingEntity);
        JobListing jobInfoPojo = employerMapper.mapEntityToPojo(saved);
        return jobInfoPojo;
    }

    @Transactional
    @Override
    public JobListing upadateJob(JobListing job) {
        Long jobId = Optional.of(job.getJobId()).orElseThrow(() -> new BusinessException("Job Id Should not be empty"));

        com.tnpserver.entity.JobListing existingJob = jobListingRepository.findById(jobId).orElseThrow(() -> new BusinessException("Job not found."));
        String currentUserRole = SessionUtil.getCurrentUserRole();

        if (!currentUserRole.equals(RoleEnum.ROLE_EMPLOYER.getRole()) && !currentUserRole.equals(RoleEnum.ROLE_ADMIN.getRole())) {
            new BusinessException(ErrorCode.JOB_DETAILS_NOT_ALLOWED_TO_ADD.getError());
        }

        /**
         * Job Title, JobId and Employer Not allowed to update by anyone
         * Status can be updated by only Admin
         */

        existingJob.setDescription(job.getDescription());
        existingJob.setApplicationDeadline(job.getApplicationDeadline());
        existingJob.setRequirements(job.getRequirements());

        // Add the validation as per requirement
        com.tnpserver.entity.JobListing saved = jobListingRepository.save(existingJob);
        LOG.info("Job Details Successfully updated - {}",saved);
        return jobListingMapper().mapEntityToPojo(saved);
    }

    @Override
    public void removeJob(Long jobId) {
        JobListing job = getJob(jobId);
        jobListingRepository.deleteById(job.getJobId());
        LOG.info("Job info deleted - {}",job.getJobId());
    }

    @Override
    public JobListing getJob(Long jobId) {
        com.tnpserver.entity.JobListing jobInfoEntity = jobListingRepository.findById(jobId).orElseThrow(() -> new BusinessException("Job not found."));
        JobListing jobListingPojo = jobListingMapper().mapEntityToPojo(jobInfoEntity);
        return jobListingPojo;
    }

    @Override
    public List<JobListing> getAllJobByUser() {
        User loggedInUser = getLoggedInUser();
        LOG.info("Fetching Job listing for user - {}", loggedInUser.getUsername());
        Employer employer = getEmployerByUserId(loggedInUser.getUserId());

        String role = loggedInUser.getRole().name();
        isUserAllowedToGetJobDetails(employer);

        List<com.tnpserver.entity.JobListing> jobList = new ArrayList<>();

        if (role.equals(RoleEnum.ROLE_EMPLOYER.name())) {
            // only Employer's job will be return
            jobList = jobListingRepository.findJobListingByEmployerId(employer.getEmployerId());
        } else {
            // This is only for the Admin
            jobList = jobListingRepository.findAll();
        }

        EntityPojoMapper<com.tnpserver.entity.JobListing, JobListing> jobListingMapper = jobListingMapper();

        List<JobListing> jobListPojo = jobList.stream()
                .map(job -> {
                    JobListing jobListing = jobListingMapper().mapEntityToPojo(job);
                    com.tnpserver.pojo.Employer employerPojo = employerMapper().mapEntityToPojo(job.getEmployer());
                    jobListing.setEmployer(employerPojo);
                    return jobListing;
                })
                .collect(Collectors.toList());


        LOG.info("Job list - {}", jobListPojo);

        return jobListPojo;
    }

    @Override
    public Employer getEmployerById(Long employerId) {
        return employerRepository.findById(employerId).orElseThrow(() -> new BusinessException("Invalid Employer Id"));
    }

    @Override
    public Employer getEmployerByUserId(Long userId) {
        return employerRepository.findByUserId(userId).orElseThrow(() -> new BusinessException("Invalid Employer Id"));
    }

    @Override
    public com.tnpserver.pojo.Employer updateEmployer(com.tnpserver.pojo.Employer employer, Long userId) {
        isUserActive(SessionUtil.getCurrentUsername());
        // only self update is allowed
        User loggedInUser = getUserByUsername(SessionUtil.getCurrentUsername());

        if (userId == null) {
            throw new BusinessException("UserId is required to update Employer details.");
        }

        boolean isUpdateAllowed = loggedInUser!=null && userId!=null && userId == loggedInUser.getUserId();

        if (!isUpdateAllowed) {
            throw new BusinessException("We not allowed to update other Employer details.");
        }

        Employer existingEmployer = getEmployerByUserId(userId);
        existingEmployer.setIndustry(employer.getIndustry());
        existingEmployer.setCompanyName(employer.getCompanyName());
        existingEmployer.setCompanyDescription(employer.getCompanyDescription());
        existingEmployer.setReviewsAndRatings(employer.getReviewsAndRatings());
        existingEmployer.setCompanyLogoUrl(employer.getCompanyLogoUrl());

        Employer saved = employerRepository.save(existingEmployer);
        LOG.info("Employer Details updated - {}", saved);
        return employerMapper().mapEntityToPojo(saved);
    }

    public void isUserAllowedToGetJobDetails(Employer employer){
        User loggedInUser = getLoggedInUser();
        String role = loggedInUser.getRole().name();

        if (!Objects.equals(role, RoleEnum.ROLE_ADMIN.name()) && !Objects.equals(role, RoleEnum.ROLE_EMPLOYER.name())) {
            LOG.info("Only Admin or Employer is allowed to fetch Job Details - {}", role);
            throw new BusinessException("Only Admin or Employer is allowed to fetch Job Details.");
        }

        if (employer == null) {
            throw new BusinessException("Employer information Not found associated to the user id - "+loggedInUser.getUserId());
        }

    }

    public User getLoggedInUser() {
        String loggedInUsername = SessionUtil.getCurrentUsername();
        User loggedInUser = getUserByUsername(loggedInUsername);

        if (loggedInUser == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND.getError());
        }

        return loggedInUser;
    }

    private EntityPojoMapper<com.tnpserver.entity.JobListing, JobListing> jobListingMapper() {
        return  new EntityPojoMapper<>(com.tnpserver.entity.JobListing.class, JobListing.class);
    }

    private EntityPojoMapper<com.tnpserver.entity.Employer, com.tnpserver.pojo.Employer> employerMapper() {
        return  new EntityPojoMapper<>(com.tnpserver.entity.Employer.class, com.tnpserver.pojo.Employer.class);
    }

}
