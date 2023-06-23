package com.kakaoent.md.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class AuditingConfig {

    @Bean
    fun auditorProvider(): AuditorAware<String> = AuditorAware {
        Optional.of("unknown")
    }
}
