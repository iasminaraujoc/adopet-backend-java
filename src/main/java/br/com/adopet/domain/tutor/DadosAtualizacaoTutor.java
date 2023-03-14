package br.com.adopet.domain.tutor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTutor (
        @NotNull Long id, String nome, String email, String foto, String telefone, String cidade, String sobre) {
}
