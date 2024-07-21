package aula04.fiap.AlunoDto.ProfessorDto;

import aula04.fiap.model.Professor;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalhesProfessorDto(Long id, String nome, LocalDate contratacao, BigDecimal salario) {
    public DetalhesProfessorDto(Professor professor){
        this(professor.getId(),professor.getNome(),professor.getContratacao(),professor.getSalario());
    }
}
