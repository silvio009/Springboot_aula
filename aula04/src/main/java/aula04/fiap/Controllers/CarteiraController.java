package aula04.fiap.Controllers;

import aula04.fiap.AlunoDto.AtualizarAlunoDTO;
import aula04.fiap.AlunoDto.CadastroAlunoDto;
import aula04.fiap.AlunoDto.CarteiraDto.AtualizarCarteiraDto;
import aula04.fiap.AlunoDto.CarteiraDto.CadatrarCateiraDto;
import aula04.fiap.AlunoDto.CarteiraDto.DetalhesCarteiraDto;
import aula04.fiap.AlunoDto.CarteiraDto.ListagemCarteiraDto;
import aula04.fiap.AlunoDto.DetalhesAlunoDto;
import aula04.fiap.AlunoDto.ListagemAlunoDto;
import aula04.fiap.Repository.AlunoRepository;
import aula04.fiap.Repository.CarteiraRepository;
import aula04.fiap.model.Aluno;
import aula04.fiap.model.Carteira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("Carteira")
public class CarteiraController {
    @Autowired
    public CarteiraRepository carteiraRepository;


    @GetMapping
    public ResponseEntity<List<ListagemCarteiraDto>> listaGet (Pageable pageable){
        var page = carteiraRepository.findAll(pageable).stream().map(ListagemCarteiraDto:: new ).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesCarteiraDto> get (@PathVariable("id") Long id){
        var get = carteiraRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesCarteiraDto(get));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesCarteiraDto> post (@RequestBody CadatrarCateiraDto cadatrarCateiraDto, UriComponentsBuilder uriComponentsBuilder) {
        var carteira = new Carteira(cadatrarCateiraDto);
        carteiraRepository.save(carteira);
        var uri = uriComponentsBuilder.path("/Carteira/{id}").buildAndExpand(carteira.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCarteiraDto(carteira));
    }

    @PutMapping("{rm}")
    @Transactional
    public ResponseEntity<DetalhesCarteiraDto> update (@PathVariable("id") Long id, @RequestBody AtualizarCarteiraDto atualizarCarteiraDto) {
        var atualizar = carteiraRepository.getReferenceById(id);
        atualizar.atualizarinfomacoes(atualizarCarteiraDto);
        return ResponseEntity.ok(new DetalhesCarteiraDto(atualizar));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar (@PathVariable("id") Long id){
        carteiraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
