package com.tnpserver.controllers;

import com.tnpserver.exception.BusinessException;
import com.tnpserver.exception.HttpApiResponse;
import com.tnpserver.pojo.Student;
import com.tnpserver.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class StudentRestController {

    private static final Logger LOG = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping("v1/student/")
    public ResponseEntity<HttpApiResponse> addAcademicInfo(@RequestBody @Valid Student student) throws BusinessException {
        HttpApiResponse response = HttpApiResponse.builder()
                .message("Academic info Successfully Added.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .mapList(Map.of("responseData",studentService.addOrUpdateAcademicInfo(student)))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("v1/student/{academicInfoId}")
    public ResponseEntity<Student> getAcademicInfo(@NotEmpty @PathVariable("academicInfoId") Long academicInfoId) throws BusinessException {
        return ResponseEntity.ok(studentService.getAcademicInfo(academicInfoId));
    }

    @DeleteMapping("v1/student/{academicInfoId}")
    public ResponseEntity<HttpApiResponse> deleteAcademicInfo(@NotEmpty @RequestBody Long academicInfoId) throws BusinessException {
        studentService.removeAcademicInfo(academicInfoId);
        HttpApiResponse apiResponse = HttpApiResponse.builder()
                .message("Academic Info Successfully Deleted.")
                .reason("SUCCESS")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("v1/student/resume/{userId}")
    public ResponseEntity<HttpApiResponse> uploadResumeOrCoverLetter(
            @PathVariable("userId") @Positive(message = "UserId must be positive number") Long userId ,
            @RequestParam MultipartFile resumeOrCoverLetter) throws BusinessException {
        studentService.uploadResumeOrCoverLetter(userId, resumeOrCoverLetter);
        HttpApiResponse response = HttpApiResponse.builder()
                .message(resumeOrCoverLetter.getOriginalFilename()+" File Successfully Added.")
                .status(HttpStatus.OK.name())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

}
