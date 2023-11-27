package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partner_opportunities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PartnerOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opportunity_id")
    private Long opportunityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String title;
    private String description;
    private LocalDateTime applicationDeadline;
    private String status;

}
