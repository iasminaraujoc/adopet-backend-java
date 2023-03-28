package br.com.adopet.domain.abrigo;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAbrigo(
        @NotBlank
        String nome) {

}
