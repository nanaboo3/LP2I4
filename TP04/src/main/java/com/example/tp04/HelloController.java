package com.example.tp04;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {
    @FXML
    private TextField pesquisarField;
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField salTextField;
    @FXML
    private TextField cargoTextField;

    private String url = "jdbc:sqlserver://DESKTOP-GE601NN; instance=MSSQLSERVER;databaseName=aulajava;encrypt=false;integratedSecurity=true;";
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet funcRS;
    private ResultSet cargoRS;
    private String query;
    private String nome;
    private double salario;
    private String cargo;

    @FXML
    protected void pesquisarClick()  {
        getDbData();
        setData();
        populate(nome, String.valueOf(salario), cargo);
    }

    @FXML
    protected void antClick() {
        try {
            while (funcRS.previous()) {
                setData();
                populate(nome, String.valueOf(salario), cargo);
                return;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void proxClick() {
        try {
            while (funcRS.next()) {
                setData();
                populate(nome, String.valueOf(salario), cargo);
                return;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void populate(String nome, String salario, String cargo) {
        nomeTextField.setText(nome);
        salTextField.setText(salario);
        cargoTextField.setText(cargo);
    }

    private void setData()  {
        try {
            nome = funcRS.getString("nome_func");
            salario = funcRS.getDouble("sal_func");
            query = "select * from tbcargos where cd_cargo = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, funcRS.getString("cod_cargo"));
            cargoRS = stmt.executeQuery();
            while (cargoRS.next()) {
                cargo = cargoRS.getString("ds_cargo");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getDbData(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            query = "select * from tbfuncs where nome_func like ?";
            stmt = con.prepareStatement(query, funcRS.TYPE_SCROLL_SENSITIVE, funcRS.CONCUR_UPDATABLE);
            stmt.setString(1, pesquisarField.getText() + '%');
            funcRS = stmt.executeQuery();
            if (funcRS != null) {
                while (funcRS.next()) {
                    return;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}