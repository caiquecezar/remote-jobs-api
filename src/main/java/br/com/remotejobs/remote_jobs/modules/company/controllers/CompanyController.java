package br.com.remotejobs.remote_jobs.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.remotejobs.remote_jobs.modules.company.CompanyEntity;
import br.com.remotejobs.remote_jobs.modules.company.dto.AuthCompanyDto;
import br.com.remotejobs.remote_jobs.modules.company.useCases.AuthCompanyUseCase;
import br.com.remotejobs.remote_jobs.modules.company.useCases.CreateCompanyUseCase;
import br.com.remotejobs.remote_jobs.modules.company.useCases.FindCompanyUseCase;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;
    @Autowired
    private FindCompanyUseCase findCompanyUseCase;
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            var result = this.createCompanyUseCase.execute(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> find(@RequestParam Map<String, String> param) {
        try {
            var result = this.findCompanyUseCase.execute(param.get("name"));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth/")
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthCompanyDto authCompanyDto) {
        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
