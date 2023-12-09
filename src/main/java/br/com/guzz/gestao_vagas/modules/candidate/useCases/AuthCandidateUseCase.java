package br.com.guzz.gestao_vagas.modules.candidate.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.guzz.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.guzz.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.guzz.gestao_vagas.providers.JWTCandidateProvider;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/Password incorrect!");
        });
        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        var authCandidateResponse = jwtCandidateProvider.createTokenCandidate(candidate);

        return authCandidateResponse;
    }
}
