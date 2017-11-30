import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> employees = null;
//        ArrayList<Department> departments = new ArrayList<>();
//        Set<String> departmentNames = new HashSet<>();
        Map<String, Department> departmentsMap = null;
        ArrayList<Object> objectArrayList = null;
//        BufferedReader bufferedReader = null;
//        BufferedWriter bufferedWriter = null;

        objectArrayList = readFile(args[0],departmentsMap,employees);
        employees = (ArrayList<Employee>) objectArrayList.get(0);
        departmentsMap = (Map<String, Department>) objectArrayList.get(1);
        writeFile(args[1],departmentsMap,employees);


    }

    public static ArrayList<Object> readFile(String str, Map<String,Department> departmentsMap, ArrayList<Employee> employees){
            ArrayList<Object> listObject = new ArrayList<>();
            try (BufferedReader bufferedReader =new BufferedReader(new FileReader(str))){

                String s;
                while((s=bufferedReader.readLine())!=null){
                    String[] info = s.split(";");
                    //employees.add(new Employee(info[0].trim(),new BigDecimal(info[2].trim())));
                    employees = appendEmployee(employees,info[0].trim(),info[2].trim());
                    departmentsMap = appendDepartment(departmentsMap,employees,info[1].trim());

                }


            } catch(FileNotFoundException e){
                System.err.println("Не удаётся найти указанный файл");
            } catch(IOException e){
                System.err.println("Не удаётся установить соединение");
            }

        listObject.add(employees);
        listObject.add(departmentsMap);

        return listObject;



    }

    public static void writeFile(String str,Map<String,Department> departmentsMap, ArrayList<Employee> employees){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str))) {

            //записываем инф. о средней зарплате
            for (Department department : departmentsMap.values()) {
                bufferedWriter.write(department.getName() + " средняя зарплата: " + department.averageSalary() + "\r\n");
            }

            //.........записываем варианты распределения сотрудников по отделам
            //?????
            for (Department department : departmentsMap.values()) {
                for (Department department1 : departmentsMap.values()) {
                    if (!department.equals(department1)) {
                        for (Employee employee : employees) {
                            if (department.averageSalary().compareTo(employee.getSalary()) == 1 && department1.averageSalary().compareTo(employee.getSalary()) == -1)
                                bufferedWriter.write(employee.getName() + "----> " + department1.getName() + "\r\n");

                        }
                    }
                }
            }
        }catch (FileNotFoundException e){
            System.err.println("Не удаётся найти указанный файл");
        }catch (IOException e){
            System.err.println("Не удаётся установить соединение");
        }catch (NullPointerException e){
            System.err.println("Передано пустое множество");
        }



    }

    public static ArrayList<Employee> appendEmployee(ArrayList<Employee> list, String name, String salary){
        if(list==null)
            list=new ArrayList<>();
        try {
            list.add(new Employee(name, new BigDecimal(salary)));
        }catch (NumberFormatException e){
            System.err.println("Указан не норректный формат данных");
        }
        return list;


    }

    public  static Map<String,Department> appendDepartment(Map<String,Department> map, ArrayList<Employee> list, String name){
        if(map==null)
            map = new HashMap<>();
        if(!map.containsKey(name)){
            map.put(name,new Department(name));
            //????
            try {
                map.get(name).addEmployee(list.get(list.size() - 1));
            }catch (NullPointerException e){
                System.err.println("Передан пустой список");
            }
        }else map.get(name).addEmployee(list.get(list.size()-1));


        return map;

    }

}