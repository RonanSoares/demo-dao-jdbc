package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

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

		System.out.println("\n=== TEST 4: seller insert =====");
		Seller newSeller = new Seller(null, "Nicolly Honorato David", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserido! Novo Id = " + newSeller.getId());
		
			System.out.println("======================================================");
		
	}
}
