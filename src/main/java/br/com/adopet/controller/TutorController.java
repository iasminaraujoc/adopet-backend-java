package br.com.adopet.controller;

import br.com.adopet.domain.tutor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTutor(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriBuilder){
        var tutor = new Tutor(dados);
        repository.save(tutor);

        var uri = uriBuilder.path("/tutores/{id}").buildAndExpand(tutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTutor(tutor));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemTutor>> listarTutores(){
        var lista= repository.findAll().stream().map(DadosListagemTutor::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoTutor> listarTutorPeloId(@PathVariable Long id){
        Optional<Tutor> tutorOptional = repository.findById(id);
        if(tutorOptional.isPresent())
            return ResponseEntity.ok(new DadosDetalhamentoTutor(tutorOptional.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTutor(@RequestBody @Valid DadosAtualizacaoTutor dados){
        var tutor = repository.getReferenceById(dados.id());
        tutor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTutor(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
