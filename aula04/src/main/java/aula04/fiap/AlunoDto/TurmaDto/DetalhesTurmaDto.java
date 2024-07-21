package aula04.fiap.AlunoDto.TurmaDto;

import aula04.fiap.model.Periodo;
import aula04.fiap.model.Turma;

public record DetalhesTurmaDto(Long id, String nome, int aula, Periodo periodo) {
    public DetalhesTurmaDto(Turma turma){
        this(turma.getId(),turma.getNome(),turma.getAula(),turma.getPeriodo());
    }
}
