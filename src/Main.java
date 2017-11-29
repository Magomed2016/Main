import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        Set<String> departmentNames = new HashSet<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D://input.txt"))){
            String s;
            while((s=bufferedReader.readLine())!=null){
                String[] info = s.split(";");
                employees.add(new Employee(info[1].trim(),info[0].trim(),new BigDecimal(info[2].trim())));
                departmentNames.add(info[1].trim());


            }

        }catch (IOException e){
            e.printStackTrace();
        }

        for (String departmentName : departmentNames) {
            departments.add(new Department(departmentName));
        }

        for (Department department : departments) {

            department.list = new ArrayList<>();
            for (Employee employee : employees) {
                if(department.name.equals(employee.departmentName))
                    department.list.add(employee);

            }

        }

        for (Department departmentL : departments) {
            departmentL.printInfo();
            System.out.println(averageSalary(departmentL));
        }


        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D://output.txt"))){

            for (Department department : departments) {
                bufferedWriter.write(department.name+" средняя зарплата: \n "+averageSalary(department)+"\n");
                bufferedWriter.flush();

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BigDecimal averageSalary(Department d){
        BigDecimal b = new BigDecimal("0.00");
        for (Employee employee : d.list) {

             b = b.add(employee.Salary);
        }
        b=b.divide(new BigDecimal(d.list.size()),2);
        return b;
    }





}