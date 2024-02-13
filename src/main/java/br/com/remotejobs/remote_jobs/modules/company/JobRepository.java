package br.com.remotejobs.remote_jobs.modules.company;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Integer>{
    Optional<List<JobEntity>> findByTitleContaining(String searchTerm);
}
