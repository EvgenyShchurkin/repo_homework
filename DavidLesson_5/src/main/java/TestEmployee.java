public class TestEmployee {
    public static void main(String[] args) {
        Employee[] staff = new Employee[5];
        staff[0]= new Employee("Иванов Иван","Слесарь","test@test.com",1001,10000,50);
        staff[1]=new Employee();
        staff[2]=new Employee("Петров Иван","Дворник","test1@test1.com",1002,10500,45);
        staff[3]=new Employee("Сидоров Петр","Плотник","test2@test2.com",1010,11000,51);
        staff[4]=new Employee("Смирнов Сергей","Начальник отдела","test3@test3.com",777,50000,25);
        for (Employee person: staff){
            if(person.getAge()>40)
                person.info();
        }
    }
}
