

import java.util.*;
import java.io.*;
import java.sql.*;

public class EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                employees.add(new Employee(
                        Integer.parseInt(d[0]),
                        d[1],
                        d[2],
                        Double.parseDouble(d[3])
                ));
            }
        } catch (Exception e) {
            System.out.println("No file data.");
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter("employees.txt")) {
            for (Employee e : employees) {
                pw.println(e.id + "," + e.name + "," + e.department + "," + e.salary);
            }
        } catch (Exception e) {
            System.out.println("File save error.");
        }
    }

    

    public void addEmployee(Employee e) {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO employees VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.id);
            ps.setString(2, e.name);
            ps.setString(3, e.department);
            ps.setDouble(4, e.salary);
            ps.executeUpdate();

            employees.add(e);
            saveToFile();
            System.out.println("Employee added!");

        } catch (Exception ex) {
            System.out.println("DB Insert Error: " + ex.getMessage());
        }
    }

    public void viewEmployees() {
        try (Connection conn = Database.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("department") + " | " +
                        rs.getDouble("salary")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection conn = Database.connect()) {
            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            employees.removeIf(emp -> emp.id == id);
            saveToFile();

            System.out.println("Deleted!");

        } catch (Exception e) {
            System.out.println("Delete Error.");
        }
    }

    public void updateEmployee(int id, String name, String dept, double salary) {
        try (Connection conn = Database.connect()) {
            String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setInt(4, id);
            ps.executeUpdate();

            saveToFile();
            System.out.println("Updated!");

        } catch (Exception e) {
            System.out.println("Update Error.");
        }
    }
}