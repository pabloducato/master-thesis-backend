package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.prz.master.thesis.backend.entity.ResetPasswordEntity;

public interface PasswordResetTokenRepository extends JpaRepository<ResetPasswordEntity, Long> {
    ResetPasswordEntity findByToken(String token);

    ResetPasswordEntity findByUserId(Long userId);

    boolean existsByUserId(Long id);
}
