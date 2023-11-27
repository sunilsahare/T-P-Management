package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PartnerOpportunity {

    private Long opportunityId;
    private Partner partner;
    private String title;
    private String description;
    private LocalDateTime applicationDeadline;
    private String status;

}
