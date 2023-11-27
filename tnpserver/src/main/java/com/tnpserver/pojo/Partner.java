package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Partner {

    private Long userId;
    private User user;
    private String organizationName;
    private String industry;
    private String organizationDescription;
    private String organizationLogoUrl;
    private String reviewsAndRatings;
}

