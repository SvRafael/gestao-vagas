package br.com.rafaelsouza.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelsouza.gestao_vagas.exceptions.UserFoundException;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CandidateService {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity createCandidate(CandidateEntity candidateEntity){
        this.candidateRepository
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });
        return this.candidateRepository.save(candidateEntity);
    }

}
