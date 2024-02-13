package br.com.remotejobs.remote_jobs.modules.candidate;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.remotejobs.remote_jobs.modules.company.JobEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "applications")
public class JobApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "candidateId", insertable = false, updatable = false)
    private CandidateEntity candidateEntity;
    @Column(name = "candidateId", nullable = false)
    private int candidateId;
    @ManyToOne()
    @JoinColumn(name = "jobId", insertable = false, updatable = false)
    private JobEntity jobEntity;
    @Column(name = "jobId", nullable = false)
    private int jobId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
