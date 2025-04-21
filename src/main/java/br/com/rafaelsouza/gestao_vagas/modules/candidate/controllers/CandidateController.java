package br.com.rafaelsouza.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.services.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    CandidateService candidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
        try {
            var result =  candidateService.createCandidate(candidateEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        
    }

}
