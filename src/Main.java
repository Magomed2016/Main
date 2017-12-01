import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Department> departments = null;

        writeFile(readFile(departments, args[0]),args[1]);

    }

    public static Set<Department> readFile(Set<Department> departmentSet, String url){

            if(departmentSet==null){
                departmentSet = new HashSet<>();
            }

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(url))){

                String string;
                while((string=bufferedReader.readLine())!=null) {
                    String[] info = string.split(";");
                    if(string.isEmpty()) continue;

                    if(info.length!=3 || info[0].trim().isEmpty() || info[1].trim().isEmpty() || info[2].trim().isEmpty()){
                        throw new IllegalArgumentException("Считываемые поля не должны быть пустыми");
                    }
                    if(info[2].trim().startsWith("-"))
                        throw  new IllegalArgumentException("Считана отрицательная зарплата");
                    Employee employee = new Employee(info[0].trim(),new BigDecimal(info[2].trim()));
                    departmentSet.add(new Department(info[1].trim()));
                    for (Department department : departmentSet) {
                        if(department.getName().equals(info[1].trim()))
                            department.addEmployee(employee);
                    }

                }

            }catch (FileNotFoundException e ){
                System.err.println("Неверный путь к файлу");

            }catch (IOException e){
                System.err.println("Не удаётся установить соединение");

            }catch (NumberFormatException e){
                System.err.println("Неверный формат данных");
            }catch (IllegalArgumentException e){

                System.err.println(e.getMessage());
            }
            return departmentSet;
    }

    public static void writeFile(Set<Department> departmentSet, String url){

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(url))){

                for (Department department : departmentSet) {
                    bufferedWriter.write(department.getName() +" средняя зарплата: "+ department.averageSalary() + "\r\n");
                }
                bufferedWriter.write(regroupingEmployees(departmentSet));

            }catch (FileNotFoundException e ){
                System.err.println("Некорректный путь к файлу");

            }catch (IOException e){
                System.err.println("Не удаётся установить соединение");

            }catch (NullPointerException e){
                System.err.println("Передано пустое множество");
            }


    }

    public static String regroupingEmployees(Set<Department> departmentSet){
        StringBuilder stringBuilder = new StringBuilder("");
        for (Department department : departmentSet) {
            for (Department department1 : departmentSet) {
                if(!department.equals(department1))
                    for (Employee employee : department.getEmployeeList()) {
                        if (department.averageSalary().compareTo(employee.getSalary()) == 1 && department1.averageSalary().compareTo(employee.getSalary()) == -1)
                            stringBuilder.append(employee.getName()+"----> "+ department1.getName()+"\r\n");

                    }

            }

        }
        return String.valueOf(stringBuilder);
    }

}