public class Dog extends Animal{
    private static int dogCount;

    static {
        dogCount=0;
    }
    public Dog(){
        super();
        this.name="Какой-то пес";
        dogCount++;
    }
    public Dog(String name){
        super(name);
        dogCount++;
    }
    @Override
    public void info(){
        System.out.println("Пес "+ name);
    }

    @Override
    public void run(int length) {
        if(length>0&&length<=500){
            System.out.println("Пес "+name+" пробежал "+length+" метров");
        }
        else if (length>500){
            System.out.println("Пес "+name+" пробежал "+500+" метров и устал");
        }
        else{
            System.out.println("Пес "+name+" никуда и не бежал");
        }
    }

    @Override
    public void swim(int length) {
        if(length>0&&length<=10){
            System.out.println("Пес "+name+" проплыл "+length+" метров");
        }
        else if (length>10){
            System.out.println("Пес "+name+" проплыл "+10+" метров и устал");
        }
        else{
            System.out.println("Пес "+name+" никуда и не плыл");
        }
    }
    public static int getDogCount(){
        return dogCount;
    }
}

