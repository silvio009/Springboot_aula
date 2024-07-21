package aula04.fiap.model;

import aula04.fiap.AlunoDto.ProfessorDto.AtualizarProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.CadastroProfessorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PROFESSOR")
public class Professor {

    @ManyToMany()
    @JoinTable(name = "TB_TURMA_PROFESSOR",
    joinColumns = @JoinColumn(name = "CD_PROFESSOR"),
    inverseJoinColumns=@JoinColumn(name ="CD_TURMA"))
    private List<Turma> turmas;



    @Id
    @GeneratedValue
    @Column(name = "ID_PROFESSOR", length = 8)
    private long id;

    @Column(name = "NM_PROFESSOR", length = 100,nullable = false)
    private String nome;

    @Column(name = "DT_CONTRATACAO",nullable = false)
    private LocalDate contratacao;

    @Column(name = "VL_SALARIO", precision = 9 , scale = 2 ,nullable = false)
    private BigDecimal salario;

    public Professor(CadastroProfessorDto cadastroProfessorDto) {
        nome = cadastroProfessorDto.nome();
        contratacao = cadastroProfessorDto.contratacao();
        salario = cadastroProfessorDto.salario();
    }

    public void atualizarinfomacoes(AtualizarProfessorDto atualizarProfessorDto) {
        if (atualizarProfessorDto.id() != null){
            id = atualizarProfessorDto.id();
        }
        if (atualizarProfessorDto.nome() != null){
            nome = atualizarProfessorDto.nome();
        }
        if (atualizarProfessorDto.contratacao() != null){
            contratacao = atualizarProfessorDto.contratacao();
        }
        if (atualizarProfessorDto.salario() != null){
            salario = atualizarProfessorDto.salario();
        }

    }
}