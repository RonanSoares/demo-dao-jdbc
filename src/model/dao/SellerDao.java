package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
	void insert(Seller dep);     // Método para Inserir no BD o objeto Seller
	void update(Seller dep);     // Método para atualizar no BD o objeto Seller
	void deleteById(Integer id); // Método para deletar um id
	Seller findById(Integer id); // método para consultar um Id no BD. Se encontrar retorna. senão retorna nulo
	List<Seller> findAll();      // Operação para retornar uma lista com todos os vendedores.
}
