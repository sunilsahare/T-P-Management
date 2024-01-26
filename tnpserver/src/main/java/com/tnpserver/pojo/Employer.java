package com.tnpserver.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employer {

    private Long employerId;
    private User user;
    @NotEmpty(message = "Company Name can not be empty")
    private String companyName;
    private String industry;
    @NotEmpty(message = "Company Name can not be empty")
    @Size(min = 10, max = 1000, message = "Company Description should be between 10 to 1000 characters")
    private String companyDescription;
    private String companyLogoUrl;
    private String reviewsAndRatings;
}

