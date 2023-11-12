package model.dao;

import java.util.List;

public interface Department {
	
	void insert(Department dep);     // Método para Inserir no BD o objeto Department
	void update(Department dep);     // Método para atualizar no BD o objeto Department
	void deleteById(Integer id);     // Método para deletar um id
	Department findById(Integer id); // método para consultar um Id no BD. Se encontrar retorna. senão retorna nulo
	List<Department> findAll();      // Operação para retornar uma lista com todos os departamentos.
}
