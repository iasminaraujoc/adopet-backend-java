package br.com.adopet.controller;

import br.com.adopet.domain.adocao.Adocao;
import br.com.adopet.domain.adocao.AdocaoRepository;
import br.com.adopet.domain.adocao.DadosCadastroAdocao;
import br.com.adopet.domain.adocao.DadosDetalhamentoAdocao;
import br.com.adopet.domain.pet.DadosCadastroPet;
import br.com.adopet.domain.pet.DadosDetalhamentoPet;
import br.com.adopet.domain.pet.PetRepository;
import br.com.adopet.domain.tutor.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoRepository adocaoRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAdocao(@RequestBody @Valid DadosCadastroAdocao dados, UriComponentsBuilder uriBuilder){
        var pet = petRepository.getReferenceById(dados.animalId());
        var tutor = tutorRepository.getReferenceById(dados.tutorId());

        var adocao = new Adocao(dados, pet, tutor);
        adocaoRepository.save(adocao);

        var uri = uriBuilder.path("/adocoes/{id}").buildAndExpand(adocao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAdocao(adocao));
    }



}
