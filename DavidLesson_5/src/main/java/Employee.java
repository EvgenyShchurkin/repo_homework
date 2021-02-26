public class Employee {
    private String name;
    private String position;
    private String email;
    private long phone;
    private double salary;
    private int age;

    public Employee() {
        this.name = "no_name";
        this.position = "unemployed";
        this.email = "empty";
        this.phone = -1;
        this.salary = 0;
        this.age = 18;
    }

    public Employee(String name, String position, String email, long phone, double salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void info(){
        System.out.println("Имя: "+ name+" должность: "+position+" email:"+ email+" телефон: "+ phone+ " зарплата: "
        + salary+" возраст: "+age);
    }
    public int getAge(){
        return age;
    }

}
