package br.com.adopet.domain.tutor;

import br.com.adopet.domain.adocao.Adocao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tutores")
@Entity(name = "Tutor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String email;

    private String foto;

    private String telefone;

    private String cidade;

    private String sobre;


    public Tutor(DadosCadastroTutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.foto = dados.foto();
        this.telefone = dados.telefone();
        this.cidade = dados.cidade();
        this.sobre = dados.sobre();
    }

    public void atualizarInformacoes(DadosAtualizacaoTutor dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.foto() != null){
            this.foto = dados.foto();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if(dados.sobre() != null){
            this.sobre = dados.sobre();
        }

    }
}
