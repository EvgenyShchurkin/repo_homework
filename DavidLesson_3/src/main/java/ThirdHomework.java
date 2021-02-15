import java.util.Random;
import java.util.Scanner;

public class ThirdHomework {
    public static void main(String[] args) {

        String[] words = new String[] {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                    "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                    "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Random rand = new Random();
        String chosenWord = words[rand.nextInt(words.length)];
        System.out.println("Компьютер загадал слово.\nВам нужно это слово отгадать.\n");
//        System.out.println(chosenWord);
        Scanner in = new Scanner(System.in);
        boolean isFound = false;
        do {
            System.out.print("Введите слово: ");
            String word = in.next();
            if (word.equals(chosenWord)) {
                System.out.println("Вы угадали!!! Игра окончена!");
                isFound = true;
                in.close();
            } else {
                System.out.println("Неправильно");
                PrintCorrectSymbols(chosenWord, word);
            }
        } while (!isFound);
    }

    public static void PrintCorrectSymbols(String correctWord, String yourWord){
        for (int i = 0; i < Math.min(correctWord.length(),yourWord.length()); i++) {
            if (yourWord.charAt(i) == correctWord.charAt(i)) {
                System.out.print(correctWord.charAt(i));
            } else {
                System.out.print('#');
            }
        }
        for (int i = 0; i <15-Math.min(correctWord.length(),yourWord.length()) ; i++) {     //допечатать ###
            System.out.print("#");
        }
        System.out.println("\n");
    }
}




