package com.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.poly.repository") // Đảm bảo đúng
@EntityScan(basePackages = "com.poly.entity")
public class Nhom4Java6AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(Nhom4Java6AssignmentApplication.class, args);
	}

}
