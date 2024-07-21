package aula04.fiap.AlunoDto;

import aula04.fiap.model.NivelEscolaridade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizarAlunoDTO(Long rm, String nome, String cpf, LocalDate dataNascimento, LocalDateTime dataCadastro, NivelEscolaridade nivelEscolaridade, BigDecimal renda) {
}
