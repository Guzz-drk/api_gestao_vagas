package br.com.guzz.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;

import br.com.guzz.gestao_vagas.exceptions.JobNotFoundException;
import br.com.guzz.gestao_vagas.exceptions.UserNotFoundException;
import br.com.guzz.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.guzz.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.guzz.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.guzz.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.guzz.gestao_vagas.modules.company.entity.JobEntity;
import br.com.guzz.gestao_vagas.modules.company.repository.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUserCaseTest {

    @InjectMocks
    private ApplyJobCandidateUserCase applyJobCandidateUserCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should Not Be Able To Apply Job With Candidate Not Found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            applyJobCandidateUserCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should Not Be Able To Apply Job With Job Not Found")
    public void shouldNotBeAbleToApplyJobWithJobNotFound(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUserCase.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should Be Able To Create A New Apply Job")
    public void shouldBeAbleToCreateANewApplyJob(){
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
    
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUserCase.execute(idCandidate, idJob);

        Assertions.assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
