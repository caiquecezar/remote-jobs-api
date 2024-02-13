package br.com.remotejobs.remote_jobs.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.exceptions.CandidateAlreadyExistsException;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateEntity;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
            .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
            .ifPresent((user) -> { 
                throw new CandidateAlreadyExistsException();
            });
        return this.candidateRepository.save(candidate);
    }
}
