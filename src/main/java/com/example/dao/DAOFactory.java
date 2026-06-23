package com.example.dao;
import com.example.db.ConnectionFactory; // Certifique-se de importar a classe ConnectionFactory corretamente
public class DAOFactory {
    public static DepartmentDAO createDepartmentDAO() {
        return new DepartmentDAOImpl(ConnectionFactory.getConnection());
    }
    public static SellerDAO createSellerDAO() {
        return new SellerDAOImpl(ConnectionFactory.getConnection());
    }
}
