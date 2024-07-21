package aula04.fiap.Repository;

import aula04.fiap.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
