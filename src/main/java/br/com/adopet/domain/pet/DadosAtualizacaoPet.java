package br.com.adopet.domain.pet;

import br.com.adopet.domain.abrigo.Abrigo;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPet(@NotNull Long id, String nome, String idade, Porte porte, String descricao, String cidade, Long abrigoId) {
}
