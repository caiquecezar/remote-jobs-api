package br.com.remotejobs.remote_jobs.modules.company;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços.")
    private String username;
    @Length(min = 10, max = 100, message = "O campo [password] deve conter entre 10 e 100 caracteres.")
    private String password;
    @Email(message = "O campo [email] deve conter um email válido.")
    private String email;
    private String name;
    private String type;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
}