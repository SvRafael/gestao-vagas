package br.com.rafaelsouza.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    CandidateService candidateService;

    @Autowired
    ProfileCandidateService profileCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
        try {
            var result =  candidateService.createCandidate(candidateEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }   
    }

    @GetMapping("/")
    public ResponseEntity<Object> getCandidateProfile(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");
        try{
            var profile = this.profileCandidateService.getCandidateProfile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
       
    }

}
