package br.com.adopet.domain.tutor;

public record DadosDetalhamentoTutor(Long id, String nome, String email, String foto, String telefone, String cidade, String sobre) {
    public DadosDetalhamentoTutor(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getEmail(), tutor.getFoto(), tutor.getTelefone(), tutor.getCidade(), tutor.getSobre());
    }
}
