package br.com.rafaelsouza.gestao_vagas.modules.candidates.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import br.com.rafaelsouza.gestao_vagas.exceptions.JobNotFoundException;
import br.com.rafaelsouza.gestao_vagas.exceptions.UserNotFoundException;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.rafaelsouza.gestao_vagas.modules.candidate.services.ApplyJobService;
import br.com.rafaelsouza.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobServiceTest {
    
    @InjectMocks
    private ApplyJobService applyJobService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found(){
        try{
            applyJobService.applyJob(null, null);
        }catch(Exception ex){
            assertThat(ex).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var idCandidate = UUID.randomUUID();
        try{
            var candidate = new CandidateEntity();
            candidate.setId(idCandidate);

            when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        }catch(Exception ex){
            assertThat(ex).isInstanceOf(UserNotFoundException.class);
        }

        try{
            applyJobService.applyJob(idCandidate, null);
        }catch(Exception ex){
            assertThat(ex).isInstanceOf(JobNotFoundException.class);
        }
    }

}
