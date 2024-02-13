package br.com.remotejobs.remote_jobs.modules.candidate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer>{
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);

    List<CandidateEntity> findByUsernameAndIdNotOrEmailAndIdNot(String username, int id1, String email, int id2);
}
