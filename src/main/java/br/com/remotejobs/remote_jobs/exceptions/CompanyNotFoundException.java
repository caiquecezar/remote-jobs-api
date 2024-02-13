package br.com.remotejobs.remote_jobs.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Empresa não encontrada!");
    }
}
