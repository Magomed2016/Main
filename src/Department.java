import java.util.ArrayList;

public class Department {
    String name;
    ArrayList<Employee> list;

    public Department(String name) {
        this.name = name;
    }

    public void printInfo(){
        System.out.println(name);
        for (Employee employee : list) {
            System.out.println("----------"+employee.Name);

        }
    }
}
