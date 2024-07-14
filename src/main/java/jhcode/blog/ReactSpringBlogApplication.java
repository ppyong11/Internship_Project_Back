package jhcode.blog;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.TimeZone;

@EnableJpaAuditing //BaseTimeEntity
@SpringBootApplication
@EnableScheduling
public class ReactSpringBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringBlogApplication.class, args);
    }
    @PostConstruct
    public void init() { //TIME SETIING
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println("Spring boot timezone :" + new Date());   // it will print system time in Seoul timezone
    }
}
