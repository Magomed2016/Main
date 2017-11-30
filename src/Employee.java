import java.math.BigDecimal;

public class Employee {


    private String Name;
    private BigDecimal Salary;

    public Employee(String name, BigDecimal salary) {
        this.Name = name;
        this.Salary = salary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getSalary() {
        return Salary;
    }

    public void setSalary(BigDecimal salary) {
        Salary = salary;
    }

    public String printInfo(){
        return(Name+"; "+"; "+Salary);
    }
}
