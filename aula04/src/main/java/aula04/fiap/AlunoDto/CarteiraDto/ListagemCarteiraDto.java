package aula04.fiap.AlunoDto.CarteiraDto;

import aula04.fiap.model.Carteira;

import java.time.LocalDate;

public record ListagemCarteiraDto(Long id, LocalDate dataEmissao,LocalDate dataVencimento,Boolean ativo) {
    public ListagemCarteiraDto(Carteira carteira){
         this (carteira.getId(),carteira.getDataEmissao(),carteira.getDataVencimento(),carteira.getAtivo());
    }
}
