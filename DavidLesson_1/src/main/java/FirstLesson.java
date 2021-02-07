
public class FirstLesson {

    public static void main(String[] args){
        byte b = 15;
        short s = 150;
        int i = 300;
        long l = 1500L;
        float f = 177.7f;
        double d = 18475.58;
        char c = '$';
        boolean b1 = true;
        String str ="Тестовая строка";


    }
    public static float FirstMethod(float  a, float b, float c, float d){
        float result = a*(b+(c/d));
        return result;
    }
    public static boolean SecondMethod(int a, int b){
        if (a+b>=10 && a+b<=20) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void ThirdMethod(int a){
        if(a>=0){
            System.out.println("Число "+a+" положительное.");
        }
        else {
            System.out.println("Число "+a+" отрицательное.");
        }
    }
    public static boolean FourthMethod(int a){
        if(a<0){
            return true;
        }
        else{
            return false;
        }
    }
    public static void FifthMethod(String name){

        System.out.println("Привет, "+name+"!");
    }
    public static void SixthMethod(int year){
        if(year%400==0){
            System.out.println("Год "+year+ " високосный, т.к. каждый 400й год високосный");
        }
        else if (year%100==0){
            System.out.println("Год "+year+ " невисокосный, т.к. каждый 100й год невисокосный");
        }
        else if (year%4==0){
            System.out.println("Год "+year+ " високосный, т.к. каждый 4й год високосный");;
        }
        else {
            System.out.println("Год "+year+ " невисокосный");
        }
    }
}


