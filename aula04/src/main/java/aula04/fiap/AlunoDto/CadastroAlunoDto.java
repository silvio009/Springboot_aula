package aula04.fiap.AlunoDto;

import aula04.fiap.model.NivelEscolaridade;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CadastroAlunoDto(
        @NotBlank(message = "o nome é obrigatório") // notblack = obrigatório
        String nome,
        @NotBlank(message = "o cpf é obrigatório")
        @Size(max = 11, min = 11,message = "o cpf deve ter no maximo 11 números")
        String cpf,
        @Past // tem que estar no passado
        @NotNull(message = "a data de nascimento não pode ser nula ")
        LocalDate dataNascimento,
        @Past // tem que estar no passado
        @NotNull(message = "a data de cadastro não pode ser nula")
        LocalDateTime dataCadastro,

        @NotNull(message = "O valor nçao pode ser nulo")
        NivelEscolaridade nivelEscolaridade,

        @PositiveOrZero(message = "o valor deve ser positivo ou nulo") // valor deve ser positivo ou nulo
        BigDecimal renda
) {


}
