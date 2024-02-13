package br.com.remotejobs.remote_jobs.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.exceptions.CompanyAlreadyExistsException;
import br.com.remotejobs.remote_jobs.modules.company.CompanyEntity;
import br.com.remotejobs.remote_jobs.modules.company.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository
            .findByUsernameOrEmail(company.getUsername(), company.getEmail())
            .ifPresent((user) -> { 
                throw new CompanyAlreadyExistsException();
            });
        return this.companyRepository.save(company);
    }
}
