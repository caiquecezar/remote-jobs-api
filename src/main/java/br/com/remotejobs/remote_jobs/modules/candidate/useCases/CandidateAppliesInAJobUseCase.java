package br.com.remotejobs.remote_jobs.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.exceptions.CandidateAlreadyAppliedInJob;
import br.com.remotejobs.remote_jobs.exceptions.CandidateDoesntExistsException;
import br.com.remotejobs.remote_jobs.exceptions.JobDoesntExistsException;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateRepository;
import br.com.remotejobs.remote_jobs.modules.candidate.JobApplicationEntity;
import br.com.remotejobs.remote_jobs.modules.candidate.JobApplicationRepository;
import br.com.remotejobs.remote_jobs.modules.company.JobRepository;

@Service
public class CandidateAppliesInAJobUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;


    public JobApplicationEntity execute(int candidateId, int jobId) {
        var candidate = this.candidateRepository.findById(candidateId);
        if (!candidate.isPresent()) {
            throw new CandidateDoesntExistsException();
        }
        
        var job = this.jobRepository.findById(candidateId);
        if (!job.isPresent()) {
            throw new JobDoesntExistsException();
        }

        var applicationJob = this.jobApplicationRepository.findByCandidateIdAndJobId(candidateId, jobId);
        if (applicationJob.isPresent()) {
            throw new CandidateAlreadyAppliedInJob();
        }
    
        var application = new JobApplicationEntity();
        application.setCandidateId(candidateId);
        application.setJobId(jobId);

        return this.jobApplicationRepository.save(application);
    }
}
