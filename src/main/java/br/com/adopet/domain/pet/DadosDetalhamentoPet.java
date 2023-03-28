package br.com.adopet.domain.pet;

public record DadosDetalhamentoPet(String nome, String idade, Porte porte, String descricao, String cidade) {
    public DadosDetalhamentoPet(Pet pet) {
        this(pet.getNome(), pet.getIdade(), pet.getPorte(), pet.getDescricao(), pet.getIdade());
    }
}
