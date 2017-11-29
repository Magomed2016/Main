import java.math.BigDecimal;

public class Employee {

    public Employee(String departmentName, String name, BigDecimal salary) {
        this.departmentName = departmentName;
        Name = name;
        Salary = salary;
    }

    String departmentName;
    String Name;
    BigDecimal Salary;

    public String printInfo(){
        return(Name+"; "+departmentName+"; "+Salary);
    }
}
