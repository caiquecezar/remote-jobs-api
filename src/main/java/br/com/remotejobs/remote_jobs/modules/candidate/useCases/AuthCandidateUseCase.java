package br.com.remotejobs.remote_jobs.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.remotejobs.remote_jobs.exceptions.CandidateDoesntExistsException;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateRepository;
import br.com.remotejobs.remote_jobs.modules.candidate.dto.AuthCandidateDto;

@Service
public class AuthCandidateUseCase {

    @Value("${securiry.token.secret}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCandidateDto authCandidateDto) throws AuthenticationException {
        var result = this.candidateRepository.findByUsername(authCandidateDto.getUsername());

        if (!result.isPresent()) {
            throw new CandidateDoesntExistsException();
        }
        var candidate = result.get();
        var passwordIsCorrect = this.passwordEncoder.matches(authCandidateDto.getPassword(), candidate.getPassword());

        if (!passwordIsCorrect) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
            .withIssuer("remotejobs")
            .withSubject(Integer.toString(candidate.getId()))
            .withClaim("roles", Arrays.asList("candidate"))
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .sign(algorithm);
        
        return token;
    }
}
