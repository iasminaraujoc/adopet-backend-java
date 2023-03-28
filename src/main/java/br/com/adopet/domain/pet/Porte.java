package br.com.adopet.domain.pet;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Porte {
    PEQUENO("pequeno"),
    MEDIO("médio"),
    GRANDE("grande");

    private String definicao;

    Porte(String def){
        this.definicao = def;
    }

    public String getDefinicao() {
        return definicao;
    }
}
