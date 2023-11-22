package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TESTE 1: Procura por Id =======");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);

		System.out.println("\n=== TESTE 2: Buscar todos =======");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

		/*System.out.println("\n=== TESTE 3: Inserir =======");
		Department newDepartment = new Department(null, "Musicas do ABBA");
		departmentDao.insert(newDepartment);
		System.out.println("Inserido com sucesso! Novo id: " + newDepartment.getId());*/

		System.out.println("\n=== TESTE 4: Atualizar =======");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Comida");
		departmentDao.update(dep2);
		System.out.println("Atualização completada com sucesso");

		System.out.println("\n=== TESTE 5: delete =======");
		System.out.print("Entre com o id para apagar: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Registro apagado com sucesso");

		sc.close();
	}
}
