package br.com.remotejobs.remote_jobs.modules.candidate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Integer>{
    Optional<JobApplicationEntity> findByCandidateIdAndJobId(int candidateId, int jobId);
}
