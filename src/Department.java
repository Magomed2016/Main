import java.math.BigDecimal;
import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> list;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getList() {
        if(list==null)
            list = new ArrayList<>();
        return list;
    }

    public void setList(ArrayList<Employee> list) {
        this.list = list;
    }

    public BigDecimal averageSalary(){

        BigDecimal averageSalary = new BigDecimal("0.00");
        for (Employee employee : list) {
            averageSalary = averageSalary.add(employee.getSalary());
        }
        return averageSalary.divide(new BigDecimal(list.size()),2,BigDecimal.ROUND_UP);

    }

    public void addEmployee(Employee employee){
        getList().add(employee);
    }

    public void printInfo(){
        System.out.println(name);
        for (Employee employee : list) {
            System.out.println("----------"+employee.getName());

        }
    }
}
