package com.tnpserver.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alumni_mentorship")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlumniMentorship {

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Alumni mentor;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Getters and setters
}
