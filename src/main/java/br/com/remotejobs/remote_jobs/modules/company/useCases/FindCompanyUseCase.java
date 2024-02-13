package br.com.remotejobs.remote_jobs.modules.company.useCases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.modules.company.CompanyEntity;
import br.com.remotejobs.remote_jobs.modules.company.CompanyRepository;

@Service
public class FindCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyEntity> execute(String searchTerm) {
        var results = this.companyRepository.findByNameContaining(searchTerm);
        
        if (results.isEmpty()) {
            return new ArrayList<>();
        } 

        return results; 
    }
}
