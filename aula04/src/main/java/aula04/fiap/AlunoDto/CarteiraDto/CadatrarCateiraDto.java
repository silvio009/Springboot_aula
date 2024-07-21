package aula04.fiap.AlunoDto.CarteiraDto;

import java.time.LocalDate;

public record CadatrarCateiraDto(LocalDate dataEmissao, LocalDate dataVencimento, Boolean ativo) {

}
