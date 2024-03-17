package com.example.goinggoing;

import com.example.goinggoingdomain.GoinggoingDomainRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackageClasses = {
        GoingGoingApplication.class,
        GoinggoingDomainRoot.class})
public class GoingGoingApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoingGoingApplication.class, args);
    }
}
