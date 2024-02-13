package br.com.remotejobs.remote_jobs.modules.company.useCases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.modules.company.JobEntity;
import br.com.remotejobs.remote_jobs.modules.company.JobRepository;

@Service
public class FindJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String searchTerm) {
        var results = this.jobRepository.findByTitleContaining(searchTerm);

        if (!results.isPresent()) {
            return new ArrayList<>();
        } 

        return results.get(); 
    }

}
