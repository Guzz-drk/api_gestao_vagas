package br.com.guzz.gestao_vagas.providers;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.guzz.gestao_vagas.modules.company.entity.CompanyEntity;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

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
}
