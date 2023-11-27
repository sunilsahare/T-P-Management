package com.tnpserver.pojo;

import jakarta.persistence.*;
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
    private String title;
    private String description;
    private String requirements;
    private LocalDateTime applicationDeadline;
    private String status;
}
