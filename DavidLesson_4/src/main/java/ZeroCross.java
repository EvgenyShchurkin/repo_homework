import java.util.Random;
import java.util.Scanner;

public class ZeroCross {

    public static final char DOT_X= 'X';
    public static final char DOT_O='O';
    public static final char EMPTY_DOT='•';
    public static final int LENGTH_TO_WIN=3;
    public static char[][] field;
    public static  int SIZE =3;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int count =0;
        fieldInit();
        fieldDisplay();
        do {
            humanMove();
            fieldDisplay();
            count++;
            if (isWinner5x5for4(DOT_X,LENGTH_TO_WIN)) {
                System.out.println("Вы победили!!!");
                break;
            }
            if(isFieldFull(count)){
                System.out.println("Ничья");
                break;
            }
            pcMove();
            fieldDisplay();
            count++;
            if (isWinner5x5for4(DOT_O,LENGTH_TO_WIN)) {
                System.out.println("Комп победил!!!");
                break;
            }
            if(isFieldFull(count)){
                System.out.println("Ничья");
                break;
            }
        }while(true);
        in.close();
    }
    public static void fieldInit(){
        field = new char[SIZE][SIZE];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j]=EMPTY_DOT;
            }
        }
    }
    public static void fieldDisplay(){
        System.out.print("  ");
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%2d",i+1);
        }
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%2d",i+1);
            for (int j = 0; j < field.length ; j++) {
                System.out.printf("%2c",field[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanMove(){
        int pointX=-1, pointY=-1;
        do {
            System.out.println("Введите координаты X и Y, куда хотите сделать ход:");
            if(in.hasNextInt()) {
                pointX = in.nextInt() - 1;
            }
            if(in.hasNextInt()) {
                pointY = in.nextInt() - 1;
            }
            in.nextLine();
        }while(!(pointX>=0&&pointX< field.length&&pointY>=0&&pointY< field.length&&field[pointY][pointX]==EMPTY_DOT));
        field[pointY][pointX]=DOT_X;
    }

    public static boolean isFieldFull(int count){
        return count==field.length* field.length;
    }

    public static void pcMove(){              //ход компьютера
        int pointX, pointY;
        Random rand = new Random();
        System.out.println("Ход Компьютера");
        if(tryToWin()){
           moveToWin();
        }
        else if(!isBlockLine()) {
            do {
                pointX = rand.nextInt(field.length);
                pointY = rand.nextInt(field.length);
            } while (!(field[pointY][pointX] == EMPTY_DOT));
            field[pointY][pointX]=DOT_O;
        }
        else{
            if(numberStr()!=-1&&!isNumberStrFull(numberStr())) {
                blockStr(numberStr());
            }
            else if(numberCln()!=-1&&!isNumberClmFull(numberCln())){
                blockClm(numberCln());
            }
            else{
                blockDiag();
            }
        }
    }
    // использовать для поля >3на3
    public static void pcTurn(){
        int pointX, pointY;
        Random rand = new Random();
        System.out.println("Ход Компьютера");
            do {
                pointX = rand.nextInt(field.length);
                pointY = rand.nextInt(field.length);
            } while (!(field[pointY][pointX] == EMPTY_DOT));
            field[pointY][pointX]=DOT_O;
    }


    public static boolean tryToWin(){               //проверяет, есть ли возможность у компьютера победить
        int countRightDiagO=0,countRightDiagEmpty=0;
        int countLeftDiagO=0, countLeftDiagEmpty=0;
        for (int i = 0; i < field.length; i++) {
            int countStr=0, countCln=0,countStrEmpty=0,countClnEmpty=0;
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j]==DOT_O){
                    countStr++;
                }
                if(field[i][j]==EMPTY_DOT){
                    countStrEmpty++;
                }
                if(field[j][i]==DOT_O){
                    countCln++;
                }
                if(field[j][i]==EMPTY_DOT){
                    countClnEmpty++;
                }
                if(i==j&&field[i][j]==DOT_O){
                    countRightDiagO++;
                }
                if(i==j&&field[i][j]==EMPTY_DOT){
                    countRightDiagEmpty++;
                }
                if(j==field[i].length-1-i&&field[i][j]==DOT_O){
                    countLeftDiagO++;
                }
                if(j==field[i].length-1-i&&field[i][j]==EMPTY_DOT){
                    countLeftDiagEmpty++;
                }
            }
            if((countStr==2&&countStrEmpty==1)||(countCln==2&&countClnEmpty==1)){
                return true;
            }
        }
        return (countRightDiagO==2&&countRightDiagEmpty==1)||(countLeftDiagO==2&&countLeftDiagEmpty==1);
    }


    public static void moveToWin(){         //сделать решающий победный ход
        int str=-1, cln=-1;
        int countRightDiagO=0;
        int countRightDiagEmpty=0;
        for (int i = 0; i < field.length ; i++) {
            int countStrO=0,countEmptyStr=0, countClnO=0,countEmptyCln=0;
            for (int j = 0; j <field[i].length; j++) {
                if(field[i][j]==DOT_O){
                    countStrO++;
                }
                if(field[i][j]==EMPTY_DOT){
                    countEmptyStr++;
                }
                if(field[j][i]==DOT_O){
                    countClnO++;
                }
                if(field[j][i]==EMPTY_DOT){
                    countEmptyCln++;
                }
                if(i==j&&field[i][j]==DOT_O){
                    countRightDiagO++;
                }
                if(i==j&&field[i][j]==EMPTY_DOT){
                   countRightDiagEmpty++;
                }
            }
            if(countStrO==2&&countEmptyStr==1){
                str=i;
                break;
            }
            if(countClnO==2&&countEmptyCln==1){
                cln=i;
                break;
            }
        }
        if(str>-1) {
            for (int i = 0; i < field[str].length; i++) {
                if (field[str][i] == EMPTY_DOT) {
                    field[str][i] = DOT_O;
                    return;
                }
            }
        }
        else if(cln>-1){
            for (int i = 0; i <field.length; i++) {
                if(field[i][cln]==EMPTY_DOT){
                    field[i][cln] = DOT_O;
                    return;
                }
            }
        }
        else if(countRightDiagO==2&&countRightDiagEmpty==1){
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if(i==j&&field[i][j]==EMPTY_DOT){
                        field[i][j]=DOT_O;
                        return;
                    }
                }
            }
        }
          else{
              for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if(j==field[i].length-1-i&&field[i][j]==EMPTY_DOT){
                        field[i][j]=DOT_O;
                        return;
                    }
                }
            }
        }
    }
    //проверка победы для поля 3на3
    public static boolean isWinner(char symbol){
        int countX=0,countY=0,countL=0,countR=0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if(field[i][j]==symbol){
                    countX++;
                }
                if(field[j][i]==symbol){
                    countY++;
                }
                if(i==j&&field[i][j]==symbol){
                    countL++;
                }
                if(j== field.length-i-1&&field[i][j]==symbol){
                    countR++;
                }
            }
            if(countX==3||countY==3){
                return true;
            }
            else{
                countX=countY=0;
            }
        }
        return countL==3||countR==3;
    }


    // метод подходит для любого поля, необходимо передавать количество фишек для победы
    // не производится проверка превышения количества фишек и длины поля
    public static boolean isWinner5x5for4 (char symbol,int countLength){
        int countX=0,countY=0,countL=0,countR=0;
        for (int l = 0; l < SIZE-(countLength-1) ; l++) {
            int subRightDiag=0,subRightDiag1=0;
            int subLeftDiag=0,subLeftDiag1=0;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == symbol) {
                        countX++;
                        if (countX == countLength) {
                            return true;
                        }
                    }
                    if (field[i][j] == EMPTY_DOT && countX < countLength) {
                        countX = 0;
                    }
                    if (field[j][i] == symbol) {
                        countY++;
                        if (countY == countLength) {
                            return true;
                        }
                    }
                    if (field[j][i] == EMPTY_DOT && countY < countLength) {
                        countY = 0;
                    }
                    if (i == j && field[i][j] == symbol) {
                        countR++;
                        if (countR == countLength) {
                            return true;
                        }
                    }
                    if (i == j && field[i][j] == EMPTY_DOT && countR < countLength) {
                        countR = 0;
                    }

                    if (j == field.length - i - 1 && field[i][j] == symbol) {
                        countL++;
                        if (countL == countLength) {
                            return true;
                        }
                    }
                    if (j == field.length - i - 1 && field[i][j] == EMPTY_DOT && countL < countLength) {
                        countL = 0;
                    }
                    if(j==i+l&&field[i][j]==symbol){
                        subRightDiag++;
                        if(subRightDiag==countLength){
                            return true;
                        }
                    }
                    if(j==i+l&&field[i][j]==EMPTY_DOT){
                        subRightDiag=0;
                    }
                    if(i==j+l&&field[i][j]==symbol){
                        subRightDiag1++;
                        if(subRightDiag1==countLength){
                            return true;
                        }
                    }
                    if(i==j+l&&field[i][j]==EMPTY_DOT){
                        subRightDiag1=0;
                    }
                    if (j==field[i].length-1-l-i&&field[i][j]==symbol){
                        subLeftDiag++;
                        if(subLeftDiag==countLength){
                            return true;
                        }
                    }
                    if(j==field[i].length-1-l-i&&field[i][j]==EMPTY_DOT){
                        subLeftDiag=0;
                    }
                    if(i==field.length+l-j-1&&field[i][j]==symbol){
                        subLeftDiag1++;
                        if(subLeftDiag1==countLength) {
                            return true;
                        }
                    }
                    if(i==field.length+l-j-1&&j==field.length-i&&field[i][j]==EMPTY_DOT){
                        subLeftDiag1=0;
                    }
                }
                countX = countY = 0;
            }
        }
        return false;
    }

