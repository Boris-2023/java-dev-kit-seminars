import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Directory {
    private List<Employee> employeeList;

    public Directory() {
        this.employeeList = new ArrayList<>();
    }

    public void add(Employee employee) {
        employeeList.add(employee);
    }

    public List<Employee> findEmployeeByYearsOnService(int minYears, int maxYears) {
        if (minYears > maxYears) return null;
        return employeeList.stream()
                .filter(e -> e.getYearsOnService() >= minYears && e.getYearsOnService() <= maxYears)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByName(String name) {
        return employeeList.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Employee findEmployeeById(int id) {
        for (Employee e: employeeList) {
            if(e.getId()==id) return e;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (Employee e : employeeList) {
            list.append(e.toString());
        }
        return "" + list;
    }
}
