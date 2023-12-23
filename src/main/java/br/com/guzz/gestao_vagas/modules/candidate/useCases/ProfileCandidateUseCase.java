package br.com.guzz.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.exceptions.UserNotFoundException;
import br.com.guzz.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.guzz.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate){
       var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
        throw new UserNotFoundException("User not found!");
       });

       var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .id(candidate.getId()).build();
        
       return candidateDTO;
    }
}
