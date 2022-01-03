import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Monthlies {
    private LinkedList<Task> readyTasks;
    private LinkedList<Task> doneTasks;
    private Random rand;
    public final int MONTHLY_TASKS = 1;
    private int taskNum;

    public Monthlies() {
        load();
        rand = new Random();
    }

    public void getTasks(LinkedList<Task> dailyTasks) {
        taskNum--;
        if(taskNum == 0) {
            if(readyTasks.isEmpty()) {
                resetTasks();
            }
            Task task = pullTask();
            taskNum += task.getTaskValue();
            dailyTasks.add(task);
        }
        else {
            dailyTasks.add(new Task("No monthly", 1, true));
        }
    }

    private void resetTasks() {
        while(doneTasks.size() > 0) {
            Task task= doneTasks.removeFirst();
            task.setDone(false);
            readyTasks.add(task);
        }
    }

    private Task pullTask() {
        int num = rand.nextInt(readyTasks.size());
        Task task = readyTasks.remove(num);
        task.setDone(true);
        doneTasks.add(task);
        return task;
    }

    public void save()
	{
		try
		{
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream("C:\\Users\\wmenk\\OneDrive\\Desktop\\java-projects\\Java Projects\\Tasker\\monthlies.txt"));
            fileWriter.println(taskNum);
			for(Task t : readyTasks)
            {
				fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
            }
            for(Task t : doneTasks) {
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
			Scanner fileScanner = new Scanner(new File("C:\\Users\\wmenk\\OneDrive\\Desktop\\java-projects\\Java Projects\\Tasker\\monthlies.txt"));
			readyTasks = new LinkedList<Task>(); //erases previous tasks or inits
            doneTasks = new LinkedList<Task>();
            taskNum = Integer.parseInt(fileScanner.nextLine());

			while (fileScanner.hasNextLine())
			{
				String fileLine = fileScanner.nextLine();
                String[] split = fileLine.split(",");
                Task newTask = new Task(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
                if(newTask.isDone()) {
                    doneTasks.add(newTask);
                }
                else {
                    readyTasks.add(newTask);
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