//заблокировать какую-либо из диагоналей
    public static void blockDiag(){
        int rightDiagCount=0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j <field[i].length ; j++) {
                if(i==j&&field[i][j]==DOT_X){
                    rightDiagCount++;
                }
            }
        }
        if(rightDiagCount==2) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (i == j && field[i][j] == EMPTY_DOT) {
                        field[i][j] = DOT_O;
                        return;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (j==field[i].length-1-i&&field[i][j]==EMPTY_DOT) {
                        field[i][j] = DOT_O;
                        return;
                    }
                }
            }
        }
    }

    public static boolean isNumberStrFull(int numberStr){   //есть ли свободное место в строке
        int count=0;
        for (int i = 0; i < field.length; i++) {
            if(field[numberStr][i]!=EMPTY_DOT){
                count++;
            }
        }
        return count==3;
    }
    public static boolean isNumberClmFull(int numberCln){   //есть ли свободное место в столбце
        int count=0;
        for (int i = 0; i < field.length; i++) {
            if(field[i][numberCln]!=EMPTY_DOT){
                count++;
            }
        }
        return count==3;
    }
    public static boolean isBlockLine(){             //нужно ли блокировать следующий ход игрока
        int countRightX=0, countLeftX=0;
        int countRightO=0, countLeftO=0;
        for (int i = 0; i < field.length ; i++) {
            int countX=0,countO=0,countClmX=0, countClmO=0;
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j]==DOT_X){
                    countX++;
                }
                if(field[i][j]==DOT_O){
                    countO++;
                }
                if(field[j][i]==DOT_X){
                    countClmX++;
                }
                if(field[j][i]==DOT_O){
                    countClmO++;
                }
                if(i==j&&field[i][j]==DOT_X){
                    countRightX++;
                }
                if(i==j&&field[i][j]==DOT_O){
                    countRightO++;
                }
                if(j== field[i].length-1-i&&field[i][j]==DOT_X){
                    countLeftX++;
                }
                if(j== field[i].length-1-i&&field[i][j]==DOT_O){
                    countLeftO++;
                }
            }
            if(countX+countO>2&&countClmO+countClmX>2){
                continue;
            }
            if((countX==2&&countO==0)||(countClmX==2&&countClmO==0)) {
                return true;
            }
        }
        return (countRightX==2&&countRightO==0)||(countLeftX==2&&countLeftO==0);
    }
    public static int numberStr(){                     //возвращает номер строки
        int countX=0,countO=0;
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field.length; j++) {
                if(field[i][j]==DOT_X){
                    countX++;
                }
                if(field[i][j]==DOT_O){
                    countO++;
                }
            }
            if(countX==2&&countO==0) {
                return i;
            }
            else{
                countX=countO=0;
            }
        }
        return -1;
    }
    public static int numberCln(){                    //возвращает номер столбца
        int countX =0,countO=0;
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field.length; j++) {
                if(field[j][i]==DOT_X){
                    countX++;
                }
                if(field[j][i]==DOT_O){
                    countO++;
                }
            }
            if(countX==2&&countO==0) {
                return i;
            }
            else{
                countX=countO=0;
            }
        }
        return -1;
    }
    public static void blockStr(int lineNumber){    //блокирует строку
        if(lineNumber<0){
            return;
        }
        for (int i = 0; i < field.length; i++) {
            if(field[lineNumber][i]==EMPTY_DOT){
                field[lineNumber][i]=DOT_O;
            }
        }
    }
    public static void blockClm(int clmNumber){     //блокирует столбец
        if(clmNumber<0){
            return;
        }
        for (int i = 0; i < field.length; i++) {
            if(field[i][clmNumber]==EMPTY_DOT){
                field[i][clmNumber]=DOT_O;
            }
        }
    }
}
