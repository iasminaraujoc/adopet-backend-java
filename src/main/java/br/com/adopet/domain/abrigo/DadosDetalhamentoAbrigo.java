package br.com.adopet.domain.abrigo;

public record DadosDetalhamentoAbrigo(Long id, String nome) {
    public DadosDetalhamentoAbrigo(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome());
    }

}
