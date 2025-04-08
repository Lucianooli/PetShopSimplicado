package org.example.model;

public class Animal {
    private String nome;
    private String raca;

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    @Override
    public String toString() {
        return nome + " (Raça: " + raca + ")";
    }
}
