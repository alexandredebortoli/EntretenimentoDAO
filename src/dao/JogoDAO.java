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

    public List<Jogo> listar(int codigo) {
        List<Jogo> jogos = new ArrayList<Jogo>();
        String QUERY;
        if(codigo == 0) {
            QUERY = "SELECT * FROM jogo WHERE ativo != 0";
        }
        else {
            QUERY = "SELECT * FROM jogo WHERE id = " + codigo + " AND ativo != 0";
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

    public void atualizar(int codigo) {
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
                    QUERY = "UPDATE jogo SET titulo = '" + jogo.getTitulo() + "' WHERE codigo = " + codigo;
                }
                case 2 -> {
                    System.out.print("Novo ano: ");
                    jogo.setAno(Integer.parseInt(scan.nextLine()));
                    QUERY = "UPDATE jogo SET ano = " + jogo.getAno() + " WHERE codigo = " + codigo;
                }
                case 3 -> {
                    System.out.print("Nova categoria: ");
                    categoria = scan.nextLine();
                    QUERY = "UPDATE contato SET telefone = '" + categoria + "' WHERE codigo = " + codigo;
                }
                case 4 -> {
                    System.out.print("Nova sinopse: ");
                    sinopse = scan.nextLine();
                    QUERY = "UPDATE contato SET dataNascimento = '" + sinopse + "' WHERE codigo = " + codigo;
                }
                case 5 -> {
                    System.out.print("Novo titulo: ");
                    titulo = scan.nextLine();
                    System.out.print("Novo ano: ");
                    ano = scan.nextLine();
                    System.out.print("Novo categoria: ");
                    categoria = scan.nextLine();
                    System.out.print("Nova data de nascimento (dd/mm/ano): ");
                    sinopse = scan.nextLine();
                    QUERY = "UPDATE contato SET nome = '" + titulo
                            + "', email = '" + ano
                            + "', telefone = '" + categoria
                            + "', dataNascimento = '" + sinopse + "' WHERE codigo = " + codigo;
                }
                default -> System.out.println("Opcão inválida!");
            }
        } while(QUERY.equals(""));

        System.out.println("Alterando informacões...");
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);

            System.out.println("\nAlterado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Conexão falhou!");
        }
    }
}
