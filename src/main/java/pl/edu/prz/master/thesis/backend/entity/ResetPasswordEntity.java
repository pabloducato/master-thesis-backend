package pl.edu.prz.master.thesis.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PasswordResetToken")
@Data
@EqualsAndHashCode(exclude = {"id", "expiryDate"})
@ToString
@AllArgsConstructor
@Builder
public class ResetPasswordEntity implements Serializable {
    @Transient
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", insertable = false, updatable = false, unique = true)
    private User user;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    public void PasswordResetToken(Long userId) {
        this.userId = userId;
        this.expiryDate = calculateExpiryDate();
    }

    public boolean isValid() {
        return getExpiryDate().isAfter(LocalDateTime.now());
    }

    public static LocalDateTime calculateExpiryDate() {
        return LocalDateTime.now().plusMinutes(EXPIRATION);
    }
}
