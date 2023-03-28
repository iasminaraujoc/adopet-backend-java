package br.com.adopet.domain.pet;

import br.com.adopet.domain.abrigo.Abrigo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Pet(DadosCadastroPet dados) {
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.porte = dados.porte();
        this.descricao = dados.descricao();
        this.cidade = dados.cidade();
        this.abrigo = dados.abrigo();
    }

    public void atualizarInformacoes(DadosAtualizacaoPet dados) {
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
        if(dados.abrigo() != null){
            this.abrigo = dados.abrigo();
        }
    }
}
