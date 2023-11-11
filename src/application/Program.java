package application;

import model.entities.Departament;

public class Program {

	public static void main(String[] args) {
		
		Departament obj = new Departament(1, "Livros");
		
		System.out.println("DEPARTAMENTO");
		System.out.println("------------");
		System.out.println(obj.toString());
		
		
		

	}

}
