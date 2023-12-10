package br.com.guzz.gestao_vagas.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {
    
    @Bean
    public AuditEventRepository auditEventRepository(){
        return new InMemoryAuditEventRepository();
    }
}
