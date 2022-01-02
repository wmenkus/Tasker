import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Weeklies {
    private LinkedList<Task> column1Ready;
    private LinkedList<Task> column1Done;
    private LinkedList<Task> column2Ready;
    private LinkedList<Task> column2Done;
    private LinkedList<Task> column3Ready;
    private LinkedList<Task> column3Done;
    private Random rand;
    private boolean wasLaundry;
    private int litterboxCooldown;

    public Weeklies() {
        load();
        rand = new Random();
    }

    public void getTasks(LinkedList<Task> dailyTasks) {
        //Adds task from column 1
        Task column1Task;
        if(wasLaundry) {
            column1Task = new Task("Put away laundry", 1, true);
        }
        else {
            if(column1Ready.isEmpty()) {
                resetColumn1();
            }
            column1Task = pullColumn1();
        }
        dailyTasks.add(column1Task);

        //Adds task from column 2 if there is space
        if(column1Task.getTaskValue() == 1) {
            if(column2Ready.isEmpty()) {
                resetColumn2();
            }
            dailyTasks.add(pullColumn2());
        }

        //Adds task from column 3
        Task column3Task;
        if(column3Ready.isEmpty()) {
            litterboxCooldown = rand.nextInt(2) + 3;
            resetColumn3();
            column3Task = new Task("Litterboxes", 1, true);
        }
        else {
            litterboxCooldown--;
            if(litterboxCooldown == 0) {
                column3Task = new Task("Litterboxes", 1, true);
            }
            else {
                column3Task = pullColumn3();
                if(column3Task.getTask().equals("Laundry")) {
                    wasLaundry = true;
                }
            }
        }
        dailyTasks.add(column3Task);
        return;
    }

    private Task pullColumn1() {
        int num = rand.nextInt(column1Ready.size());
        Task task = column1Ready.remove(num);
        task.setDone(true);
        column1Done.add(task);
        return task;
    }

    private Task pullColumn2() {
        int num = rand.nextInt(column2Ready.size());
        Task task = column2Ready.remove(num);
        task.setDone(true);
        column2Done.add(task);
        return task;
    }

    private Task pullColumn3() {
        int num = rand.nextInt(column3Ready.size());
        Task task = column3Ready.remove(num);
        task.setDone(true);
        column3Done.add(task);
        return task;
    }

    private void resetColumn1() {
        while(column1Done.size() > 0) {
            column1Ready.add(column1Done.removeFirst());
        }
    }

    private void resetColumn2() {
        while(column2Done.size() > 0) {
            column2Ready.add(column2Done.removeFirst());
        }
    }

    private void resetColumn3() {
        while(column3Done.size() > 0) {
            column3Ready.add(column3Done.removeFirst());
        }
    }

    public void save()
	{
		try
		{
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream("weeklies.txt"));
            fileWriter.println(wasLaundry);
            fileWriter.println(litterboxCooldown);
            fileWriter.println("COLUMN 1");
			for(Task t : column1Ready) {
				fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            for(Task t : column1Done) {
                fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            fileWriter.println("COLUMN 2");
            for(Task t : column2Ready) {
                fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            for(Task t : column2Done) {
                fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            fileWriter.println("COLUMN 3");
            for(Task t : column3Ready) {
                fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            for(Task t : column3Done) {
                fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
			fileWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    public void load()
	{
		try
		{
			Scanner fileScanner = new Scanner(new File("weeklies.txt"));
			column1Ready = new LinkedList<Task>(); //erases previous tasks or inits
            column1Done = new LinkedList<Task>();
            column2Ready = new LinkedList<Task>();
            column2Done = new LinkedList<Task>();
            column3Ready = new LinkedList<Task>();
            column3Done = new LinkedList<Task>();

            wasLaundry = Boolean.parseBoolean(fileScanner.nextLine());
            litterboxCooldown = Integer.parseInt(fileScanner.nextLine());
            String fileLine = fileScanner.nextLine();
			while (!fileLine.equals("COLUMN 2"))
			{
				fileLine = fileScanner.nextLine();
                String[] split = fileLine.split(",");
                Task newTask = new Task(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
                if(!newTask.isDone()) {
                    column1Done.add(newTask);
                }
                else {
                    column1Ready.add(newTask);
                }
			}
            while (!fileLine.equals("COLUMN 3"))
			{
				fileLine = fileScanner.nextLine();
                String[] split = fileLine.split(",");
                Task newTask = new Task(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
                if(!newTask.isDone()) {
                    column1Done.add(newTask);
                }
                else {
                    column1Ready.add(newTask);
                }
			}
            while (fileScanner.hasNextLine())
			{
				fileLine = fileScanner.nextLine();
                String[] split = fileLine.split(",");
                Task newTask = new Task(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
                if(!newTask.isDone()) {
                    column1Done.add(newTask);
                }
                else {
                    column1Ready.add(newTask);
                }
			}
			fileScanner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
