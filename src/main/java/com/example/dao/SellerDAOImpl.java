package com.example.dao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.example.entities.Seller; // Certifique-se de importar a classe Seller corretamente
import com.example.entities.Department; // Certifique-se de importar a classe Department corretamente

public class SellerDAOImpl implements SellerDAO {
    private Connection conn;
    public SellerDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Seller> findAll() {
        List<Seller> sellers = new ArrayList<>(); // Lista para armazenar os vendedores encontrados
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT seller.*, department.name AS department_name FROM seller INNER JOIN department ON seller.department_id = department.id")) {
            while (rs.next()) {
                // Cria um novo objeto Seller para cada registro encontrado
                Seller seller = new Seller();
                seller.setId(rs.getInt("id"));
                seller.setName(rs.getString("name"));
                seller.setEmail(rs.getString("email"));
                seller.setBirthDate(rs.getDate("birth_date").toLocalDate());
                seller.setBaseSalary(rs.getDouble("base_salary"));
                // Cria um objeto Department para associar ao vendedor
                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("department_name"));
                // Associa o departamento ao vendedor
                seller.setDepartment(department);
                // Adiciona o vendedor encontrado à lista
                sellers.add(seller);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vendedores:");
            e.printStackTrace();
        }
        return sellers; // Retorna a lista de vendedores encontrados
    }
    @Override
    public Seller findById(Integer id) {
        // Implementação do método para encontrar um vendedor por ID no banco de dados
        return null; // Retorne o vendedor encontrado ou null se não encontrado
    }
    @Override
    public void insert(Seller seller) {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO seller (name, email, birth_date, base_salary, department_id) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, seller.getName());
            pstmt.setString(2, seller.getEmail());
            pstmt.setDate(3, Date.valueOf(seller.getBirthDate())); // Classe Date do java.sql para converter LocalDate
            pstmt.setDouble(4, seller.getBaseSalary());
            pstmt.setInt(5, seller.getDepartment().getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Vendedor inserido com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vendedor:");
            e.printStackTrace();
        }
    }
    @Override
    public void update(Seller seller) {
        // Implementação do método para atualizar um vendedor no banco de dados
    }
    @Override
    public void deleteById(Integer id) {
        // Implementação do método para excluir um vendedor do banco de dados por ID
    }
}