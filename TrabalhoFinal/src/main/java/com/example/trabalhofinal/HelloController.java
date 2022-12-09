package com.example.trabalhofinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class HelloController {
    private String nome, objetivo, query;
    private int idade;
    private double peso, altura;
    private String url = "jdbc:sqlserver://DESKTOP-GE601NN;instance=MSSQLSERVER;databaseName=academia;encrypt=false;integratedSecurity=true;";
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @FXML
    private TextField nomeField;
    @FXML
    private TextField idadeField;
    @FXML
    private TextField pesoField;
    @FXML
    private TextField altField;
    @FXML
    private TextField objField;
    @FXML
    private Button addBtn;
    @FXML
    private Button limparBtn;
    @FXML
    private Button dadosBtn;
    @FXML
    private Button sairBtn;

    @FXML
    public void limpar() {
        nomeField.setText("");
        idadeField.setText("");
        pesoField.setText("");
        altField.setText("");
        objField.setText("");
    }

    @FXML
    public void sair() {
        System.exit(0);
    }

    @FXML public void salvar() {
        query = "insert into aluno (nome, idade, peso, altura, objetivo) values(?, ?, ?, ?, ?)";

        nome = nomeField.getText();
        idade = Integer.parseInt(idadeField.getText());
        peso = Double.parseDouble(pesoField.getText());
        altura = Double.parseDouble(altField.getText());
        objetivo = objField.getText();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            System.out.println("Conexão OK");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, nome);
            pstmt.setInt(2,idade);
            pstmt.setDouble(3,peso);
            pstmt.setDouble(4,altura);
            pstmt.setString(5,objetivo);
            limpar();
            pstmt.executeQuery();
            con.close();
            pstmt.close();
            rs.close();
        } catch (Exception e) {
        }
    }

    @FXML
    public void mostrar() {
        Gson gson = new Gson();
        query = "select * from aluno";
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("DADOS DOS ALUNOS");
        ButtonType ok = new ButtonType("Sair", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(ok);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            System.out.println("Conexão OK");
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            List<String> alunosJSON = new ArrayList<String>();

            while (rs.next()) {
                nome = rs.getString("nome");
                idade = Integer.parseInt(rs.getString("idade"));
                altura = Double.parseDouble(rs.getString("altura"));
                peso = Double.parseDouble(rs.getString("peso"));
                objetivo = rs.getString("objetivo");
                int id = Integer.parseInt(rs.getString("ID"));
                Aluno aluno = new Aluno(rs.getString("nome"), objetivo, idade, id, altura, peso);
                alunosJSON.add(gson.toJson(aluno));
            }

            dialog.setContentText(alunosJSON.toString());

            con.close();
            pstmt.close();
            rs.close();
        } catch (Exception e) {
        }
        dialog.showAndWait();
    }
}