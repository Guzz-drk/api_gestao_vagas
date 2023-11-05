package br.com.guzz.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCompanyDTO {
    
    private String password;

    private String username;
}
