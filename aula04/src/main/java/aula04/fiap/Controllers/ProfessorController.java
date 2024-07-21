package aula04.fiap.Controllers;

import aula04.fiap.AlunoDto.AtualizarAlunoDTO;
import aula04.fiap.AlunoDto.CadastroAlunoDto;
import aula04.fiap.AlunoDto.DetalhesAlunoDto;
import aula04.fiap.AlunoDto.ListagemAlunoDto;
import aula04.fiap.AlunoDto.ProfessorDto.AtualizarProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.CadastroProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.DetalhesProfessorDto;
import aula04.fiap.AlunoDto.ProfessorDto.ListagemProfesssorDto;
import aula04.fiap.Repository.AlunoRepository;
import aula04.fiap.Repository.ProfessorRepository;
import aula04.fiap.model.Aluno;
import aula04.fiap.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("Professor")
public class ProfessorController {
    @Autowired
    public ProfessorRepository professorRepository;


    @GetMapping
    public ResponseEntity<List<ListagemProfesssorDto>> listaGet (Pageable pageable){
        var page = professorRepository.findAll(pageable).stream().map(ListagemProfesssorDto :: new ).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesProfessorDto> get (@PathVariable("id") Long id){
        var get = professorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProfessorDto(get));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProfessorDto> post (@RequestBody CadastroProfessorDto cadastroProfessorDto, UriComponentsBuilder uriComponentsBuilder) {
        var professor= new Professor(cadastroProfessorDto);
        professorRepository.save(professor);
        var uri = uriComponentsBuilder.path("/Professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesProfessorDto(professor));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesProfessorDto> update (@PathVariable("id") Long id, @RequestBody AtualizarProfessorDto atualizarProfessorDto) {
        var atualizar = professorRepository.getReferenceById(id);
        atualizar.atualizarinfomacoes(atualizarProfessorDto);
        return ResponseEntity.ok(new DetalhesProfessorDto(atualizar));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar (@PathVariable("id") Long id){
        professorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
