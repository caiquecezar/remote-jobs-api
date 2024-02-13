package br.com.remotejobs.remote_jobs.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.remotejobs.remote_jobs.exceptions.CompanyNotFoundException;
import br.com.remotejobs.remote_jobs.modules.company.CompanyRepository;
import br.com.remotejobs.remote_jobs.modules.company.dto.AuthCompanyDto;

@Service
public class AuthCompanyUseCase {

    @Value("${securiry.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var result = this.companyRepository.findByUsername(authCompanyDto.getUsername());

        if (!result.isPresent()) {
            throw new CompanyNotFoundException();
        }
        var company = result.get();
        var passwordIsCorrect = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if (!passwordIsCorrect) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
            .withIssuer("remotejobs")
            .withSubject(Integer.toString(company.getId()))
            .sign(algorithm);
        
        return token;
    }
}
