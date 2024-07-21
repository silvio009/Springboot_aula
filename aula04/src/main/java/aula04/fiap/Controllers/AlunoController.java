package aula04.fiap.Controllers;

import aula04.fiap.AlunoDto.AtualizarAlunoDTO;
import aula04.fiap.AlunoDto.CadastroAlunoDto;
import aula04.fiap.AlunoDto.DetalhesAlunoDto;
import aula04.fiap.AlunoDto.ListagemAlunoDto;
import aula04.fiap.Repository.AlunoRepository;
import aula04.fiap.model.Aluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    public AlunoRepository alunoRepository;


    @GetMapping
    public ResponseEntity<List<ListagemAlunoDto>> listaGet (Pageable pageable){
        var page = alunoRepository.findAll(pageable).stream().map(ListagemAlunoDto :: new ).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{rm}")
    public ResponseEntity<DetalhesAlunoDto> get (@PathVariable("rm") Long rm){
        var get = alunoRepository.getReferenceById(rm);
        return ResponseEntity.ok(new DetalhesAlunoDto(get));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesAlunoDto> post (@RequestBody @Valid CadastroAlunoDto cadastroAlunoDto, UriComponentsBuilder uriComponentsBuilder) {
    var aluno = new Aluno(cadastroAlunoDto);
    alunoRepository.save(aluno);
    var uri = uriComponentsBuilder.path("/aluno/{id}").buildAndExpand(aluno.getRm()).toUri();
    return ResponseEntity.created(uri).body(new DetalhesAlunoDto(aluno));
    }

    @PutMapping("{rm}")
    @Transactional
    public ResponseEntity<DetalhesAlunoDto> update (@PathVariable("rm") Long rm, @RequestBody AtualizarAlunoDTO atualizarAlunoDTO) {
        var atualizar = alunoRepository.getReferenceById(rm);
        atualizar.atualizarinfomacoes(atualizarAlunoDTO);
        return ResponseEntity.ok(new DetalhesAlunoDto(atualizar));

    }

    @DeleteMapping("{rm}")
    @Transactional
    public ResponseEntity<Void> deletar (@PathVariable("rm") Long rm){
    alunoRepository.deleteById(rm);
    return ResponseEntity.noContent().build();
    }



}
