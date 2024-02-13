package br.com.remotejobs.remote_jobs.exceptions;

public class CandidateDoesntExistsException extends RuntimeException {
    public CandidateDoesntExistsException() {
        super("Este candidato não existe!");
    }
}
