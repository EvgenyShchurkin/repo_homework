public class TestAnimal {
    public static void main(String[] args) {
        Animal []a = new Animal[5];
        a[0] = new Dog("Рекс");
        a[1] = new Animal("Тигра");
        a[2] = new Cat();
        a[3] = new Cat("Барсик");
        a[4]=new Dog();
        System.out.println("Счетчик животных: "+Animal.getAnimalCount());
        System.out.println("Счетчик котов: "+Cat.getCatCount());
        System.out.println("Счетчик собак: "+Dog.getDogCount());
        System.out.println();
        for(Animal animals: a){
                animals.run(1000);
                animals.swim(50);
        }
    }
}
