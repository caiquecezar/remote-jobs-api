package br.com.remotejobs.remote_jobs.exceptions;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException() {
        super("Empresa já cadastrada!");
    }
}
