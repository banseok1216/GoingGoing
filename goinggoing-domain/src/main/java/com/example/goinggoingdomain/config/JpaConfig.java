package com.example.goinggoingdomain.config;

import com.example.goinggoingdomain.GoinggoingDomainRoot;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {GoinggoingDomainRoot.class})
@EnableJpaRepositories(basePackageClasses = {GoinggoingDomainRoot.class})
public class JpaConfig {

}