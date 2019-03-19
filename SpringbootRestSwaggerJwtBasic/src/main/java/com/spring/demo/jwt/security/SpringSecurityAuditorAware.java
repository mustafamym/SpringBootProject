package com.spring.demo.jwt.security;

import com.spring.demo.constants.Constants;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;



@EnableJpaAuditing
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public Optional getCurrentAuditor() {
    	return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT));
    }
}