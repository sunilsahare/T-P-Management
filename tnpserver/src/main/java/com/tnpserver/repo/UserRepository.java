package com.tnpserver.repo;

import com.tnpserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByIsActive(boolean isActive,Pageable pageable);
    Page<User> findAllByRoleAndIsActive(String role, boolean isActive ,Pageable pageable);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
