package br.com.adopet.domain.abrigo;

import br.com.adopet.domain.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "abrigos")
@Entity(name = "Abrigo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "abrigo")
    private List<Pet> pets = new ArrayList<>();

    public Abrigo(DadosCadastroAbrigo dados){
        this.nome = dados.nome();
    }

    public void atualizarInformacoes(DadosAtualizacaoAbrigo dados) {
        if(dados.nome()!=null)
            this.nome = dados.nome();
    }
}
