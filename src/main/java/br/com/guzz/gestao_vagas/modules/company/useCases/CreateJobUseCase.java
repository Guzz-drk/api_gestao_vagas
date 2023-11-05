package br.com.guzz.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.modules.company.entity.JobEntity;
import br.com.guzz.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
