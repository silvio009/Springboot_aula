package aula04.fiap.model;


import aula04.fiap.AlunoDto.AtualizarAlunoDTO;
import aula04.fiap.AlunoDto.CadastroAlunoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ALUNO") // ALTERA O NOME NA TABELA SEM ALTERAR O NOME DA CLASSE
public class Aluno {

    // Relacionamento 1:1 Bidirecional
    @OneToOne(mappedBy = "aluno", // nome do atributo que mapeia a fk no banco (nao recebe fk / dono da relação)
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY) // lazy não traz as informações carterinha e eager traz as informações da carterinha
    private Carteira carteira;




    @ManyToOne()
    @JoinColumn(name= "cd_turma", nullable = false) // criando a fk na tabela aluno
    private Turma turma;




    @Id
    @GeneratedValue
    @Column(name = "ID_RM", length = 8)
    private Long rm;

    @Column(name = "NM_ALUNO", length = 100,nullable = false)  // Configurando nome, tamanho e prioridade dos atributos no banco
    private String nome;

    @Column(name = "NR_CPF", length = 11, nullable = false, unique = true)  // Configurando nome, tamanho e prioridade dos atributos no banco
    private String cpf;

    @Column(name = "DT_NASCIMENTO",nullable = false )
    private LocalDate dataNascimento;

    @Column(name = "DT_CADASTRO", nullable = false)
    @CreatedDate // gerar data de cadastro automatico
    private LocalDateTime dataCadastro;

    @Column(name = "DS_NIVEL_ESCOLARIDADE", length = 35,nullable = false)  // Configurando nome, tamanho e prioridade dos atributos no banco
    @Enumerated(EnumType.STRING) // tranformando o enum em uma string para o banco
    private NivelEscolaridade nivelEscolaridade;

    @Column(name = "NR_RENDA",scale = 2, precision = 10,nullable = true) // escola e precisão (10,2)
    private BigDecimal renda ;

    @Transient // apenas no java (NÃO SERÁ UMA COLUNA NO BANCO DE DADOS)
    private Integer idade;

    public Aluno(CadastroAlunoDto cadastroAlunoDto) {
       nome = cadastroAlunoDto.nome();
       cpf = cadastroAlunoDto.cpf();
       dataCadastro = cadastroAlunoDto.dataCadastro();
       dataNascimento = cadastroAlunoDto.dataNascimento();
       nivelEscolaridade = cadastroAlunoDto.nivelEscolaridade();
       renda = cadastroAlunoDto.renda();
    }


    public void atualizarinfomacoes(AtualizarAlunoDTO atualizarAlunoDTO) {
    if (atualizarAlunoDTO.rm() !=null){
        rm = atualizarAlunoDTO.rm();
    }
    if (atualizarAlunoDTO.nome() != null){
        nome = atualizarAlunoDTO.nome();
    }
    if (atualizarAlunoDTO.cpf() != null){
        cpf = atualizarAlunoDTO.cpf();
    }
    if (atualizarAlunoDTO.dataCadastro()!= null){
        dataNascimento = LocalDate.from(atualizarAlunoDTO.dataCadastro());
    }
    if (atualizarAlunoDTO.dataNascimento() !=null){
        dataNascimento = LocalDate.from(atualizarAlunoDTO.dataNascimento());
    }
    if (atualizarAlunoDTO.nivelEscolaridade() !=null){
        nivelEscolaridade = atualizarAlunoDTO.nivelEscolaridade();
    }
    if (atualizarAlunoDTO.renda() !=null){
        renda = atualizarAlunoDTO.renda();
    }
    }
}