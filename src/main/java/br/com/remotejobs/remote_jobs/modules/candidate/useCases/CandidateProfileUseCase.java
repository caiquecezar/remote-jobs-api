package br.com.remotejobs.remote_jobs.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.remotejobs.remote_jobs.modules.candidate.CandidateEntity;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateRepository;
import br.com.remotejobs.remote_jobs.modules.candidate.dto.CandidateProfileDto;

@Service
public class CandidateProfileUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateProfileDto execute(int candidateId) throws Exception {
        var result = this.candidateRepository
                .findById(candidateId);

        if (!result.isPresent()) {
            throw new Exception("Usuário não encontrado.");
        }

        CandidateEntity candidateEntity = result.get();

        var candidate = CandidateProfileDto.builder()
                .curriculum(candidateEntity.getCurriculum())
                .description(candidateEntity.getDescription())
                .email(candidateEntity.getEmail())
                .name(candidateEntity.getName())
                .username(candidateEntity.getUsername())
                .id(candidateEntity.getId())
                .build();

        return candidate;
    }
}
