package jhcode.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CsrfController {
    @GetMapping("/csrf")
    public CsrfToken csrf(HttpServletRequest request) {
        log.info("csrfToken: {}", CsrfToken.class.getName());
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
