package br.com.rafaelsouza.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelsouza.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.rafaelsouza.gestao_vagas.modules.company.service.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try{
            var result = this.companyService.createCompany(companyEntity);
            return ResponseEntity.ok().body(result);
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        
    }

}
