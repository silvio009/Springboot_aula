package aula04.fiap.AlunoDto.CarteiraDto;

import aula04.fiap.model.Aluno;
import aula04.fiap.model.Carteira;

import java.time.LocalDate;

public record AtualizarCarteiraDto(Long id,LocalDate dataEmissao, LocalDate dataVencimento, Boolean ativo) {


}
