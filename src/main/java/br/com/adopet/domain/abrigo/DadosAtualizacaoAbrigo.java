package br.com.adopet.domain.abrigo;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAbrigo(@NotNull Long id, String nome) {
}
