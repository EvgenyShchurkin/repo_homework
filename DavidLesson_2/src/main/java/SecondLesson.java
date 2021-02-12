
public class SecondLesson {
    public static void main(String[] args) {
        int[]a = new int[8];
        int[]b ={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[]q ={1, 5, 3, 10, 0, 0, 0, 2, 4, 8, 0, 4,1};
        int[][]c= new int[10][10];
        int[] z = {1,2,3,4,5,6,7,8,9};
        int[] u = {1,2,3,4,5,6,7,8,9};
        SeventhMethod(z,-10);
        for (int i = 0; i < z.length; i++) {
            System.out.print(z[i]+" ");
        }
        System.out.println();
        FinalArrMove(u,-10);
        for (int i = 0; i < z.length; i++) {
            System.out.print(z[i]+" ");
        }
    }





    public static void FirstMethod(int arr[]){
        System.out.println("Заполнение массива:");
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*2);
            System.out.print(arr[i]+" ");
        }
        System.out.println("\nЗамена 0 на 1 и 1 на 0");
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0){
                arr[i]=1;
            }
            else{
                arr[i]=0;
            }
            System.out.print(arr[i]+" ");
        }
    }

    public static void SecondMethod(int[] arr){
        System.out.println("\nЗаполнение массива:\n");
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i*3;
            System.out.print(arr[i]+" ");
        }
    }
    public static void ThirdMethod(int[]arr){
        System.out.println("\nВывод массимва\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
            if(arr[i]<6){
                arr[i]*=2;
            }
        }
        System.out.println("\nУмножение элементов <6 на 2");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    public static void ForthMethod(int arr[][]){

        for (int i = 0; i < arr.length; i++) {
            for(int j=0;j<arr[i].length;j++){
                if((i==j)||(j==arr[i].length-i-1)) {
                    arr[i][j] = 1;
                }
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void FifthMethod(int[] arr){
        int min=arr[0],max=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
            if(arr[i]<min){
                min = arr[i];
            }
        }
        System.out.println("Min="+min+", max="+max);
    }
    public static boolean SixthMethod(int arr[]){
        int sumLeft=0,sumRight=0;
        for (int i = 0; i < arr.length-1; i++) {
            sumLeft+= arr[i];
            for (int j = i+1; j < arr.length ; j++) {
                sumRight+=arr[j];
            }
            if(sumLeft==sumRight){
                return true;
            }
            else{
               sumRight=0;
            }
        }
        return false;
    }
    public static void SeventhMethod(int[] arr, int n) {
        int tmp;
        if(Math.abs(n)>=arr.length){        //если сдвиг больше количесва элементов массива
            n%= arr.length;
        }
        if (n == 0) {
            return;
        } else if (n > 0) {
            for (;n != 0;n--) {         //цикл для сдвига на 1 элемент
                for (int i = 0; i < (arr.length - 1); i++) { //сдвиг вправо на 1 элемент
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }

            }
        } else {
            for (;n != 0;n++) {
                for (int i = arr.length - 1; i > 0; i--) {
                    tmp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = tmp;
                }
            }
        }
    }
    public static int MaxDivisor(int a, int b) {         //поиск наибольшего общего делителя двух чисел
        b=Math.abs(b);
        while (a != b) {
            if (a > b) {
                a = a - b;
            }
            else {
                b = b - a;
            }
        }
        return a;
    }
    public static void FinalArrMove(int[] arr, int n) {
        int tmp;
        if (Math.abs(n)>=arr.length) {
            n%=arr.length;
        }
        if(n==0){
            return;
        }
        else if(n>0){
            int index = n-1;
            for (int i = 0; i < (arr.length - MaxDivisor(arr.length, n)); i++) {
                index++;
                if(index== arr.length){
                    int arrlong=arr.length/n;
                    index=n*arrlong;            //перемещение индекса в конец массива
                }
                if(index<=i){
                    index= arr.length-1;
                }
                tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }
        else{
            int index = arr.length+n;
            for (int i = arr.length -1; i>MaxDivisor(arr.length, n)-1; i--) {
                index--;
                if (index == -1) {
                    int arrlong = arr.length / n;
                    index = arr.length-1 -n*arrlong;            //перемещение индекса в начало массива
                }
                if (index >= i) {
                    index = 0;
                }
                tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }
    }
}
