import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Weeklies {
    private LinkedList<Task> tasks;
    private LinkedList<Task> readyTasks;
    private LinkedList<Task> singleTasks;
    private Random rand;
    private final int WEEKLY_TASKS = 3;
    private int taskNum;

    public Weeklies() {
        readFromFile("weeklies.txt");
        rand = new Random();
        taskNum = 0;
    }

    public Task getTask() {
        if(taskNum == WEEKLY_TASKS - 1) {
            int num = rand.nextInt(singleTasks.size());
            singleTasks.get(num).setDone(true);
            readyTasks.remove(singleTasks.get(num));
            return singleTasks.remove(num);
        }
        else if(taskNum < WEEKLY_TASKS) {
            int num = rand.nextInt(readyTasks.size());
            readyTasks.get(num).setDone(true);
            return readyTasks.remove(num);
        }
        else return null;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void printToFile(String aFilename)
	{
		try
		{
			PrintWriter fileWriter = new PrintWriter(new FileOutputStream(aFilename));
			for(Task t : tasks)
				fileWriter.println(t.getTask()+","+t.getTaskValue()+","+t.isDone());
			fileWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    public void readFromFile(String aFilename)
	{
		try
		{
			Scanner fileScanner = new Scanner(new File(aFilename));
			tasks = new LinkedList<Task>(); //erases previous tasks or inits
            readyTasks = new LinkedList<Task>();
            singleTasks = new LinkedList<Task>();

			while (fileScanner.hasNextLine())
			{
				String fileLine = fileScanner.nextLine();
				String[] split = fileLine.split(",");
                Task newTask = new Task(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
                tasks.add(newTask);
                if(!newTask.isDone()) {
                    readyTasks.add(newTask);
                }
			}
			fileScanner.close();

            for(Task t : readyTasks) {
                if(t.getTaskValue() == 1) {
                    singleTasks.add(t);
                }
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
