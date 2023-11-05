package br.com.guzz.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDTO {
    
    private String message;

    private String field;
}
