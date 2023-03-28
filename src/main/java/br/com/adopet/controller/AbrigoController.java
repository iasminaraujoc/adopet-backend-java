package br.com.adopet.controller;

import br.com.adopet.domain.abrigo.*;
import br.com.adopet.domain.tutor.DadosAtualizacaoTutor;
import br.com.adopet.domain.tutor.DadosDetalhamentoTutor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAbrigo(@RequestBody @Valid DadosCadastroAbrigo dados, UriComponentsBuilder uriBuilder){
        var abrigo = new Abrigo(dados);
        abrigoRepository.save(abrigo);

        var uri = uriBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoAbrigo>> listarAbrigos(){
        List<Abrigo> abrigos = abrigoRepository.findAll();
        if(!abrigos.isEmpty()) {
            var abrigosDto = abrigos.stream().map(DadosDetalhamentoAbrigo::new).toList();
            return ResponseEntity.ok(abrigosDto);
        }
        return ResponseEntity.notFound().build(); //TODO: retornar mensagem "nao encontrado"
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoAbrigo> listarAbrigoPeloId(@PathVariable Long id){
        Optional<Abrigo> abrigoOptional = abrigoRepository.findById(id);
        if(abrigoOptional.isPresent())
            return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigoOptional.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarAbrigo(@RequestBody @Valid DadosAtualizacaoAbrigo dados){
        var abrigo = abrigoRepository.getReferenceById(dados.id());
        abrigo.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigo));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluirAbrigos(@PathVariable Long id){
        abrigoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
