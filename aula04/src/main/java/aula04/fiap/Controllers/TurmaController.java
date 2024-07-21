package aula04.fiap.Controllers;

import aula04.fiap.AlunoDto.ProfessorDto.AtualizarProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.CadastroProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.DetalhesProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.ListagemProfesssorDto;
import aula04.fiap.AlunoDto.TurmaDto.AtualizarTurmaDto;
import aula04.fiap.AlunoDto.TurmaDto.CadastroTurmaDto;
import aula04.fiap.AlunoDto.TurmaDto.DetalhesTurmaDto;
import aula04.fiap.AlunoDto.TurmaDto.ListagemTurmaDto;
import aula04.fiap.Repository.ProfessorRepository;
import aula04.fiap.Repository.TurmaRepository;
import aula04.fiap.model.Professor;
import aula04.fiap.model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("Turma")
public class TurmaController {
    @Autowired
    public TurmaRepository turmaRepository;


    @GetMapping
    public ResponseEntity<List<ListagemTurmaDto>> listaGet (Pageable pageable){
        var page = turmaRepository.findAll(pageable).stream().map(ListagemTurmaDto :: new ).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesTurmaDto> get (@PathVariable("id") Long id){
        var get = turmaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesTurmaDto(get));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTurmaDto> post (@RequestBody CadastroTurmaDto cadastroTurmaDto, UriComponentsBuilder uriComponentsBuilder) {
        var turma = new Turma(cadastroTurmaDto);
        turmaRepository.save(turma);
        var uri = uriComponentsBuilder.path("/Turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesTurmaDto(turma));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesTurmaDto> update (@PathVariable("id") Long id, @RequestBody AtualizarTurmaDto atualizarTurmaDto) {
        var atualizar = turmaRepository.getReferenceById(id);
        atualizar.atualizarinfomacoes(atualizarTurmaDto);
        return ResponseEntity.ok(new DetalhesTurmaDto(atualizar));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar (@PathVariable("id") Long id){
        turmaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
