package br.com.remotejobs.remote_jobs.modules.company;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "jobs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private CompanyEntity companyEntity;
    @Column(name = "companyId", nullable = false)
    private int companyId;
    @NotBlank(message = "O campo [title] é obrigatório!")
    private String title;
    @NotBlank(message = "O campo [description] é obrigatório!")
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
