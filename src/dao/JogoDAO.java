package dao;

import conexao.ConexaoDB;
import model.Jogo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoDAO {
    public void cadastrar(Jogo jogo) {
        String QUERY = "INSERT INTO jogo (titulo, ano, categoria, sinopse, ativo) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConexaoDB.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.setString(1, jogo.getTitulo());
            preparedStatement.setInt(2, jogo.getAno());
            preparedStatement.setString(3, jogo.getCategoria());
            preparedStatement.setString(4, jogo.getSinopse());
            preparedStatement.setInt(5, jogo.getAtivo());

            preparedStatement.execute();
            System.out.println("Jogo cadastrado com sucesso");

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

    public List<Jogo> listar(int id) {
        List<Jogo> jogos = new ArrayList<Jogo>();
        String QUERY;
        if(id == 0) {
            QUERY = "SELECT * FROM jogo WHERE ativo != 0";
        }
        else {
            QUERY = "SELECT * FROM jogo WHERE id = " + id + " AND ativo != 0";
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexaoDB.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);

            resultSet = preparedStatement.executeQuery(QUERY);

            while (resultSet.next()) {
                Jogo jogo = new Jogo();

                jogo.setId(resultSet.getInt("id"));
                jogo.setTitulo(resultSet.getString("titulo"));
                jogo.setAno(resultSet.getInt("ano"));
                jogo.setCategoria(resultSet.getString("categoria"));
                jogo.setSinopse(resultSet.getString("sinopse"));
                jogo.setAtivo(resultSet.getInt("ativo"));

                jogos.add(jogo);
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
        return jogos;
    }

    public void atualizar(int id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Nome\n2. Ano\n3. Categoria\n4. Sinopse\n5. Tudo");
        System.out.print("Alterar: ");
        String QUERY = "";
        //String titulo, ano, categoria, sinopse;
        do {
            Jogo jogo = new Jogo();
            switch (Integer.parseInt(scan.nextLine())) {
                case 1 -> {
                    System.out.print("Novo titulo: ");
                    jogo.setTitulo(scan.nextLine());
                    QUERY = "UPDATE jogo SET titulo = '" + jogo.getTitulo() + "' WHERE id = " + id;
                }
                case 2 -> {
                    System.out.print("Novo ano: ");
                    jogo.setAno(Integer.parseInt(scan.nextLine()));
                    QUERY = "UPDATE jogo SET ano = " + jogo.getAno() + " WHERE id = " + id;
                }
                case 3 -> {
                    System.out.print("Nova categoria: ");
                    jogo.setCategoria(scan.nextLine());
                    QUERY = "UPDATE jogo SET telefone = '" + jogo.getCategoria() + "' WHERE id = " + id;
                }
                case 4 -> {
                    System.out.print("Nova sinopse: ");
                    jogo.setSinopse(scan.nextLine());
                    QUERY = "UPDATE jogo SET dataNascimento = '" + jogo.getSinopse() + "' WHERE id = " + id;
                }
                case 5 -> {
                    System.out.print("Novo titulo: ");
                    jogo.setTitulo(scan.nextLine());
                    System.out.print("Novo ano: ");
                    jogo.setAno(Integer.parseInt(scan.nextLine()));
                    System.out.print("Novo categoria: ");
                    jogo.setCategoria(scan.nextLine());
                    System.out.print("Nova data de nascimento (dd/mm/ano): ");
                    jogo.setSinopse(scan.nextLine());
                    QUERY = "UPDATE jogo SET titulo = '" + jogo.getTitulo()
                            + "', ano = " + jogo.getAno()
                            + ", categoria = '" + jogo.getCategoria()
                            + "', sinopse = '" + jogo.getSinopse() + "' WHERE id = " + id;
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
        String QUERY = "UPDATE jogo SET ativo = " + ativo + " WHERE id = " + id;
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
