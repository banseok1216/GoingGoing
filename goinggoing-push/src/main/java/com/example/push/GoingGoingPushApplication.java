package com.example.push;

import com.example.goinggoingdomain.GoinggoingDomainRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackageClasses = {
        GoingGoingPushApplication.class,
        GoinggoingDomainRoot.class})
@EnableScheduling
public class GoingGoingPushApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoingGoingPushApplication.class, args);
    }
}