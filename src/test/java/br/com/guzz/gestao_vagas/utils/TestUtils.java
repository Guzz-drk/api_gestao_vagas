package br.com.guzz.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guzz.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.guzz.gestao_vagas.modules.company.entity.CompanyEntity;

public class TestUtils {

    public static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CompanyEntity generateCompanyEntity() {
        return CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("EMAIL@COMPANY.COM")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .build();
    }

    public static String generateTokenCompany(UUID idCompany, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("GestaoVagas")
                .withExpiresAt(expiresIn)
                .withSubject(idCompany.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);
        return token;

    }

    public static CreateJobDTO generateCreateJobDTO() {
        return CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();
    }
}
