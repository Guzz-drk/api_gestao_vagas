package br.com.guzz.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.guzz.gestao_vagas.exceptions.UserNotFoundException;
import br.com.guzz.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.guzz.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.guzz.gestao_vagas.providers.JWTProvider;

@Service
public class AuthCompanyUseCase {
    
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        var token = jwtProvider.createToken(company);

        return token;
    }
}
