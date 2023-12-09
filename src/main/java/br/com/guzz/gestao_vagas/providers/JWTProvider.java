package br.com.guzz.gestao_vagas.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.guzz.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.guzz.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.guzz.gestao_vagas.modules.company.entity.CompanyEntity;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${security.token.secret.candidate}")
    private String secretKeyCandidate;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var subject = JWT.require(algorithm).build().verify(token).getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String createToken(CompanyEntity company) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("GestaoVagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;
    }

    public String validateTokenCandidate(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);

        try {
            var subject = JWT.require(algorithm).build().verify(token).getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }

        public AuthCandidateResponseDTO createTokenCandidate(CandidateEntity candidate) {
        Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("Javagas")
                .withExpiresAt(expiresIn)
                .withSubject(candidate.getId().toString())
                .withClaim("roles",Arrays.asList("candidate"))
                .sign(algorithm);
        var authCandidateResponseDTO = AuthCandidateResponseDTO.builder().access_token(token).expires_in(expiresIn.toEpochMilli()).build();
        return authCandidateResponseDTO;
    }
}
