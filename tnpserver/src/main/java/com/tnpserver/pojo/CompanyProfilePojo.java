package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String companyName;
    private String logoUrl;
    private String industry;
    private String description;
    private String location;
    private String website;
    private int foundedYear;
    private int companySize;
    private String typeOfCompany;
    private String contactInformation;
    private String benefits;
    private String companyCulture;
    private String mission;
    private String vision;
    private String specializations;
    private String awardsOrRecognitions;
    private String newsOrUpdates;

}
