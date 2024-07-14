package jhcode.blog.repository;

import jakarta.transaction.Transactional;
import jhcode.blog.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.Transient;
import java.time.LocalDateTime;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {

    void deleteByExpiryDateTimeBefore(LocalDateTime expiryDateTime);

    boolean existsByToken(String token);
}
