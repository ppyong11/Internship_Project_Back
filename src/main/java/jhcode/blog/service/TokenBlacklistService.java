package jhcode.blog.service;

import jhcode.blog.entity.TokenBlacklist;
import jhcode.blog.repository.TokenBlacklistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    /*@Value("${token.expiration.minutes}") // application.yml에서 설정한 값 가져오기
    private int tokenExpirationMinutes;*/

    public TokenBlacklistService(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    @Transactional
    public void addToBlacklist(String token) {
        if (!tokenBlacklistRepository.existsByToken(token)) {
            LocalDateTime expiryDateTime = LocalDateTime.now().plusMinutes(3); // 예: 60분 후 만료
            TokenBlacklist tokenBlacklist = new TokenBlacklist(token, expiryDateTime);
            tokenBlacklistRepository.save(tokenBlacklist);
        }
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }
}
