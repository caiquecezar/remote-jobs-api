package br.com.remotejobs.remote_jobs.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.remotejobs.remote_jobs.exceptions.CandidateDoesntExistsException;
import br.com.remotejobs.remote_jobs.modules.candidate.CandidateEntity;
import br.com.remotejobs.remote_jobs.modules.candidate.dto.AuthCandidateDto;
import br.com.remotejobs.remote_jobs.modules.candidate.useCases.AuthCandidateUseCase;
import br.com.remotejobs.remote_jobs.modules.candidate.useCases.CandidateProfileUseCase;
import br.com.remotejobs.remote_jobs.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.remotejobs.remote_jobs.modules.candidate.useCases.UpdateCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private UpdateCandidateUseCase updateCandidateUseCase;

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @Autowired
    private CandidateProfileUseCase candidateProfileUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.updateCandidateUseCase.execute(Integer.parseInt(id), candidate);
            return ResponseEntity.ok().body(result);
        } catch (CandidateDoesntExistsException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth/")
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthCandidateDto authCandidateDto) {
        try {
            var result = this.authCandidateUseCase.execute(authCandidateDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/profile/")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        try {
            var candidateId = request.getAttribute("candidateId").toString();
            var result = this.candidateProfileUseCase.execute(Integer.parseInt(candidateId));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
