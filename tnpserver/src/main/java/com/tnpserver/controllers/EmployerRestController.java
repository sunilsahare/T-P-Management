package com.tnpserver.controllers;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.exception.HttpApiResponse;
import com.tnpserver.pojo.Employer;
import com.tnpserver.pojo.JobListing;
import com.tnpserver.pojo.Student;
import com.tnpserver.service.EmployerService;
import com.tnpserver.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class EmployerRestController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployerRestController.class);

    @Autowired
    private EmployerService employerService;

    @PostMapping("v1/job/")
    public ResponseEntity<HttpApiResponse> addJobDetails(@RequestBody @Valid JobListing jobDetails) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("Job info Successfully Added.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",employerService.addJob(jobDetails)))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("v1/job/")
    public ResponseEntity<HttpApiResponse> updateJobDetails(@RequestBody @Valid JobListing jobDetails) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("Job info Successfully updated.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",employerService.upadateJob(jobDetails)))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("v1/job/{jobId}")
    public ResponseEntity<JobListing> getJob(@PathVariable("jobId") Long jobId) throws BusinessException {
        return ResponseEntity.ok(employerService.getJob(jobId));
    }

    @GetMapping("v1/job/")
    public ResponseEntity<List<JobListing>> getAllJobByUser() throws BusinessException {
        return ResponseEntity.ok(employerService.getAllJobByUser());
    }

    @PostMapping("v1/employer/{userId}")
    public ResponseEntity<HttpApiResponse> updateEmployer(@RequestBody @Valid Employer employer, @Valid @NotNull @NotEmpty @PathVariable("userId") Long userId) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("Employer information Successfully updated.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",employerService.updateEmployer(employer, userId)))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("v1/job/{jobId}")
    public ResponseEntity<HttpApiResponse> removeJob(@PathVariable("jobId") Long jobId) throws BusinessException {
        employerService.removeJob(jobId);
        HttpApiResponse response = HttpApiResponse.builder()
                .message("Job info Successfully Deleted.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


}
