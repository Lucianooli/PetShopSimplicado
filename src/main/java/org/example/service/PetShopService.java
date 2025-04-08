package org.example.service;



import org.example.model.Animal;
import org.example.model.Servico;

import java.util.ArrayList;
import java.util.List;

public class PetShopService {
    private final List<Animal> animais = new ArrayList<>();
    private final List<Servico> servicos = new ArrayList<>();

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public String comprarServico(int indexServico, int indexAnimal) {
        if (indexServico < 0 || indexServico >= servicos.size() || indexAnimal < 0 || indexAnimal >= animais.size()) {
            return "Erro: índices inválidos.";
        }
        Servico servico = servicos.get(indexServico);
        Animal animal = animais.get(indexAnimal);
        return "✅ Serviço '" + servico.getNome() + "' foi aplicado no animal '" + animal.getNome() + "' com sucesso!";
    }
}
