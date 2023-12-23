package br.com.guzz.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.guzz.gestao_vagas.modules.company.entity.JobEntity;
import br.com.guzz.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.guzz.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity){
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException("Company not found");
        });
        return this.jobRepository.save(jobEntity);
    }
}
