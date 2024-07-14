package jhcode.blog.servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@WebServlet("/login")
public class IpServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(IpServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 클라이언트의 공인 IP 주소 가져오기
        String clientIp = getClientIp(request);

        // IP 주소 로그로 출력
        LOGGER.info("Login request from IP: " + clientIp);

        // 로그인 처리 로직 (예: 사용자 인증 등)

        // 로그 출력만 할 경우, 응답 처리는 생략
        // 응답 처리가 필요 없으면 다음 줄을 생략해도 됩니다
        // response.getWriter().write("Login request received from IP: " + clientIp);
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
