package aula04.fiap.AlunoDto.ProfessorDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarProfessorDto(Long id, String nome, LocalDate contratacao, BigDecimal salario) {
}
