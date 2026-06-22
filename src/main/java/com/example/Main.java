package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.entities.Department;
import com.example.entities.Seller;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/loja_jdbc";
        String usuario = "postgres";
        String senha = "jhonny27112005";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }

        Department d1 = new Department(1, "Vendas");
        Seller s1 = new Seller(1, "João Silva", "joao.silva@email.com", LocalDate.of(1990, 5, 15), 5000.0, d1);

        System.out.println("Departamento: " + s1.getDepartment().getName());
        System.out.printf("Vendedor: %s, Email: %s%nData de Nascimento: %s, Salário Base: %.2f \n", s1.getName(), s1.getEmail(), s1.getBirthDate(), s1.getBaseSalary());   
    }
}