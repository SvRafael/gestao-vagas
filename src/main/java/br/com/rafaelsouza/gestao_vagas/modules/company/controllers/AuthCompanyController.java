package br.com.rafaelsouza.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelsouza.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.rafaelsouza.gestao_vagas.modules.company.service.AuthCompanyService;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    
    @Autowired
    private AuthCompanyService authCompanyService;

    @PostMapping("/company")
    public ResponseEntity<Object> authLogin(@RequestBody AuthCompanyDTO authCompanyDTO){
        try{
            var result = this.authCompanyService.authLogin(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
        
    }

}
