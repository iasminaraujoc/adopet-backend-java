package br.com.adopet.domain.pet;

import br.com.adopet.domain.abrigo.Abrigo;
import br.com.adopet.domain.abrigo.AbrigoRepository;
//import br.com.adopet.domain.adocao.Adocao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String idade;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private String descricao;

    private String cidade;

    @ManyToOne
    @JoinColumn(name="abrigo_id")
    private Abrigo abrigo;

//    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private Adocao adocao;

    public Pet(DadosCadastroPet dados, AbrigoRepository repository) {
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.porte = dados.porte();
        this.descricao = dados.descricao();
        this.cidade = dados.cidade();
        this.abrigo = repository.getReferenceById(dados.abrigoId());
    }

    public void atualizarInformacoes(DadosAtualizacaoPet dados, AbrigoRepository repository) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.idade() != null){
            this.idade = dados.idade();
        }
        if(dados.porte() != null){
            this.porte = dados.porte();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.abrigoId() != null){
            this.abrigo = repository.getReferenceById(dados.abrigoId());
        }
    }
}
