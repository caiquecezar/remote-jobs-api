package br.com.remotejobs.remote_jobs.exceptions;

public class JobDoesntExistsException extends RuntimeException {
    public JobDoesntExistsException() {
        super("Esta vaga não existe!");
    }
}
