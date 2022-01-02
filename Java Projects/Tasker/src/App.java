import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        final int WEEKLY_TASKS = 3;
        final int MONTHLY_TASKS = 1;
        LinkedList<Task> dailyTasks;
        Weeklies weeklies = new Weeklies();
        Monthlies monthlies = new Monthlies();

        boolean quit = false;
        while(!quit) {
            System.out.println("Does Julia need new tasks? Y/N");
            String input = keyboard.nextLine().toUpperCase();
            if(input.equals("Y")) {
                dailyTasks.clear();
                while(weeklies.getTaskNum() < WEEKLY_TASKS) {
                    dailyTasks.add(weeklies.getTask());
                }
                while(monthlies.getTaskNum() < MONTHLY_TASKS) {
                    dailyTasks.add(monthlies.getTask());
                }
                
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
    }
}
