package aula04.fiap.AlunoDto.TurmaDto;

import aula04.fiap.model.Periodo;

public record AtualizarTurmaDto(Long id, String nome, int aula, Periodo periodo) {
}
