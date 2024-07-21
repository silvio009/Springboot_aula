package aula04.fiap.model;


import aula04.fiap.AlunoDto.TurmaDto.AtualizarTurmaDto;
import aula04.fiap.AlunoDto.TurmaDto.CadastroTurmaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_TURMA")

public class Turma {



    // mappedby é o dono da relaçao, ou seja, quem vai maperar ou doar a pk para outra entidade
    @OneToMany(mappedBy = "turma",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Aluno>alunos = new ArrayList<>();



    // medoto para cadastro de turma e alunos em cascata
    // seta a Fk na tabela aluno
    public void addaluno(Aluno aluno){
        aluno.setTurma(this); // seta a fk no banco
        alunos.add(aluno); // add o aluno na lista de aluno
    }


    @ManyToMany(mappedBy = "turmas")
    private List<Professor> professors;



    @Id
    @GeneratedValue
    @Column(name = "ID_TURMA", length = 8,nullable = false)
    private long id;

    @Column(name = "NM_TURMA" , length = 100,nullable = false)
    private String nome;

    @Column(name= "QT_AULA", length = 30,nullable = false)
    private int aula;


    @Enumerated(EnumType.STRING)
    @Column(name = "DS_PERIODO", length = 20,nullable = false)
    private Periodo periodo;


    public Turma(CadastroTurmaDto cadastroTurmaDto) {
        nome = cadastroTurmaDto.nome();
        aula = cadastroTurmaDto.aula();
        periodo = cadastroTurmaDto.periodo();
    }

    public void atualizarinfomacoes(AtualizarTurmaDto atualizarTurmaDto) {
        if (atualizarTurmaDto.id() != null){
            id = atualizarTurmaDto.id();
        }
        if (atualizarTurmaDto.nome() != null){
            nome = atualizarTurmaDto.nome();
        }
        if (atualizarTurmaDto.aula() != 0){
            aula  = atualizarTurmaDto.aula();
        }
        if (atualizarTurmaDto.periodo() != null){
            periodo = atualizarTurmaDto.periodo();
        }
    }
}
