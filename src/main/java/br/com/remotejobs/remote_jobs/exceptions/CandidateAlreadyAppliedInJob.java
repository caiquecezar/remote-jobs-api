package br.com.remotejobs.remote_jobs.exceptions;

public class CandidateAlreadyAppliedInJob extends RuntimeException {
    public CandidateAlreadyAppliedInJob() {
        super("Você já aplicou para esta vaga!");
    }
}
