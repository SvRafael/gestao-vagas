package br.com.rafaelsouza.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelsouza.gestao_vagas.modules.company.entities.JobEntity;
import br.com.rafaelsouza.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class JobService {

    @Autowired JobRepository jobRepository;

    public JobEntity create(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
