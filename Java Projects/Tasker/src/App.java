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
            System.out.println("Does Julia need new tasks? Y/N");
            String input = keyboard.nextLine().toUpperCase();
            if(input.equals("Y")) {
                weeklies.getTasks(dailyTasks);
                monthlies.getTasks(dailyTasks);
                System.out.println(dailyTasks); //This is probably going to print a memory address
            }
            if(input.equals("N")) {
                System.out.println("See you tomorrow");
                quit = true;
            }
            else {
                System.out.println("Incorrect input");
            }
        }

        weeklies.save();
        monthlies.save();
        keyboard.close();
    }
}
