public  class Animal {
    protected String name;
    private static int animalCount;

    static {
        animalCount=0;
    }
    public Animal(){
        this.name="Какое-то животное";
        animalCount++;
    }
    public Animal(String name){
        this.name=name;
        animalCount++;
    }
    public void run(int length){
        System.out.println(name+" бежит "+length+" метров");
    }
    public void swim(int length){
        System.out.println(name+" плывет "+length+" метров");
    }
    public void info(){
        System.out.println("Животное "+ name);
    }
    public static int getAnimalCount(){
        return animalCount;
    }
}
