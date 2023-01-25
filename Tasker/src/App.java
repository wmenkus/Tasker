import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        LinkedList<Task> dailyTasks = new LinkedList<Task>();
        Weeklies weeklies = new Weeklies();
        Monthlies monthlies = new Monthlies();

        boolean quit = false;
        while(!quit) {
            System.out.println("Weeklies or Monthlies?\n" +
                "1. Weeklies\n" +
                "2. Monthlies\n" +
                "3. Quit\n");
            String input = keyboard.nextLine().toUpperCase();
            if(input.equals("1")) {
                weeklies.getTasks(dailyTasks);
                System.out.println(dailyTasks);
                quit = true;
            }
            else if(input.equals("2")) {
                monthlies.getTasks(dailyTasks);
                System.out.println(dailyTasks);
                quit = true;
            }
            else if(input.equals("3")) {
                quit = true;
            }
            else {
                System.out.println("Incorrect input");
            }
        }

        System.out.println("See you tomorrow");
        weeklies.save();
        monthlies.save();
        keyboard.close();
    }
}
