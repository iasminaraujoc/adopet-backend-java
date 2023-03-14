package br.com.adopet.domain.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroTutor(
        @NotBlank
        String nome,

        @NotBlank @Email
        String email,

        @NotBlank
        String foto,

        @NotBlank @Pattern(regexp = "\\d{10,11}")
        String telefone,

        @NotBlank
        String cidade,

        @NotBlank
        String sobre) {

}
