import java.time.LocalDate;

public class Employee {
    private static int eId = 0;
    private String phone, name;
    private LocalDate dateOfEmployment;
    private int id;

    public Employee(String name, String phone, LocalDate dateOfEmployment) {
        this.phone = phone;
        this.name = name;
        this.dateOfEmployment = dateOfEmployment;
        id = ++eId;
    }


    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOnService() {
        return LocalDate.now().compareTo(dateOfEmployment);
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }
    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    @Override
    public String toString() {
        return "\nEmployee ID " + getId() + ": {" +
                "name = '" + name + '\'' +
                ", phone ='" + phone + '\'' +
                ", dateOfEmployment = " + dateOfEmployment +
                ", yearsOnService = " + getYearsOnService() +
                "}";
    }
}
