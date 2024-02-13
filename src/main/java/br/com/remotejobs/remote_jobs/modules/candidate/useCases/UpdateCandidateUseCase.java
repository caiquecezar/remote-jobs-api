package br.com.remotejobs.remote_jobs.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.exceptions.CandidateAlreadyExistsException;
import br.com.remotejobs.remote_jobs.exceptions.CandidateDoesntExistsException;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateEntity;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateRepository;

@Service
public class UpdateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(int id, CandidateEntity candidate) {
        var existsToUpdate = this.candidateRepository.findById(id);
        
        if (!existsToUpdate.isPresent()) {
            throw new CandidateDoesntExistsException();
        }

        var candidateUpdated = existsToUpdate.get();
        if (candidate.getCurriculum() != null) {
            candidateUpdated.setCurriculum(candidate.getCurriculum());
        }
        if (candidate.getCreatedAt() != null) {
            candidateUpdated.setCreatedAt(candidate.getCreatedAt());
        }
        if (candidate.getDescription() != null) {
            candidateUpdated.setDescription(candidate.getDescription());
        }
        if (candidate.getEmail() != null) {
            candidateUpdated.setEmail(candidate.getEmail());
        }
        if (candidate.getName() != null) {
            candidateUpdated.setName(candidate.getName());
        }
        if (candidate.getPassword() != null) {
            candidateUpdated.setPassword(candidate.getPassword());
        }
        if (candidate.getUsername() != null) {
            candidateUpdated.setUsername(candidate.getUsername());
        }

        var existsEmailorUsername = this.candidateRepository
            .findByUsernameAndIdNotOrEmailAndIdNot(candidateUpdated.getUsername(), candidateUpdated.getId(), candidateUpdated.getEmail(), candidateUpdated.getId());

        if (!existsEmailorUsername.isEmpty()) {
            throw new CandidateAlreadyExistsException();
        }

        return this.candidateRepository.save(candidateUpdated);
    }
}
