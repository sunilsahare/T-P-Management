package com.tnpserver.repo;

import com.tnpserver.entity.AlumniMentorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniMentorshipRepo extends JpaRepository<AlumniMentorship, Long> {
}
