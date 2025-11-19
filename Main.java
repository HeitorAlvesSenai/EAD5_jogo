import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String nome;
        int quantidade = 0;
        int opcao = 0;
        int lutadoresMortos;
        int lutadoresVivos;
        boolean valido = false;
        boolean run = true;

        Lutador[] lutadores;
        Scanner sc = new Scanner(System.in);

        // === JOGO DE LUTA ===

        do {
            System.out.println("=== Jogo de Luta ===");
            valido = false;
            while (!valido) {
                try {
                    System.out.println("Insira quantos jogadores irão participar (Mínimo 2): ");
                    quantidade = sc.nextInt();
                    sc.nextLine();
                        valido = true;
                    if (quantidade >= 2) {
                        
                    } else {
                        System.out.println("Mínimo de 2 jogadores. Insira outro valor!");
                    }
                } catch (Exception e) {
                    System.out.println("Você deve apenas colocar números inteiros.");
                    sc.nextLine();
                }
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

            System.out.println("A ordem de ataque é do mais rapido para o mais lento: ");
            for (int i = 0; i < quantidade; i++) {
                System.out.println((i + 1) + "º. " + lutadores[i].nome + " (Velocidade: " + lutadores[i].getVelocidade() + ")");
                System.out.println("-------------------------------------------------------------------------");
            }

            lutadoresVivos = quantidade;
            lutadoresMortos = 0;

            while (run) {
                for (Lutador atacante: lutadores) {
                    if (atacante.estarVivo() == true) {
                        System.out.println("\n" + atacante.nome + ", o que você quer fazer?");
                        
                        valido = false;
                        while (!valido) {
                            try {
                                System.out.println("1- Atacar | 2- Defender | 3- Usar especial");

                                opcao = sc.nextInt();
                                if (opcao >= 1 && opcao <= 3) {
                                    valido = true;
                                } else {
                                    System.out.println("Escolha de 1 até 3!");
                                }
                            } catch (Exception e) {
                                System.out.println("Você deve apenas colocar números inteiros.");
                                sc.nextLine();
                            }
                        }
                        valido = false;

                        while (run) {
                            
                            if (opcao >= 1 && opcao <= 3) {

                                // ATAQUE NORMAL
                                if (opcao == 1) {
                                    System.out.println("Quem você quer atacar?");
                                    for (int i = 0; i < quantidade; i++) {
                                        if (atacante != lutadores[i] && lutadores[i].estarVivo()) {
                                            System.out.println("\n" +
                                                (i + 1) + "- " +lutadores[i].nome +
                                                "(Vida: " + lutadores[i].vida +
                                                " | Energia " + lutadores[i].energia +
                                                " | Defesa " + (lutadores[i].defesa? "Ativada": "Desativada"));
                                            }
                                    }
                                    
                                    while (run) {
                                        
                                        valido = false;
                                        while(!valido) {
                                            try {
                                                opcao = sc.nextInt();
                                                valido = true;
                                            } catch (Exception e) {
                                                System.out.println("Escolha com números inteiros!");
                                                sc.nextLine();
                                            }
                                        }

                                        if (opcao > 0 && opcao <= quantidade) {
                                            for (int i = 0; i < quantidade; i++) {
                                                if ((opcao - 1) == i) {
                                                    if (atacante == lutadores[i]) {
                                                        System.out.println("Você não pode se bater! Escolha outra pessoa.");
                                                    } else if (atacante != lutadores[i] && lutadores[i].estarVivo() == true) {
                                                        atacante.atacar(lutadores[i]);
                                                        run = false;
                                                    } else {
                                                        System.out.println("Esse lutador está morto...\nEscolha outra pessoa.");
                                                        run = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    run = true;
                                
                                // DEFESA
                                } else if (opcao == 2) {
                                    atacante.defender();

                                // ESPECIAL
                                } else if (opcao == 3) {
                                    System.out.println("Quem você quer atacar?");
                                    for (int i = 0; i < quantidade; i++) {
                                        if (atacante != lutadores[i] && lutadores[i].estarVivo()) {
                                            System.out.println("\n" +
                                                (i + 1) + "- " +lutadores[i].nome +
                                                "(Vida: " + lutadores[i].vida +
                                                " | Energia " + lutadores[i].energia +
                                                " | Defesa " + (lutadores[i].defesa? "Ativada": "Desativada"));
                                            }
                                    }
                                    
                                    while (run) {

                                        valido = false;
                                        while(!valido) {
                                            try {
                                                opcao = sc.nextInt();
                                                valido = true;
                                            } catch (Exception e) {
                                                System.out.println("Escolha com números inteiros!");
                                                sc.nextLine();
                                            }
                                        }

                                        if (opcao > 0 && opcao <= quantidade) {
                                            for (int i = 0; i < quantidade; i++) {
                                                if ((opcao - 1) == i) {
                                                    if (atacante == lutadores[i]) {
                                                        System.out.println("Você não pode se bater! Escolha outra pessoa.");
                                                    } else if (atacante != lutadores[i] && lutadores[i].estarVivo() == true) {
                                                        atacante.especial(lutadores[i]);
                                                        run = false;
                                                    } else {
                                                        System.out.println("Esse lutador está morto...\nEscolha outra pessoa.");
                                                        run = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    run = true;
                                }
                                run = false;
                            } else {

                                valido = false;
                                while (!valido) {
                                    try {
                                        System.out.println("Escolha de 1 até 3!");
                                        opcao = sc.nextInt();
                                        valido = true;
                                    } catch (Exception e) {
                                        System.out.println("Escolha com números inteiros!");
                                    }
                                }
                            }
                        }
                        run = true;
                    } else {
                        lutadoresMortos = 0;
                        for (Lutador morto: lutadores) {
                            if (morto.estarVivo() == false) {
                                 lutadoresMortos += 1;
                            } 
                        }
                    }
                    if ((lutadoresVivos - lutadoresMortos) == 1) {
                        for (Lutador vencedor : lutadores) {
                            if (vencedor.estarVivo() == true) {
                                System.out.println("\n===O vencedor é " + vencedor.nome + "!!!===\n Fim.\nComeçando outra partida...\n ");
                            }
                        }
                        run = false;
                        break;
                    }
                }
            }
            run = true;

        } while (run);

        sc.close();
    }
}
