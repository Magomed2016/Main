import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    static StringBuilder solutions;
   // static ArrayList<Employee> combinationOfEmployees;
    //static Set<Department> departments;

    public static void main(String[] args) {

        Set<Department> departments = null;

       writeFile(readFile(departments,args[0]),args[1]);

    }


////////ЧТЕНИЕ ИЗ ФАЙЛА....

    public static Set<Department> readFile(Set<Department> departments, String url){

            if(departments==null){
                departments = new HashSet<>();
            }

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(url))){

                StringBuilder InfoAboutNegativeSalary = new StringBuilder("");

                String string;
                while((string=bufferedReader.readLine())!=null) {
                    String[] info = string.split(";");
                    if(string.isEmpty()) continue;

                    if(info.length!=3 || info[0].trim().isEmpty() || info[1].trim().isEmpty() || info[2].trim().isEmpty()){
                        throw new IllegalArgumentException("Считываемые поля не должны быть пустыми");
                    }
                    if(info[2].trim().startsWith("-")) {
                        InfoAboutNegativeSalary.append("У сотрудника " + info[0].trim() + " считана отрицательна зарплата" + "\r\n");
                        continue;
                    }
                    Employee employee = new Employee(info[0].trim(),new BigDecimal(info[2].trim()));
                    departments.add(new Department(info[1].trim()));
                    for (Department department : departments) {
                        if(department.getName().equals(info[1].trim()))
                            department.addEmployee(employee);
                    }

                }
                if (!String.valueOf(InfoAboutNegativeSalary).equals(""))
                    throw  new IllegalArgumentException(String.valueOf(InfoAboutNegativeSalary));

            }catch (FileNotFoundException e ){
                System.err.println("Неверный путь к файлу");

            }catch (IOException e){
                System.err.println("Не удаётся установить соединение");

            }catch (NumberFormatException e){
                System.err.println("Неверный формат данных");
            }catch (IllegalArgumentException e){

                System.err.println(e.getMessage());
            }
            return departments;
    }

//////ЗАПИСЬ В ФАЙЛ......

    public static void writeFile(Set<Department> departments, String url){

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(url))){

                for (Department department : departments) {
                    bufferedWriter.write(department.getName() +" средняя зарплата: "+ department.averageSalary() + "\r\n");
                }
                bufferedWriter.write(regroupingEmployees(departments));

            }catch (FileNotFoundException e ){
                System.err.println("Некорректный путь к файлу");

            }catch (IOException e){
                System.err.println("Не удаётся установить соединение");

            }catch (NullPointerException e){
                System.err.println("Передано пустое множество");
                e.printStackTrace();
            }


    }

///////ПЕРЕСТАНОВКА.......

    public static String regroupingEmployees(Set<Department> departments){

      //  StringBuilder solutions = null;

        ArrayList<Employee> combinationOfEmployees=null;

        for (Department department : departments) {
            for (int i = 1; i < department.getEmployeeList().size() ; i++) {
                algoritm(departments,department,combinationOfEmployees,i,0);
            }
        }
        return String.valueOf(solutions);
    }

    ////////ГЕНЕРИРОВАНИЕ КОМБАНАЦЙ......

    public static void algoritm(Set<Department> departments,Department department, ArrayList<Employee> employeeArrayList, int level, int nextPosition){


        if (employeeArrayList ==null)
            employeeArrayList = new ArrayList<>();


        if(employeeArrayList.size()==level){
            for (Employee combinationOfEmployee : employeeArrayList) {
                System.out.print(combinationOfEmployee.getName());
            }
            System.out.println("");
            chekPermunation(departments, department, employeeArrayList);
          //  employeeArrayList.remove(employeeArrayList.size()-1);
            return;
        }


        for(int j= department.getEmployeeList().indexOf(department.getEmployeeList().get(nextPosition)); j<department.getEmployeeList().size(); j++){
            if(!employeeArrayList.contains(department.getEmployeeList().get(j))){
                employeeArrayList.add(department.getEmployeeList().get(j));
                algoritm(departments,department,employeeArrayList,level,nextPosition);
                employeeArrayList.remove(department.getEmployeeList().get(j));
            }
            nextPosition++;
        }


    }

//////ПРОВЕРКА НА ВОЗМОЖНОСТЬ ПЕРСТАНОВКИ.....

    public static String chekPermunation(Set<Department> departments, Department department, ArrayList<Employee> combinationOfEmployees){

        if(solutions==null)
            solutions = new StringBuilder();
        BigDecimal budget = new BigDecimal("0.00");
        BigDecimal zero = new BigDecimal("0.00");
        for (Employee employee : combinationOfEmployees) {
            budget = budget.add(employee.getSalary());
        }
        for (Department department1 : departments) {

            if(!department.equals(department1)){

                //System.out.println((((department.getBudget().add(budget.multiply(new BigDecimal("-1.00")))).divide(new BigDecimal(String.valueOf(department.getEmployeeList().size()- combinationOfEmployees.size())+".00"),RoundingMode.UP)).compareTo(department.averageSalary())==1) +" "+ ((department1.getBudget().add(budget)).divide(new BigDecimal(String.valueOf(department1.getEmployeeList().size()+ combinationOfEmployees.size())+".00"),RoundingMode.UP).compareTo(department1.averageSalary())==1));
                if ((((department.getBudget().add(budget.multiply(new BigDecimal("-1.00")))).divide(new BigDecimal(String.valueOf(department.getEmployeeList().size()- combinationOfEmployees.size())+".00"),RoundingMode.UP)).compareTo(department.averageSalary())==1) && ((department1.getBudget().add(budget)).divide(new BigDecimal(String.valueOf(department1.getEmployeeList().size()+ combinationOfEmployees.size())+".00"),RoundingMode.UP).compareTo(department1.averageSalary())==1)){

                    for (Employee employee : combinationOfEmployees) {
                        solutions.append(employee.getName() + " ");
                    }
                    solutions.append(" " + department.getName() + "----->" + department1.getName() + "\r\n");
                }

            }
        }

        return String.valueOf(solutions);

    }



}