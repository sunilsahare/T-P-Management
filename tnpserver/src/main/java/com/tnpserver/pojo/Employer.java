package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employer {

    private Long userId;
    private User user;
    private String companyName;
    private String industry;
    private String companyDescription;
    private String companyLogoUrl;
    private String reviewsAndRatings;
}

