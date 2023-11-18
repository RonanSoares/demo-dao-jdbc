package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	void insert(DepartmentDao dep);     // Método para Inserir no BD o objeto DepartmentDao
	void update(DepartmentDao dep);     // Método para atualizar no BD o objeto DepartmentDao
	void deleteById(Integer id);        // Método para deletar um id
	Department findById(Integer id);    // método para consultar um Id no BD. Se encontrar retorna. senão retorna nulo
	List<Department> findAll();         // Operação para retornar uma lista com todos os departamentos.
}
