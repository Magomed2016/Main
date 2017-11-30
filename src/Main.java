import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();
//        ArrayList<Department> departments = new ArrayList<>();
//        Set<String> departmentNames = new HashSet<>();
        Map<String, Department> departmentsMap = new HashMap<>();



//.....чтение из файла
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]+"2"))){
            String s;
            while((s=bufferedReader.readLine())!=null){
                String[] info = s.split(";");
                employees.add(new Employee(info[0].trim(),new BigDecimal(info[2].trim())));
                if(!departmentsMap.containsKey(info[1].trim())){
                    departmentsMap.put(info[1].trim(),new Department(info[1].trim()));
                    //????
                    departmentsMap.get(info[1].trim()).addEmployee(employees.get(employees.size()-1));

                }else departmentsMap.get(info[1].trim()).addEmployee(employees.get(employees.size()-1));
            }

        } catch (FileNotFoundException e){
            System.err.println("Не удаётся найти указанный файл");
        } catch (IOException e) {
            System.err.println("Не удаётся установить соединение с файлом");
        }


//.....запись в файл
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))){

            //записываем инф. о средней зарплате
            for (Department department : departmentsMap.values()) {
                bufferedWriter.write(department.getName()+" средняя зарплата: "+department.averageSalary()+"\r\n");
            }

//.........записываем варианты распределения сотрудников по отделам
            //?????
            for (Department department : departmentsMap.values()) {
                for (Department department1 : departmentsMap.values()) {
                    if(!department.equals(department1)){
                        for (Employee employee : employees) {
                            if(department.averageSalary().compareTo(employee.getSalary() )==1&& department1.averageSalary().compareTo(employee.getSalary() )==-1)
                                bufferedWriter.write(employee.getName()+"----> "+department1.getName()+"\r\n");

                        }
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }



    }

}