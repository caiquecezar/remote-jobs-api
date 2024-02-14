package br.com.remotejobs.remote_jobs.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileDto {
    private int id;
    private String name;
    private String username;
    private String email;
    private String description;
    private String curriculum;
}
