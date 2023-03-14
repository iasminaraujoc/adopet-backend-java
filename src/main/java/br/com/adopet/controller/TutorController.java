package br.com.adopet.controller;

import br.com.adopet.domain.tutor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTutor dados){
        repository.save(new Tutor(dados));
    }

    @GetMapping
    public List<DadosListagemTutor> listar(){
        return repository.findAll().stream().map(DadosListagemTutor::new).toList();
    }

    @GetMapping("{id}")
    public DadosListagemTutor listarPeloId(@PathVariable Long id){
        return new DadosListagemTutor(repository.getReferenceById(id));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTutor dados){
        Tutor tutor = repository.getReferenceById(dados.id());
        tutor.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
