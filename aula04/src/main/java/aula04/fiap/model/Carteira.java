package aula04.fiap.model;

import aula04.fiap.AlunoDto.CarteiraDto.AtualizarCarteiraDto;
import aula04.fiap.AlunoDto.CarteiraDto.CadatrarCateiraDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "TB_CARTERINHA")
public class Carteira {

    // relacionamento 1:1
    @OneToOne (cascade = CascadeType.PERSIST) // relação 1:1
    @JoinColumn(name = "nr_rm") // nome da fk que está sensdo instaciada
    private Aluno aluno; // recebendo do aluno



    @Id
    @GeneratedValue
    @Column(name = "ID_CARTERINHA", length = 8)
    private long id;

    @Column(name = "DT_EMISSAO",nullable = false)
    private LocalDate dataEmissao;
    @Column(name = "DT_VENCIMENTO",nullable = false)
    private LocalDate dataVencimento;
    @Column(name = "ST_ATIVO",length = 1,nullable = false)
    private Boolean ativo;

    public Carteira(CadatrarCateiraDto cadatrarCateiraDto) {
        dataEmissao = cadatrarCateiraDto.dataEmissao();
        dataVencimento = cadatrarCateiraDto.dataVencimento();
        ativo = cadatrarCateiraDto.ativo();
    }

    public void atualizarinfomacoes(AtualizarCarteiraDto atualizarCarteiraDto) {
        if (atualizarCarteiraDto.id() != null){
            id = atualizarCarteiraDto.id();
        }
        if ( atualizarCarteiraDto.dataEmissao() != null){
            dataVencimento = atualizarCarteiraDto.dataEmissao();
        }
        if (atualizarCarteiraDto.dataVencimento() != null){
            dataVencimento = atualizarCarteiraDto.dataVencimento();
        }
        if (atualizarCarteiraDto.ativo() != null){
            ativo = atualizarCarteiraDto.ativo();
        }
    }
}
