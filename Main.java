import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String nome;
        int quantidade;
        int opcao = 0;
        boolean run = true;

        Lutador[] lutadores;
        Scanner sc = new Scanner(System.in);

        // === JOGO DE LUTA ===

        do {
            System.out.println("=== Jogo de Luta ===");
            System.out.println("Insira quantos jogadores irão participar (Mínimo 2): ");
            quantidade = sc.nextInt();
            sc.nextLine();

            if (quantidade < 2) {
                System.out.println("Mínimo de 2 jogadores. Insira outro valor!");
                continue;
            }
            
            lutadores = new Lutador[quantidade];

            for (int i = 0; i < quantidade; i++) {
                System.out.println("--- jogador " + (i + 1) + " ---");
                System.out.println("Insira o seu nome:");
                nome = sc.nextLine();

                do {
                    System.out.println("escolha o número da opção do seu tipo de lutador: ");
                    System.out.println("1- Lutador leve:\n      Velocidade rápida | Vida menor | Dano menor.");
                    System.out.println("2- Lutador Médio:\n      Velocidade média | Vida média | Dano médio.");
                    System.out.println("3- Lutador Pesado:\n      Velocidade lenta | Vida maior | Dano maior.");

                    if (sc.hasNextInt()) {
                        opcao = sc.nextInt();
                        sc.nextLine(); 

                        if (opcao == 1) {
                            lutadores[i] = new LutadorLeve(nome);
                        } else if (opcao == 2) {
                            lutadores[i] = new LutadorMedio(nome);
                        } else if (opcao == 3) {
                            lutadores[i] = new LutadorPesado(nome);
                        }
                    } else {
                        opcao = 0;
                        sc.next();
                    }

                    if (opcao < 1 || opcao > 3) {
                        System.out.println("Escolha invalida. Escolha de 1 a 3!");
                    }
                } while (opcao < 1 || opcao > 3);
            }

            System.out.println(" --- Iniciando Confronto e Ordenando Turnos ---");
            Arrays.sort(lutadores, new ComparadorVelocidade());

            System.out.println("Ordem de ataque do mais rapido ao mais lento: ");
            for (int i = 0; i < quantidade; i++) {
                System.out.println((i + 1) + ". " + lutadores[i].nome + " (Velocidade: " + lutadores[i].getVelocidade() + ")");
            }

            
        } while (run);

        sc.close();
    }
}
