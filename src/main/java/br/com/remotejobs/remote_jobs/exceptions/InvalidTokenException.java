package br.com.remotejobs.remote_jobs.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Token inválido!");
    }
}
