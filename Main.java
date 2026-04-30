

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database.createTable();

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();
        service.loadFromFile();

        while (true) {
            System.out.println("\n1. Add");
            System.out.println("2. View");
            System.out.println("3. Delete");
            System.out.println("4. Update");
            System.out.println("5. Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Dept: ");
                    String dept = sc.nextLine();

                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();

                    service.addEmployee(new Employee(id, name, dept, sal));
                    break;

                case 2:
                    service.viewEmployees();
                    break;

                case 3:
                    System.out.print("ID: ");
                    service.deleteEmployee(sc.nextInt());
                    break;

                case 4:
                    System.out.print("ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String n = sc.nextLine();

                    System.out.print("New Dept: ");
                    String d = sc.nextLine();

                    System.out.print("New Salary: ");
                    double s = sc.nextDouble();

                    service.updateEmployee(uid, n, d, s);
                    break;

                case 5:
                    System.out.println("Bye!");
                    return;
            }
        }
    }
}