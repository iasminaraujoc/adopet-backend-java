package br.com.adopet.controller;

import br.com.adopet.domain.tutor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriBuilder){
        var tutor = new Tutor(dados);
        repository.save(tutor);

        var uri = uriBuilder.path("/tutores/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTutor(tutor));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemTutor>> listar(){
        var lista= repository.findAll().stream().map(DadosListagemTutor::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoTutor> listarPeloId(@PathVariable Long id){
        return ResponseEntity.ok(new DadosDetalhamentoTutor(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTutor dados){
        var tutor = repository.getReferenceById(dados.id());
        tutor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
