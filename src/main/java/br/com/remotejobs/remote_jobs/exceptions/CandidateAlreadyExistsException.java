package br.com.remotejobs.remote_jobs.exceptions;

public class CandidateAlreadyExistsException extends RuntimeException {
    public CandidateAlreadyExistsException() {
        super("Usuário já existe!");
    }
}
