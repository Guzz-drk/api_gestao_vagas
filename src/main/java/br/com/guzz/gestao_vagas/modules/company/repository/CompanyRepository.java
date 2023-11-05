package br.com.guzz.gestao_vagas.modules.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guzz.gestao_vagas.modules.company.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

    Optional<CompanyEntity> findByUsername(String username);
}
