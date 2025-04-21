package br.com.rafaelsouza.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.dto.CandidateProfileResponseDTO;

@Service
public class ProfileCandidateService {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateProfileResponseDTO getCandidateProfile(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário nao encontrado");
        });
        
        var candidateDTO = CandidateProfileResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId())
        .build();
        return candidateDTO;
    }

}
