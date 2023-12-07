package com.tnpserver.pojo;

import com.tnpserver.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyProfilePojo {

    @Positive(message = "UserId should be greater than zero")
    private Long userId;
    @Positive(message = "CompanyProfileId should be greater than zero")
    private Long companyProfileId;
    private com.tnpserver.pojo.User user;

    @NotEmpty(message = "Company Name should not be Empty")
    @Size(max = 64, message = "Company Name should be maximum 64 characters")
    private String companyName;

    private String logoUrl;

    @NotEmpty(message = "Industry should not be Empty")
    @Size(max = 64, message = "Industry should be maximum 64 characters")
    private String industry;

    @NotEmpty(message = "Description should not be Empty")
    @Size(max = 1024, message = "Description should be maximum 1024 characters")
    private String description;

    @NotEmpty(message = "Location should not be Empty")
    @Size(max = 1024, message = "Location should be maximum 1024 characters")
    private String location;
    @NotEmpty(message = "Description should not be Empty")
    @Size(max = 64, message = "website should be maximum 64 characters")
    private String website;
    @Positive(message = "founded Year should be positive number")
    private int foundedYear;
    @Positive(message = "Company Size Year should be positive number")
    private int companySize;
    @Size(max = 1024, message = "Type Of Company should be maximum 1024 characters")
    private String typeOfCompany;
    @NotEmpty(message = "Contact Information should not be Empty")
    @Size(max = 255, message = "Contact Information should be maximum 255 characters")
    private String contactInformation;
    @Size(max = 1024, message = "Benefits should be maximum 1024 characters")
    private String benefits;
    @Size(max = 1024, message = "Company Culture should be maximum 1024 characters")
    private String companyCulture;
    @Size(max = 1024, message = "Mission should be maximum 1024 characters")
    private String mission;
    @Size(max = 1024, message = "Vision should be maximum 1024 characters")
    private String vision;
    @Size(max = 1024, message = "Specializations should be maximum 1024 characters")
    private String specializations;
    @Size(max = 1024, message = "AwardsOrRecognitions should be maximum 1024 characters")
    private String awardsOrRecognitions;
    @Size(max = 1024, message = "NewsOrUpdates should be maximum 1024 characters")
    private String newsOrUpdates;

}
