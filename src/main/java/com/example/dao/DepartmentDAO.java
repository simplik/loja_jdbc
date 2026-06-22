package com.example.dao;
import java.util.List;
import com.example.entities.Department; // Certifique-se de importar a classe Department corretamente

public interface DepartmentDAO {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
