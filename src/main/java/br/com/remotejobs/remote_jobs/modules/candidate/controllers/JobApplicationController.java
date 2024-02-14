package br.com.remotejobs.remote_jobs.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.remotejobs.remote_jobs.modules.candidate.useCases.CandidateAppliesInAJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class JobApplicationController {
    @Autowired
    private CandidateAppliesInAJobUseCase candidateAppliesInAJobUseCase;

    @PostMapping("/apply")
    public ResponseEntity<Object> create(@Valid @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        try {
            var candidateId = request.getAttribute("candidateId").toString();
            var result = this.candidateAppliesInAJobUseCase.execute(Integer.parseInt(candidateId), body.get("jobId"));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
