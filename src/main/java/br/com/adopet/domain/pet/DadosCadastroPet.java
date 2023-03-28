package br.com.adopet.domain.pet;

import br.com.adopet.domain.abrigo.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPet(

        @NotBlank
        String nome,

        @NotBlank
        String idade,

        @NotNull
        Porte porte,

        @NotBlank
        String descricao,

        @NotBlank
        String cidade,

        @NotNull
        Abrigo abrigo
) {}
