import java.util.Random;
import java.util.Scanner;

public class ThirdHomework {
    public static void main(String[] args) {

        String[] words =  {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                    "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                    "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Random rand = new Random();
        String chosenWord = words[rand.nextInt(words.length)];
        System.out.println("Компьютер загадал слово.\nВам нужно это слово отгадать.\n");
//        System.out.println(chosenWord);   //для теста
        Scanner in = new Scanner(System.in);
        boolean isFound = false;
        do {
            System.out.print("Введите слово: ");
            String word = in.next();
            in.nextLine();              // для очистки буфера, если слова вводились через пробел
            if (word.equals(chosenWord)) {
                System.out.println("Вы угадали!!! Игра окончена!");
                isFound = true;
            } else {
                System.out.println("Неправильно");
                PrintCorrectSymbols(chosenWord, word);
            }
        } while (!isFound);
        in.close();
    }

    public static void PrintCorrectSymbols(String correctWord, String inputWord){
        for (int i = 0; i < Math.min(correctWord.length(),inputWord.length()); i++) {
            if (inputWord.charAt(i) == correctWord.charAt(i)) {
                System.out.print(correctWord.charAt(i));
            } else {
                System.out.print('#');
            }
        }
        for (int i = 0; i <15-Math.min(correctWord.length(),inputWord.length()) ; i++) {     //допечатать ###
            System.out.print("#");
        }
        System.out.println("\n");
    }
}




