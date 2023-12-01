package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 1: Procura vendedor por Id ===");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println("===================================================");
		System.out.println("=== TESTE 2: Procura vendedor por departamento ===");
		Department departament = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(departament);
		for(Seller obj : list) {
			System.out.println(obj);
		}

/*		Scanner sc = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao(); // O programa não conhece a implementação só a interface.

		System.out.println("=== TESTE 1: Vendedor Procura por Id ===");

		Seller seller = sellerDao.findById(3);

		// System.out.println("DEPARTAMENTO");
		// System.out.println("------------");
		// System.out.println(dep.toString());

		System.out.println("======================================================");

		System.out.println("VENDEDOR");
		System.out.println("--------");
		System.out.println(seller.toString());

		System.out.println("======================================================");

		System.out.println("=== TESTE 2: Procura vendedor por Departamento ===");
		System.out.println("------------------------------------------------------");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);

		for (Seller obj : list) {
			System.out.println(obj);
			System.out.println("======================================================");
		}

		System.out.println("======================================================");

		System.out.println("=== TESTE 3: Procura todos vendedores ===");
		System.out.println("------------------------------------------------------");
		List<Seller> list2 = sellerDao.findAll();

		for (Seller obj : list2) {
			System.out.println(obj);
			System.out.println("======================================================");
		}

		System.out.println("\n=== TESTE 4: Insere vendedor =====");
		Seller newSeller = new Seller(null, "Nicolly Honorato David", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserido! Novo Id = " + newSeller.getId());

		System.out.println("======================================================");

		System.out.println("\n=== TESTE 5: Atualiza vendedor =====");
		seller = sellerDao.findById(1);
		seller.setName("Maria Silva");
		sellerDao.update(seller);
		System.out.println("Atualização completada com sucesso");

		System.out.println("======================================================");

		System.out.println("\n=== TESTE 6: Excluir vendedor =====");
		System.out.print("Informe o Id do vendedor: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Registro apagado com sucesso!");

		sc.close();    */
	}
}
