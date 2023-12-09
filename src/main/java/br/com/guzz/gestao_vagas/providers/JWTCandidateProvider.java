package br.com.guzz.gestao_vagas.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.guzz.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.guzz.gestao_vagas.modules.candidate.entity.CandidateEntity;

@Service
public class JWTCandidateProvider {

    @Value("${security.token.secret.candidate}")
    private String secretKeyCandidate;

    public DecodedJWT validateTokenCandidate(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);

        try {
            var tokenDecoded = JWT.require(algorithm).build().verify(token);
            return tokenDecoded;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }

    }

    public AuthCandidateResponseDTO createTokenCandidate(CandidateEntity candidate) {
        Algorithm algorithm = Algorithm.HMAC256(secretKeyCandidate);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("Javagas")
                .withExpiresAt(expiresIn)
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .sign(algorithm);
        var authCandidateResponseDTO = AuthCandidateResponseDTO.builder().access_token(token)
                .expires_in(expiresIn.toEpochMilli()).build();
        return authCandidateResponseDTO;
    }
}
