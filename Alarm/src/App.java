import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LocalTime time = null;
        DateTimeFormatter form = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (time == null) {
            try {
                System.out.print("enter an alarm time in this formatte (HH:MM:SS): ");
                String userInput = in.nextLine();
                time = LocalTime.parse(userInput, form);
                System.out.println("alarm set at " + time);
                Alarm ar = new Alarm(time);
                Thread tr = new Thread(ar);
                tr.start();
            } catch (Exception e) {
                System.out.println("invalid time formatte enter again!");
            }
        }
    }
}
