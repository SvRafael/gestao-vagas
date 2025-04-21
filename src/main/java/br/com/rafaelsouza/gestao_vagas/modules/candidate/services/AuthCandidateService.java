package br.com.rafaelsouza.gestao_vagas.modules.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateService {
    
    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO candidateLogin(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Usuário ou senha incorreto");
            });
        
        var passwordMatches = this.passwordEncoder
        .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("Simba Sistemas")
        .withExpiresAt(expiresIn)
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("candidate"))
        .sign(algorithm);
        
        var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .expires_in(expiresIn.toEpochMilli())
        .access_token(token)
        .build();

        return authCandidateResponse;
    }
}
