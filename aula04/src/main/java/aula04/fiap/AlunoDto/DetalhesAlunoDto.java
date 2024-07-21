package aula04.fiap.AlunoDto;

import aula04.fiap.model.Aluno;
import aula04.fiap.model.Carteira;
import aula04.fiap.model.NivelEscolaridade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalhesAlunoDto( Long rm,String nome, String cpf, LocalDate dataNascimento, LocalDateTime dataCadastro, NivelEscolaridade nivelEscolaridade, BigDecimal renda) {

    public DetalhesAlunoDto(Aluno aluno){
        this(aluno.getRm(),aluno.getNome(),aluno.getCpf(),aluno.getDataNascimento(),aluno.getDataCadastro(),aluno.getNivelEscolaridade(),aluno.getRenda());
    }


}
