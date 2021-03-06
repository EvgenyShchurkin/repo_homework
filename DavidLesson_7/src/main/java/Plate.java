public class Plate {
    private int foodCount;

    public Plate(int foodCount){
        this.foodCount=foodCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int addFoodCount(int foodCount) {
        return this.foodCount+=foodCount;
    }

    public void foodConsumption(int foodCount) {
        this.foodCount-=foodCount;
    }
    public void info(){
        System.out.println("Plate foodcount is "+this.foodCount);
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }
}
