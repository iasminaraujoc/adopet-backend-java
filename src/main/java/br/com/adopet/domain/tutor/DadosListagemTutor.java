package br.com.adopet.domain.tutor;

public record DadosListagemTutor(Long id, String nome, String email, String foto, String telefone, String cidade, String sobre) {

    public DadosListagemTutor(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getEmail(), tutor.getFoto(), tutor.getTelefone(), tutor.getCidade(), tutor.getSobre());
    }
}
