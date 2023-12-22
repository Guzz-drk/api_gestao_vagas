package br.com.guzz.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    
    public JobNotFoundException(String msg){
        super(msg);
    }
}
