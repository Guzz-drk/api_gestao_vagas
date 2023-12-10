package br.com.guzz.gestao_vagas.modules.company.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guzz.gestao_vagas.modules.company.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);

}
