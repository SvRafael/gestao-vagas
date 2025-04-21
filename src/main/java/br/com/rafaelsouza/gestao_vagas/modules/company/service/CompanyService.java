package br.com.rafaelsouza.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rafaelsouza.gestao_vagas.exceptions.UserFoundException;
import br.com.rafaelsouza.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.rafaelsouza.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CompanyService {
 
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity createCompany(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return this.companyRepository.save(companyEntity);
    }

}
