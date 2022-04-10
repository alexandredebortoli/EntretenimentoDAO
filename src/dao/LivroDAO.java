package dao;

import conexao.ConexaoDB;
import model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivroDAO {
    public void cadastrar(Livro livro) {
        String QUERY = "INSERT INTO livro (titulo, ano, categoria, sinopse, edicao, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConexaoDB.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setInt(2, livro.getAno());
            preparedStatement.setString(3, livro.getCategoria());
            preparedStatement.setString(4, livro.getSinopse());
            preparedStatement.setInt(5, livro.getEdicao());
            preparedStatement.setInt(6, livro.getAtivo());

            preparedStatement.execute();
            System.out.println("Livro cadastrado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Livro> listar(int id) {
        List<Livro> livros = new ArrayList<Livro>();
        String QUERY;
        if(id == 0) {
            QUERY = "SELECT * FROM livro WHERE ativo != 0";
        }
        else {
            QUERY = "SELECT * FROM livro WHERE id = " + id + " AND ativo != 0";
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexaoDB.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);

            resultSet = preparedStatement.executeQuery(QUERY);

            while (resultSet.next()) {
                Livro livro = new Livro();

                livro.setId(resultSet.getInt("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAno(resultSet.getInt("ano"));
                livro.setCategoria(resultSet.getString("categoria"));
                livro.setSinopse(resultSet.getString("sinopse"));
                livro.setEdicao(resultSet.getInt("edicao"));
                livro.setAtivo(resultSet.getInt("ativo"));

                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return livros;
    }

    public void atualizar(int id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Nome\n2. Ano\n3. Categoria\n4. Sinopse\n5. Edição\n6. Tudo");
        System.out.print("Alterar: ");
        String QUERY = "";
        do {
            Livro livro = new Livro();
            switch (Integer.parseInt(scan.nextLine())) {
                case 1 -> {
                    System.out.print("Novo titulo: ");
                    livro.setTitulo(scan.nextLine());
                    QUERY = "UPDATE livro SET titulo = '" + livro.getTitulo() + "' WHERE id = " + id;
                }
                case 2 -> {
                    System.out.print("Novo ano: ");
                    livro.setAno(Integer.parseInt(scan.nextLine()));
                    QUERY = "UPDATE livro SET ano = " + livro.getAno() + " WHERE id = " + id;
                }
                case 3 -> {
                    System.out.print("Nova categoria: ");
                    livro.setCategoria(scan.nextLine());
                    QUERY = "UPDATE livro SET telefone = '" + livro.getCategoria() + "' WHERE id = " + id;
                }
                case 4 -> {
                    System.out.print("Nova sinopse: ");
                    livro.setSinopse(scan.nextLine());
                    QUERY = "UPDATE jogo SET dataNascimento = '" + livro.getSinopse() + "' WHERE id = " + id;
                }
                case 5 -> {
                    System.out.print("Nova edicão: ");
                    livro.setAno(Integer.parseInt(scan.nextLine()));
                    QUERY = "UPDATE livro SET edicao = " + livro.getEdicao() + " WHERE id = " + id;
                }
                case 6 -> {
                    System.out.print("Novo titulo: ");
                    livro.setTitulo(scan.nextLine());
                    System.out.print("Novo ano: ");
                    livro.setAno(Integer.parseInt(scan.nextLine()));
                    System.out.print("Novo categoria: ");
                    livro.setCategoria(scan.nextLine());
                    System.out.print("Nova sinopse: ");
                    livro.setSinopse(scan.nextLine());
                    System.out.print("Novo edicão: ");
                    livro.setEdicao(Integer.parseInt(scan.nextLine()));
                    QUERY = "UPDATE livro SET titulo = '" + livro.getTitulo()
                            + "', ano = " + livro.getAno()
                            + ", categoria = '" + livro.getCategoria()
                            + "', sinopse = '" + livro.getSinopse()
                            + "', edicao = " + livro.getEdicao()
                            + " WHERE id = " + id;
                }
                default -> System.out.println("Opcão inválida!");
            }
        } while(QUERY.equals(""));

        System.out.println("Alterando informacões...");
        Connection connection;
        try {
            connection = ConexaoDB.createConnectionMySQL();
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);

            System.out.println("\nAlterado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Conexão falhou!");
            e.printStackTrace();
        }
    }

    public void ativo(int id, int ativo) {
        String QUERY = "UPDATE livro SET ativo = " + ativo + " WHERE id = " + id;
        if(ativo == 1) {
            System.out.println("Devolvendo...");
        } else if(ativo == 2) {
            System.out.println("Locando...");
        } else {
            System.out.println("Deletando...");
        }
        Connection connection;
        try {
            connection = ConexaoDB.createConnectionMySQL();
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);

            if(ativo == 1) {
                System.out.println("\nDevolvido com sucesso!");
            } else if(ativo == 2) {
                System.out.println("\nLocado com sucesso!");
            } else {
                System.out.println("\nDeletado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Conexão falhou!");
            e.printStackTrace();
        }
    }
}
