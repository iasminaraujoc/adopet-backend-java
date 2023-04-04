package br.com.adopet.domain.adocao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosCadastroAdocao(Long animalId, Long tutorId, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data) {
}
