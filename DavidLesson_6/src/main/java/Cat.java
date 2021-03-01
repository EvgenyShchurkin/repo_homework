public class Cat extends Animal{
    private static int catCount;

    static{
        catCount=0;
    }
    public Cat(){
        super();
        this.name="Какой-то кот";
        catCount++;
    }
    public Cat(String name){
        super(name);
        catCount++;
    }

    @Override
    public void info(){
        System.out.println("Кот "+ name);
    }

    @Override
    public void run(int length) {
        if(length>0&&length<=200){
            System.out.println("Кот "+name+" пробежал "+length+" метров");
        }
        else if (length>200){
            System.out.println("Кот "+name+" пробежал "+200+" метров и устал");
        }
        else{
            System.out.println("Кот "+name+" никуда и не бежал");
        }
    }

    @Override
    public void swim(int length) {
        System.out.println("Кот "+name +" не умеет плавать");
    }
    public static int getCatCount(){
        return catCount;
    }
}
