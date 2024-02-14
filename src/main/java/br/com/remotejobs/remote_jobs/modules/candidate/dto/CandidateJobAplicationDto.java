package br.com.remotejobs.remote_jobs.modules.candidate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateJobAplicationDto {
    private int jobId;
    private String jobTitle;
    private int candidateId;
    private String candidateName;
}
