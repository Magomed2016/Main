import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employeeList;

    public Department(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployeeList() {
        if(employeeList ==null)
            employeeList = new ArrayList<>();
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public BigDecimal averageSalary(){

        BigDecimal averageSalary = new BigDecimal("0.00");
        for (Employee employee : employeeList) {
            averageSalary = averageSalary.add(employee.getSalary());
        }
        return averageSalary.divide(new BigDecimal(getEmployeeList().size()),2, RoundingMode.HALF_UP);

    }

    public void addEmployee(Employee employee){
        getEmployeeList().add(employee);
    }

    public void printInfo(){
        System.out.println(name);
        for (Employee employee : employeeList) {
            System.out.println("----------"+employee.getName());

        }
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Department department = (Department)obj;
        if(getName().equals(department.getName()))
            return true;

        else return super.equals(obj);
    }
}
