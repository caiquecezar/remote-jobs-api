package br.com.remotejobs.remote_jobs.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.exceptions.CompanyAlreadyExistsException;
import br.com.remotejobs.remote_jobs.modules.company.CompanyEntity;
import br.com.remotejobs.remote_jobs.modules.company.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository
            .findByUsernameOrEmail(company.getUsername(), company.getEmail())
            .ifPresent((user) -> { 
                throw new CompanyAlreadyExistsException();
            });

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        return this.companyRepository.save(company);
    }
}
