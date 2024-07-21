package aula04.fiap.Repository;

import aula04.fiap.model.Aluno;
import aula04.fiap.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {
}
