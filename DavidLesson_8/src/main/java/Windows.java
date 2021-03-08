import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Windows extends JFrame {
    private static final int SIZE = 3;
    private static JLabel[][] jLbs = new JLabel[SIZE][SIZE];


    public Windows(){
        setTitle("Крестики/нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(360,360);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(SIZE,SIZE));
        for (int i = 0; i < jLbs.length; i++) {
            for (int j = 0; j < jLbs[i].length; j++) {
                jLbs[i][j]= new JLabel(".",SwingConstants.CENTER);
                jLbs[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(jLbs[i][j]);
            }
        }
        setVisible(true);
    }

    private int showOptions(int option){
        switch (option) {
            case (0):
                return JOptionPane.showConfirmDialog(null, "Ничья\nХотите сыграть еще раз?", "Ничья", JOptionPane.YES_NO_OPTION);
            case (1):
                return JOptionPane.showConfirmDialog(null, "Победа\nХотите сыграть еще раз?", "УРА", JOptionPane.YES_NO_OPTION);
            default:
                return JOptionPane.showConfirmDialog(null, "К сожалению вы проиграли.\nХотите сыграть еще раз?", "Поражение", JOptionPane.YES_NO_OPTION);
        }
    }

    private void reset(){
        setTitle("MyFirstTitle");
        for (int i = 0; i < jLbs.length; i++) {
            for (int j = 0; j < jLbs[i].length; j++) {
                jLbs[i][j].setText(".");
                jLbs[i][j].setEnabled(true);
            }
        }
    }

    private  void pcTurn(){
        int pointX, pointY;
        Random rand = new Random();
        if(tryToWin()){
            moveToWin();
        }
        else if(!isBlockLine()) {
            do {
                pointX = rand.nextInt(jLbs.length);
                pointY = rand.nextInt(jLbs.length);
            } while (!(jLbs[pointY][pointX].getText().equals(".")));
            jLbs[pointY][pointX].setText("O");
            jLbs[pointY][pointX].setEnabled(false);
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


    private boolean isWinner(String symbol){
        int countX=0,countY=0,countL=0,countR=0;
        for (int i = 0; i < jLbs.length; i++) {
            for (int j = 0; j < jLbs.length; j++) {
                if(jLbs[i][j].getText().equals(symbol)){
                    countX++;
                }
                if(jLbs[j][i].getText().equals(symbol)){
                    countY++;
                }
                if(i==j&& jLbs[i][j].getText().equals(symbol)){
                    countL++;
                }
                if(j==jLbs.length-i-1&& jLbs[i][j].getText().equals(symbol)){
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

    public void humanClick(){
        for (JLabel[] butt : jLbs) {
            for (JLabel everyBut : butt) {
                    everyBut.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (everyBut.getText().equals(".")) {
                                everyBut.setText("X");
                                everyBut.setEnabled(false);

                                if(isWinner("X")){
                                   setTitle("Крестики победили");
                                    if(showOptions(1)==0) {
                                        reset();
                                    }
                                    else{
                                        System.exit(0);
                                    }
                                }
                                else if(countField()==9){
                                    setTitle("Ничья");
                                    if(showOptions(0)==0) {
                                        reset();
                                    }
                                    else{
                                        System.exit(0);
                                    }
                                }
                                else {
                                    pcTurn();
                                    if(isWinner("O")){
                                        setTitle("Нолики победили");
                                        if(showOptions(2)==0) {
                                            reset();
                                        }
                                        else{
                                            System.exit(0);
                                        }
                                    }
                                }

                            }
                        }
                    });
            }
        }
    }
    private int countField(){
        int count=0;
        for (int i = 0; i < jLbs.length; i++) {
            for (int j = 0; j < jLbs[i].length; j++) {
                if(!(jLbs[i][j].getText().equals("."))){
                    count++;
                }
            }
        }
        return count;
    }
    private  boolean tryToWin(){               //проверяет, есть ли возможность у компьютера победить
        int countRightDiagO=0,countRightDiagEmpty=0;
        int countLeftDiagO=0, countLeftDiagEmpty=0;
        for (int i = 0; i < jLbs.length; i++) {
            int countStr=0, countCln=0,countStrEmpty=0,countClnEmpty=0;
            for (int j = 0; j < jLbs[i].length; j++) {
                if(jLbs[i][j].getText().equals("O")){
                    countStr++;
                }
                if(jLbs[i][j].getText().equals(".")){
                    countStrEmpty++;
                }
                if(jLbs[j][i].getText().equals("O")){
                    countCln++;
                }
                if(jLbs[j][i].getText().equals(".")){
                    countClnEmpty++;
                }
                if(i==j&&jLbs[i][j].getText().equals("O")){
                    countRightDiagO++;
                }
                if(i==j&&jLbs[i][j].getText().equals(".")){
                    countRightDiagEmpty++;
                }
                if(j==jLbs[i].length-1-i&&jLbs[i][j].getText().equals("O")){
                    countLeftDiagO++;
                }
                if(j==jLbs[i].length-1-i&&jLbs[i][j].getText().equals(".")){
                    countLeftDiagEmpty++;
                }
            }
            if((countStr==2&&countStrEmpty==1)||(countCln==2&&countClnEmpty==1)){
                return true;
            }
        }
        return (countRightDiagO==2&&countRightDiagEmpty==1)||(countLeftDiagO==2&&countLeftDiagEmpty==1);
    }
    private  void moveToWin(){         //сделать решающий победный ход
        int str=-1, cln=-1;
        int countRightDiagO=0;
        int countRightDiagEmpty=0;
        for (int i = 0; i < jLbs.length ; i++) {
            int countStrO=0,countEmptyStr=0, countClnO=0,countEmptyCln=0;
            for (int j = 0; j <jLbs[i].length; j++) {
                if(jLbs[i][j].getText().equals("O")){
                    countStrO++;
                }
                if(jLbs[i][j].getText().equals(".")){
                    countEmptyStr++;
                }
                if(jLbs[j][i].getText().equals("O")){
                    countClnO++;
                }
                if(jLbs[j][i].getText().equals(".")){
                    countEmptyCln++;
                }
                if(i==j&&jLbs[i][j].getText().equals("O")){
                    countRightDiagO++;
                }
                if(i==j&&jLbs[i][j].getText().equals(".")){
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
            for (int i = 0; i < jLbs[str].length; i++) {
                if (jLbs[str][i].getText().equals(".")) {
                    jLbs[str][i].setText("O");
                    jLbs[str][i].setEnabled(false);
                    return;
                }
            }
        }
        else if(cln>-1){
            for (int i = 0; i <jLbs.length; i++) {
                if(jLbs[i][cln].getText().equals(".")){
                    jLbs[i][cln].setText("O");
                    jLbs[i][cln].setEnabled(false);
                    return;
                }
            }
        }
        else if(countRightDiagO==2&&countRightDiagEmpty==1){
            for (int i = 0; i < jLbs.length; i++) {
                for (int j = 0; j < jLbs[i].length; j++) {
                    if(i==j&&jLbs[i][j].getText().equals(".")){
                        jLbs[i][j].setText("O");
                        jLbs[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < jLbs.length; i++) {
                for (int j = 0; j < jLbs[i].length; j++) {
                    if(j==jLbs[i].length-1-i&&jLbs[i][j].getText().equals(".")){
                        jLbs[i][j].setText("O");
                        jLbs[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
    }
    private  boolean isBlockLine(){             //нужно ли блокировать следующий ход игрока
        int countRightX=0, countLeftX=0;
        int countRightO=0, countLeftO=0;
        for (int i = 0; i < jLbs.length ; i++) {
            int countX=0,countO=0,countClmX=0, countClmO=0;
            for (int j = 0; j < jLbs[i].length; j++) {
                if(jLbs[i][j].getText().equals("X")){
                    countX++;
                }
                if(jLbs[i][j].getText().equals("O")){
                    countO++;
                }
                if(jLbs[j][i].getText().equals("X")){
                    countClmX++;
                }
                if(jLbs[j][i].getText().equals("O")){
                    countClmO++;
                }
                if(i==j&&jLbs[i][j].getText().equals("X")){
                    countRightX++;
                }
                if(i==j&&jLbs[i][j].getText().equals("O")){
                    countRightO++;
                }
                if(j== jLbs[i].length-1-i&&jLbs[i][j].getText().equals("X")){
                    countLeftX++;
                }
                if(j== jLbs[i].length-1-i&&jLbs[i][j].getText().equals("O")){
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

    private static int numberStr(){                     //возвращает номер строки
        int countX=0,countO=0;
        for (int i = 0; i < jLbs.length ; i++) {
            for (int j = 0; j < jLbs.length; j++) {
                if(jLbs[i][j].getText().equals("X")){
                    countX++;
                }
                if(jLbs[i][j].getText().equals("O")){
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
    private static int numberCln(){                    //возвращает номер столбца
        int countX =0,countO=0;
        for (int i = 0; i < jLbs.length ; i++) {
            for (int j = 0; j < jLbs.length; j++) {
                if(jLbs[j][i].getText().equals("X")){
                    countX++;
                }
                if(jLbs[j][i].getText().equals("O")){
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
    private static void blockStr(int lineNumber){    //блокирует строку
        if(lineNumber<0){
            return;
        }
        for (int i = 0; i < jLbs.length; i++) {
            if(jLbs[lineNumber][i].getText().equals(".")){
                jLbs[lineNumber][i].setText("O");
                jLbs[lineNumber][i].setEnabled(false);
            }
        }
    }
    private static void blockClm(int clmNumber){     //блокирует столбец
        if(clmNumber<0){
            return;
        }
        for (int i = 0; i < jLbs.length; i++) {
            if(jLbs[i][clmNumber].getText().equals(".")){
                jLbs[i][clmNumber].setText("O");
                jLbs[i][clmNumber].setEnabled(false);
            }
        }
    }


    private static boolean isNumberStrFull(int numberStr){   //есть ли свободное место в строке
        int count=0;
        for (int i = 0; i < jLbs.length; i++) {
            if(!(jLbs[numberStr][i].getText().equals("."))){
                count++;
            }
        }
        return count==3;
    }

    private static boolean isNumberClmFull(int numberCln){   //есть ли свободное место в столбце
        int count=0;
        for (int i = 0; i < jLbs.length; i++) {
            if(!(jLbs[i][numberCln].getText().equals("."))){
                count++;
            }
        }
        return count==3;
    }

    //заблокировать какую-либо из диагоналей
    private static void blockDiag(){
        int rightDiagCount=0,rightCountEmpty=0;
        for (int i = 0; i < jLbs.length; i++) {
            for (int j = 0; j <jLbs[i].length ; j++) {
                if(i==j&&jLbs[i][j].getText().equals("X")){
                    rightDiagCount++;
                }
                if(i==j&&jLbs[i][j].getText().equals(".")){
                    rightCountEmpty++;
                }
            }
        }
        if(rightDiagCount==2&&rightCountEmpty==1) {
            for (int i = 0; i < jLbs.length; i++) {
                for (int j = 0; j < jLbs[i].length; j++) {
                    if (i == j && jLbs[i][j].getText().equals(".")) {
                        jLbs[i][j].setText("O");
                        jLbs[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < jLbs.length; i++) {
                for (int j = 0; j < jLbs[i].length; j++) {
                    if (j==jLbs[i].length-1-i&&jLbs[i][j].getText().equals(".")) {
                        jLbs[i][j].setText("O");
                        jLbs[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
    }

}
