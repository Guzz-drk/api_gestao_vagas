package br.com.guzz.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(String msg){
        super(msg);
    }
}
