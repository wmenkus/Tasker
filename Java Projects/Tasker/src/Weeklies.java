import java.util.LinkedList;
import java.util.Random;

public class Weeklies {
    private LinkedList<Task> tasks;
    private LinkedList<Task> singleTasks;
    private Random rand;
    private int taskNum;

    public Weeklies() {
        tasks = new LinkedList<Task>();
        singleTasks = new LinkedList<Task>();
        rand = new Random();
        taskNum = 0;
    }

    public void addTask(Task task) {
        if(task.getTaskValue() == 1) {
            singleTasks.add(task);
        }
        tasks.add(task);
    }

    public Task getTask() {
        if(taskNum == 0) {
            return singleTasks.remove(rand.nextInt(singleTasks.size()));
        }
        else {
            return tasks.remove(rand.nextInt(tasks.size()));
        }
    }

    public int getTaskNum() {
        return taskNum;
    }
}
