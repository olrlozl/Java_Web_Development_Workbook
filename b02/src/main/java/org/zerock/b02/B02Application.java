package org.zerock.b02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // (BaseEntity에서)AuditingEntityListener를 활성화 시키기 위함
public class B02Application {

    public static void main(String[] args) {
        SpringApplication.run(B02Application.class, args);
    }

}
