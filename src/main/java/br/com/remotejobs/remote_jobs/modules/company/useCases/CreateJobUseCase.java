package br.com.remotejobs.remote_jobs.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.modules.company.JobEntity;
import br.com.remotejobs.remote_jobs.modules.company.JobRepository;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job) {
        return this.jobRepository.save(job);
    }
}
