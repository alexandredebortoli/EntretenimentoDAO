package view;

import dao.JogoDAO;
import dao.LivroDAO;
import model.Jogo;
import model.Livro;

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
    public static void exibir(LivroDAO livroDAO, int codigo) {
        for(Livro l : livroDAO.listar(codigo)) {
            if(codigo == 0) {
                System.out.println("Livro: " + l.getTitulo());
            } else {
                System.out.print("Livro: " + l.getTitulo());
                System.out.print(", Ano: " + l.getAno());
                System.out.print(", Categoria: " + l.getCategoria());
                System.out.println(", Sinopse: " + l.getSinopse());
                System.out.println(", Edicão: " + l.getEdicao());
            }
        }
    }
    public static void menu() {
        JogoDAO jogoDAO = new JogoDAO();
        LivroDAO livroDAO = new LivroDAO();
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
                    if(selJogo) {
                        exibir(jogoDAO, id);
                    } else {
                        exibir(livroDAO, id);
                    }
                }
                case 2 -> {
                    System.out.println("- Cadastrar -");

                    if(selJogo) {
                        Jogo jogo = new Jogo();
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
                    } else {
                        Livro livro = new Livro();
                        System.out.print("Nome: ");
                        livro.setTitulo(scan.nextLine());
                        System.out.print("Ano: ");
                        livro.setAno(Integer.parseInt(scan.nextLine()));
                        System.out.print("Categoria: ");
                        livro.setCategoria(scan.nextLine());
                        System.out.print("Sinopse: ");
                        livro.setSinopse(scan.nextLine());
                        System.out.print("Edicão: ");
                        livro.setEdicao(Integer.parseInt(scan.nextLine()));
                        System.out.print("1. Ativo / 2. Locado: ");
                        livro.setAtivo(Integer.parseInt(scan.nextLine()));
                        livroDAO.cadastrar(livro);
                    }
                }
                case 3 -> {
                    System.out.println("- Atualizar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    if(selJogo) {
                        exibir(jogoDAO, id);
                        jogoDAO.atualizar(id);
                        exibir(jogoDAO, id);
                    } else {
                        exibir(livroDAO, id);
                        livroDAO.atualizar(id);
                        exibir(livroDAO, id);
                    }
                }
                case 4 -> {
                    System.out.println("- Locar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    if(selJogo) {
                        exibir(jogoDAO, id);
                        jogoDAO.ativo(id, 2);
                    } else {
                        exibir(livroDAO, id);
                        livroDAO.ativo(id, 2);
                    }
                }
                case 5 -> {
                    System.out.println("- Devolver -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    if(selJogo) {
                        exibir(jogoDAO, id);
                        jogoDAO.ativo(id, 1);
                    } else {
                        exibir(livroDAO, id);
                        livroDAO.ativo(id, 1);
                    }
                }
                case 6 -> {
                    System.out.println("- Deletar -");
                    System.out.print("ID: ");
                    id = Integer.parseInt(scan.nextLine());
                    if(selJogo) {
                        exibir(jogoDAO, id);
                        jogoDAO.ativo(id, 0);
                    } else {
                        exibir(livroDAO, id);
                        livroDAO.ativo(id, 0);
                    }
                }
                default -> System.out.println("Opcão inválida!\n");
            }
        } while(opcao != 0);
    }
}
