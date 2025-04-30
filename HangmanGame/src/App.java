import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    @SuppressWarnings("rawtypes")
    private static ArrayList getGuesses(String fileName ) {
        ArrayList<String> guesses = new ArrayList<>();
        try (FileReader file = new FileReader(fileName); BufferedReader read = new BufferedReader(file)) {
            String Buffer;
            Buffer = "";
            while (Buffer != null) {
                Buffer = read.readLine();
                if (Buffer == null)
                    break ;
                guesses.add(Buffer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found or path not valid !");
        } catch (IOException e) {
            System.out.println("some thing wrong happend in reading from file");
        } catch (Exception e) {
            System.out.println("some thing wrong");
        }
        return (guesses);
    }

    private static String handmanDrow(int ties) {
        switch (ties) {
            case 0:
                return "";
            case 1:
                return " O\n\n";
            case 2:
                return " O\n/ \n";
            case 3:
                return " O\n/|\\\n";
            case 4:
                return " O\n/|\\\n/";
            case 5:
                return " O\n/|\\\n/ \\";
            default:
                return "";
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
        ArrayList<Character> wordState = new ArrayList<>();
        ArrayList<String> words = getGuesses("words.txt");
        Scanner in = new Scanner(System.in);

        System.out.println("*********************************");
        System.out.println("Wellocome to Java Gangman Game!");
        System.out.println("*********************************");
        System.out.println("\nthere are " + words.size() + " Chapters in this game");
        int counteGames = 0;

        while (counteGames < words.size()){
            System.out.println("game number "+ counteGames + 1);
            System.out.println();
            wordState.clear();
            for (int i = 0; i < words.get(counteGames).length() ; i++) {
                wordState.add('_');
            }
            System.out.print("Word : ");
            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();
            int Answer = 0;
            int i = 0;
            while (Answer <= 5) {
                System.out.print("Enter gusse : ");
                char gu = in.next().toLowerCase().charAt(0);
                if (words.get(counteGames).indexOf(gu) >= 0)
                {
                    System.out.println("Correct Answer");
                    for (int j = 0; j <words.get(counteGames).length(); j++) {
                        if (words.get(counteGames).charAt(j) == gu)
                            wordState.set(j, gu);
                    }
                    for (char c : wordState) {
                        System.out.print(c + " ");
                    }
                    System.out.println();
                    if (!wordState.contains('_')) {
                        System.out.println("You Win game number " + (counteGames + 1) + "\n");
                        break;
                    }
                } else {
                    Answer++;
                    System.out.println(handmanDrow(Answer));
                    if (Answer == 5)
                    {
                        System.out.println("you lost");
                        break ;
                    }
                }
                i++;
            }
            System.out.println("--------------------------");
            counteGames++;
        };
    }
}
