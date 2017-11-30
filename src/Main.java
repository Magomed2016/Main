import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        Set<String> departmentNames = new HashSet<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))){
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



        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))){

            for (Department department : departments) {
                bufferedWriter.write(department.name+" средняя зарплата: "+averageSalary(department)+"\r\n");
            }

            for (Department department : departments) {
                for (Department department1 : departments) {
                    if(!department.equals(department1)){
                        for (Employee employee : employees) {
                            if(averageSalary(department).compareTo(employee.Salary )==1&& averageSalary(department1).compareTo(employee.Salary )==-1)
                                bufferedWriter.write(employee.Name+"----> "+department1.name+"\r\n");

                        }
                    }
                }
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
        b=b.divide(new BigDecimal(d.list.size()),2, RoundingMode.UP);


        return b;
    }


}