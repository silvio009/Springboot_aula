package aula04.fiap.Repository;

import aula04.fiap.model.Aluno;
import aula04.fiap.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository  extends JpaRepository<Turma, Long> {
}
