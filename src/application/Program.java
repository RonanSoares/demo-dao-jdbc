package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dep = new Department(1, "Livros");
		Seller seller = new Seller(21, "Ronan", "ronanps@gmail.com", new Date(), 5000.0, dep);
		SellerDao sellerDao = DaoFactory.createSellerDao(); //O programa não conhece a implementação só a interface.
		
		
		System.out.println("DEPARTAMENTO");
		System.out.println("------------");
		System.out.println(dep.toString());
		
		System.out.println("=======================================");
		
		System.out.println("VENDEDOR");
		System.out.println("--------");
		System.out.println(seller.toString());		

	}
}
