package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Application {

    private Long applicationId;
    private JobListing jobListing;
    private Student student;
    private LocalDateTime applicationDate;
    private String status;

}
