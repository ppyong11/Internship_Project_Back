package jhcode.blog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TokenBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;
    private LocalDateTime expiryDateTime; // 토큰 만료 시간

    public TokenBlacklist(String token, LocalDateTime expiryDateTime) {
        this.token = token;
        this.expiryDateTime = expiryDateTime;
    }
}
