public class Cat {
    private final String name;
    private boolean isHungry;   //true если foodToEat>0
    private int foodToEat;
    private static int catsCount;
    static {
        catsCount=0;
    }
    public Cat(){
        this.name="Кот номер "+(++catsCount);
        this.foodToEat= 17;
        this.isHungry=true;
    }
    public Cat(String name, int foodToEat) {
        this.name = name;
        this.foodToEat = foodToEat;
        this.isHungry = true;
        catsCount++;
    }

    public int getFoodToEat() {
        return foodToEat;
    }

    public void catEatsFromPlate(Plate plate){
        if(plate.getFoodCount()>=this.foodToEat){
            {
                plate.foodConsumption(this.foodToEat);
                this.foodToEat=0;
                this.isHungry=false;
            }
        }
    }
    public void info(){
        if (this.isHungry) {
            System.out.println("Name " + this.name + " hungry for " + this.foodToEat + " isHungry is True");
        }
        else{
            System.out.println("Name " + this.name +
                    " not hungry. foodToEat is " + this.foodToEat + " isHungry is False");
        }
    }

    public static void main(String[] args) {
        Cat[] cats = new Cat[5];
        Plate plate = new Plate(50);

        for (int i = 0; i < cats.length; i++) {
            cats[i]=new Cat();
        }
        plate.addFoodCount(10);
        plate.info();
        for (Cat e: cats) {
            e.catEatsFromPlate(plate);
            e.info();
            plate.info();
        }
    }
}
