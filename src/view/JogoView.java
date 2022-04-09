package view;

import dao.JogoDAO;
import model.Jogo;

import java.util.Scanner;

public class JogoView {
    public static void exibir(JogoDAO jogoDAO, int codigo) {
        for(Jogo j : jogoDAO.listar(codigo)) {
            if(codigo == 0) {
                System.out.println("Jogo: " + j.getTitulo());
            } else {
                System.out.print("Jogo: " + j.getTitulo());
                System.out.print(", Ano: " + j.getAno());
                System.out.print(", Categoria: " + j.getCategoria());
                System.out.println(", Sinopse: " + j.getSinopse());
            }
        }
    }
    public static void menu() {
        JogoDAO jogoDAO = new JogoDAO();
        Scanner scan = new Scanner(System.in);

        int opcao, id;
        boolean selJogo, selLivro;

        do {
            selJogo = false;
            selLivro = false;
            System.out.println("\n- Menu -");
            System.out.println("0. Sair\n1. Jogos\n2. Livros");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scan.nextLine());

            if(opcao == 1) {
                selJogo = true;
            }
            else if(opcao == 2) {
                selLivro = true;
            }

            if(opcao != 0) {
                System.out.println("0. Sair\n1. Listar\n2. Cadastrar\n3. Atualizar\n4. Locar\n5. Devolver\n6. Deletar");
                System.out.print("Escolha: ");
                opcao = Integer.parseInt(scan.nextLine());
            }

            switch (opcao) {
                case 0 -> System.out.println("Fechando o programa...");
                case 1 -> {
                    System.out.println("- Listar -");
                    System.out.println("OBS: Digite 0 para listar tudo");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    exibir(jogoDAO, id);
                }
                case 2 -> {
                    Jogo jogo = new Jogo();
                    System.out.println("- Cadastrar -");
                    System.out.print("Nome: ");
                    jogo.setTitulo(scan.nextLine());
                    System.out.print("Ano: ");
                    jogo.setAno(Integer.parseInt(scan.nextLine()));
                    System.out.print("Categoria: ");
                    jogo.setCategoria(scan.nextLine());
                    System.out.print("Sinopse: ");
                    jogo.setSinopse(scan.nextLine());
                    System.out.print("1. Ativo / 2. Locado: ");
                    jogo.setAtivo(Integer.parseInt(scan.nextLine()));
                    jogoDAO.cadastrar(jogo);
                }
                case 3 -> {
                    System.out.println("- Atualizar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    exibir(jogoDAO, id);
                    jogoDAO.atualizar(id);
                    exibir(jogoDAO, id);
                }
                case 4 -> {
                    System.out.println("- Locar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    exibir(jogoDAO, id);
                    jogoDAO.ativo(id, 2);
                }
                case 5 -> {
                    System.out.println("- Devolver -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    exibir(jogoDAO, id);
                    jogoDAO.ativo(id, 1);
                }
                case 6 -> {
                    System.out.println("- Deletar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    jogoDAO.ativo(id, 0);
                }
                default -> System.out.println("Opcão inválida!\n");
            }
        } while(opcao != 0);
    }
}
