package br.com.guzz.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    
    public CompanyNotFoundException(String msg){
        super(msg);
    }
}
