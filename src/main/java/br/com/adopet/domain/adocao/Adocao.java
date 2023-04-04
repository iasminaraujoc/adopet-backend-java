package br.com.adopet.domain.adocao;

import br.com.adopet.domain.pet.Pet;
import br.com.adopet.domain.pet.PetRepository;
import br.com.adopet.domain.tutor.Tutor;
import br.com.adopet.domain.tutor.TutorRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "adocoes")
@Entity(name = "Adocao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Pet animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    private LocalDate dataAdocao;

    public Adocao(DadosCadastroAdocao dados, Pet pet, Tutor tutor) {
        this.animal = pet;
        this.tutor = tutor;
        this.dataAdocao = dados.data();
    }
}
