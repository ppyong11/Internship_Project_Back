## 📌 프로젝트 소개
<br>**해당 프로젝트는 https://github.com/jhcode33/react-spring-blog-backend 프로젝트를 기반으로 개선한 프로젝트입니다.**
1. **프로젝트명**: 웹 취약점 분석 및 방어 프로젝트
2. **프로젝트 인원**: 3명 (보안 전공 2명, 개발 1명)
3. **프로젝트 기간**: 2024.06~2024.07 
4. **프로젝트 목표**: 웹 서버 공격 및 방어 환경을 구축하고 웹 취약점 공격을 테스트하며 대응 로직을 구현함으로써 웹 보안 동작 원리와 인증 및 권한 검증 구조에 대한 이해를 높이기 위해 진행한 협업 프로젝트입니다.
5. **사용 기술**
    - **Language:** Java
    - **Framework:** Spring Boot
    - **Database:** MySQL
    - **Infra:** AWS EC2, Ubuntu
    - **Tool:** Burp Suite
## 📌 구현 내용
<img width="642" height="374" alt="image (4)" src="https://github.com/user-attachments/assets/36735d67-e183-4752-b28c-d1dbecfcc370" /><br>
- JWT 기반 인증 구조에서 사용자 권한 검증 로직을 추가해 다른 사용자의 게시글을 수정할 수 있는 취약점을 방어했습니다.
- 로그아웃한 Access Token을 무효화하기 위해 Token Blacklist 테이블을 도입하고, 스케줄러로 만료된 토큰을 주기적으로 정리하도록 구현했습니다.
- AWS EC2 기반으로 프론트엔드와 백엔드를 분리 배포하고, CORS 정책을 적용해 지정된 Origin 요청만 허용하도록 구성했습니다.
## 📌 주요 기능 UI 소개
로그인한 회원만 블로그 기능을 이용할 수 있도록 인증 기반 접근 제어를 적용했습니다.
### 회원가입 및 로그인 기능
  <img width="575" alt="image" src="https://github.com/user-attachments/assets/b5483be3-75ba-4fbb-a831-e4e437c155e7" /><br>
### 게시글 및 댓글 작성과 파일 첨부 기능
<img width="575" alt="image" src="https://github.com/user-attachments/assets/257b33da-f883-4400-9c49-cc20f2d0996e" />

## 📝 프로젝트 상세 내용
- **노션 링크**  
https://www.notion.so/32a516e994b381b29e59e042fa698ea3?source=copy_link
