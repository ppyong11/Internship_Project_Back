package jhcode.blog.component;

import jhcode.blog.repository.TokenBlacklistRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class TokenCleanupScheduler {
    private final TokenBlacklistRepository tokenBlacklistRepository;

    public TokenCleanupScheduler(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    @Transactional
    @Scheduled(fixedRate = 60000) // 매 분마다 실행 (예: 1분마다)
    public void scheduleTokenCleanup() {
        LocalDateTime now = LocalDateTime.now();
        tokenBlacklistRepository.deleteByExpiryDateTimeBefore(now);
    }
}
