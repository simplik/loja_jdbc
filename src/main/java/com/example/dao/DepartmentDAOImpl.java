package com.example.dao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.example.entities.Department; // Certifique-se de importar a classe Department corretamente

public class DepartmentDAOImpl implements DepartmentDAO {
    private Connection conn;
    public DepartmentDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>(); // Lista para armazenar os departamentos encontrados
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM department")) {
            // next() avança para o próximo registro e retorna true enquanto houver registros para ler no ResultSet
            while (rs.next()) {
                // Cria um novo objeto Department para cada registro encontrado
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                // Adiciona o departamento encontrado à lista
                departments.add(department);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar departamentos:");
            e.printStackTrace();
        }
        return departments; // Retorna a lista de departamentos encontrados
    }
    @Override
    public void insert(Department department) {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department (name) VALUES (?)")) {
            // Define o valor do parâmetro para o nome do departamento
            // O 1 indica a posição do parâmetro na consulta SQL (o primeiro '?')
            pstmt.setString(1, department.getName());
            // Executa a consulta de inserção e retorna o número de linhas afetadas
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Departamento inserido com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir departamento:");
            e.printStackTrace();
        }
    }
    @Override
public Department findById(Integer id) {
    try (PreparedStatement pstmt = conn.prepareStatement(
            "SELECT * FROM department WHERE id = ?")) {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                return department;
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar departamento por ID:");
        e.printStackTrace();
    }
    return null;
}
@Override
public void update(Department department) {
    try (PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE department SET name = ? WHERE id = ?")) {
        pstmt.setString(1, department.getName());
        pstmt.setInt(2, department.getId());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Departamento atualizado com sucesso!");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar departamento:");
        e.printStackTrace();
    }
}
@Override
public void deleteById(Integer id) {
    try (PreparedStatement pstmt = conn.prepareStatement(
            "DELETE FROM department WHERE id = ?")) {
        pstmt.setInt(1, id);
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Departamento excluído com sucesso!");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao excluir departamento:");
        e.printStackTrace();
    }
}
}