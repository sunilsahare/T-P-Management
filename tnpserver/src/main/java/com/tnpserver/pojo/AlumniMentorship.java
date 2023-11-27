package com.tnpserver.pojo;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlumniMentorship {
    private Alumni mentor;
    private Student student;
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
