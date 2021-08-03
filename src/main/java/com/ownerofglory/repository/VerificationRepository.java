package com.ownerofglory.repository;

import com.ownerofglory.model.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByToken(String token);
}
