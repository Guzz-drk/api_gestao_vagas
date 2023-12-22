package br.com.guzz.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.exceptions.JobNotFoundException;
import br.com.guzz.gestao_vagas.exceptions.UserNotFoundException;
import br.com.guzz.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.guzz.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.guzz.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.guzz.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class ApplyJobCandidateUserCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException("User not found");
        });

        this.jobRepository.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundException("Job not found");
        });

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();
        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
