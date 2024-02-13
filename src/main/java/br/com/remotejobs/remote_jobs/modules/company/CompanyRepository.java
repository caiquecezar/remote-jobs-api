package br.com.remotejobs.remote_jobs.modules.company;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    List<CompanyEntity> findByNameContaining(String searchTerm);
}
