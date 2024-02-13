package br.com.remotejobs.remote_jobs.modules.company.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.remotejobs.remote_jobs.modules.company.CompanyEntity;
import br.com.remotejobs.remote_jobs.modules.company.JobEntity;
import br.com.remotejobs.remote_jobs.modules.company.useCases.CreateJobUseCase;
import br.com.remotejobs.remote_jobs.modules.company.useCases.FindJobUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/company/jobs")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Autowired
    private FindJobUseCase findJobsUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity job) {
        try {
            var result = this.createJobUseCase.execute(job);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> find(@RequestParam Map<String, String> param) {
        try {
            var result = this.findJobsUseCase.execute(param.get("title"));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
