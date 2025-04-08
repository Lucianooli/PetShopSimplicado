import org.example.model.Animal;
import org.example.model.Servico;
import org.example.service.ArquivoService;
import org.example.service.PetShopService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        PetShopService petShop = new PetShopService();

        petShop.getAnimais().addAll(ArquivoService.lerAnimais());
        petShop.getServicos().addAll(ArquivoService.lerServicos());

        boolean executando = true;

        while (executando) {
            String[] opcoes = {
                    "🐾 Cadastrar Animal",
                    "🧼 Cadastrar Serviço",
                    "📋 Listar Animais",
                    "📋 Listar Serviços",
                    "🛒 Comprar Serviço",
                    "❌ Sair"
            };

            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "🐶 Bem-vindo ao PetShop Interativo 🐕\n\nEscolha uma opção:",
                    "📍 Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (opcao) {
                case 0 -> cadastrarAnimal(petShop);
                case 1 -> cadastrarServico(petShop);
                case 2 -> listarAnimais(petShop);
                case 3 -> listarServicos(petShop);
                case 4 -> comprarServico(petShop);
                case 5, JOptionPane.CLOSED_OPTION -> {
                    ArquivoService.salvarAnimais(petShop.getAnimais());
                    ArquivoService.salvarServicos(petShop.getServicos());
                    executando = false;
                }
                default -> mostrarMensagemErro("Opção inválida.");
            }
        }

        mostrarMensagemInfo("🐾 Obrigado por usar o PetShop!");
    }

    private static void cadastrarAnimal(PetShopService petShop) {
        String nome = JOptionPane.showInputDialog("🐕 Nome do animal:");
        String raca = JOptionPane.showInputDialog("🐾 Raça do animal:");

        if (nome != null && raca != null && !nome.isBlank() && !raca.isBlank()) {
            petShop.adicionarAnimal(new Animal(nome.trim(), raca.trim()));
            mostrarMensagemInfo("✅ Animal cadastrado com sucesso!");
        } else {
            mostrarMensagemErro("❌ Dados inválidos. Preencha corretamente.");
        }
    }

    private static void cadastrarServico(PetShopService petShop) {
        String nome = JOptionPane.showInputDialog("🧼 Nome do serviço:");
        String precoStr = JOptionPane.showInputDialog("💰 Preço do serviço:");

        try {
            double preco = Double.parseDouble(precoStr);
            if (!nome.isBlank() && preco > 0) {
                petShop.adicionarServico(new Servico(nome.trim(), preco));
                mostrarMensagemInfo("✅ Serviço cadastrado com sucesso!");
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            mostrarMensagemErro("❌ Entrada inválida. Digite um valor numérico válido.");
        }
    }

    private static void listarAnimais(PetShopService petShop) {
        if (petShop.getAnimais().isEmpty()) {
            mostrarMensagemInfo("⚠️ Nenhum animal cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("📋 Lista de Animais:\n\n");
        for (int i = 0; i < petShop.getAnimais().size(); i++) {
            sb.append("🐾 ").append(i).append(" - ").append(petShop.getAnimais().get(i)).append("\n");
        }

        mostrarMensagemInfo(sb.toString());
    }

    private static void listarServicos(PetShopService petShop) {
        if (petShop.getServicos().isEmpty()) {
            mostrarMensagemInfo("⚠️ Nenhum serviço cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("📋 Lista de Serviços:\n\n");
        for (int i = 0; i < petShop.getServicos().size(); i++) {
            sb.append("💼 ").append(i).append(" - ").append(petShop.getServicos().get(i)).append("\n");
        }

        mostrarMensagemInfo(sb.toString());
    }

    private static void comprarServico(PetShopService petShop) {
        if (petShop.getAnimais().isEmpty() || petShop.getServicos().isEmpty()) {
            mostrarMensagemInfo("⚠️ Cadastre ao menos 1 animal e 1 serviço antes.");
            return;
        }

        listarAnimais(petShop);
        String animalIndexStr = JOptionPane.showInputDialog("📌 Informe o número do animal:");

        listarServicos(petShop);
        String servicoIndexStr = JOptionPane.showInputDialog("📌 Informe o número do serviço:");

        try {
            int indexAnimal = Integer.parseInt(animalIndexStr);
            int indexServico = Integer.parseInt(servicoIndexStr);

            String mensagem = petShop.comprarServico(indexServico, indexAnimal);
            mostrarMensagemInfo("✅ " + mensagem);
        } catch (Exception e) {
            mostrarMensagemErro("❌ Entrada inválida. Digite números válidos.");
        }
    }

    private static void mostrarMensagemInfo(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "📢 Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "⚠️ Erro", JOptionPane.ERROR_MESSAGE);
    }
}
