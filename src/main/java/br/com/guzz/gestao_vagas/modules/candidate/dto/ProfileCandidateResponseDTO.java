package br.com.guzz.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "Desenvolvedor Java Júnior")
    private String description;

    @Schema(example = "guzz-drk")
    private String username;

    @Schema(example = "gwprkg@gmail.com")
    private String email;

    private UUID id;

    @Schema(example = "Gustavo")
    private String name;
}
