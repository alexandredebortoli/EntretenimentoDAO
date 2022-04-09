package controller;

import conexao.ConexaoDB;
import dao.JogoDAO;
import model.Jogo;
import view.JogoView;

import java.sql.*;
import static view.JogoView.exibir;
import static view.JogoView.menu;

import java.util.Scanner;

public class JogoController {
    public static void iniciar() throws SQLException {
        Connection connection = ConexaoDB.createConnectionMySQL();
        if(connection != null) {
            System.out.println("Conexão realizada com sucesso");
            connection.close();
        }

        menu();
    }
}
