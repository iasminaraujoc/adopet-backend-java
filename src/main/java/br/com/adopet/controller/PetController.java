package br.com.adopet.controller;


import br.com.adopet.domain.abrigo.Abrigo;
import br.com.adopet.domain.abrigo.AbrigoRepository;
import br.com.adopet.domain.pet.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPet(@RequestBody @Valid DadosCadastroPet dados, UriComponentsBuilder uriBuilder){
        var pet = new Pet(dados, abrigoRepository);
        petRepository.save(pet);

        var uri = uriBuilder.path("/abrigos/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPet(pet));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPet>> listarPets(){
        List<Pet> pets = petRepository.findAll();
        if(!pets.isEmpty()) {
            var petsDto = pets.stream().map(DadosDetalhamentoPet::new).toList();
            return ResponseEntity.ok(petsDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoPet> listarPetPeloId(@PathVariable Long id){
        Optional<Pet> petOptional = petRepository.findById(id);
        if(petOptional.isPresent())
            return ResponseEntity.ok(new DadosDetalhamentoPet(petOptional.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPet(DadosAtualizacaoPet dados){
        var pet = petRepository.getReferenceById(dados.id());

        pet.atualizarInformacoes(dados, abrigoRepository);

        return ResponseEntity.ok(new DadosDetalhamentoPet(pet));

    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluirPets(@PathVariable Long id){
        petRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
