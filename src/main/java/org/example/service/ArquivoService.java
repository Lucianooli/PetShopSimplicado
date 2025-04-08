package org.example.service;

import org.example.model.Animal;
import org.example.model.Servico;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoService {

    public static void salvarAnimais(List<Animal> animais) {
        try (FileWriter writer = new FileWriter("animais.txt")) {
            for (Animal animal : animais) {
                writer.write(animal.getNome() + "," + animal.getRaca() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar animais.");
        }
    }

    public static void salvarServicos(List<Servico> servicos) {
        try (FileWriter writer = new FileWriter("servicos.txt")) {
            for (Servico servico : servicos) {
                writer.write(servico.getNome() + "," + servico.getPreco() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar serviços.");
        }
    }

    public static List<Animal> lerAnimais() {
        List<Animal> animais = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("animais.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    animais.add(new Animal(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de animais não encontrado.");
        }
        return animais;
    }

    public static List<Servico> lerServicos() {
        List<Servico> servicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("servicos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    String nome = partes[0];
                    double preco = Double.parseDouble(partes[1]);
                    servicos.add(new Servico(nome, preco));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de serviços não encontrado.");
        }
        return servicos;
    }
}
