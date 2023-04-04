package br.com.adopet.domain.adocao;

public record DadosDetalhamentoAdocao(String nomeAnimal, String nomeTutor) {
    public DadosDetalhamentoAdocao(Adocao adocao) {
        this(adocao.getAnimal().getNome(), adocao.getTutor().getNome());
    }
}
