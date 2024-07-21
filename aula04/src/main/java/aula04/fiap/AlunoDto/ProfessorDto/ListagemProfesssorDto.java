package aula04.fiap.AlunoDto.ProfessorDto;

import aula04.fiap.AlunoDto.CarteiraDto.ListagemCarteiraDto;
import aula04.fiap.model.Professor;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListagemProfesssorDto(Long id,String nome, LocalDate contratacao, BigDecimal salario) {

   public ListagemProfesssorDto(Professor professor){
        this(professor.getId(),professor.getNome(),professor.getContratacao(),professor.getSalario());
   }
}
