package com.tnpserver.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobListing {

    private Long jobId;
    private Employer employer;
    @NotEmpty(message = "Job Title should not be empty")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    @Size(max = 1000, message = "Description should be maximum 1000 characters")
    private String description;
    @NotEmpty(message = "requirements should not be empty")
    @Size(max = 1000, message = "requirements should be maximum 1000 characters")
    private String requirements;
    private LocalDateTime applicationDeadline;
    private String status;
}
