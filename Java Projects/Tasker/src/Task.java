public class Task {
    private int taskValue;
    private String task;

    public Task() {
        taskValue = 0;
        task = "";
    }

    public Task(int taskValue, String task) {
        setTaskValue(taskValue);
        this.task = task;
    }

    public int getTaskValue() {
        return taskValue;
    }

    public void setTaskValue(int taskValue) {
        if(taskValue > 0) {
            this.taskValue = taskValue;
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String toString() {
        return task;
    }
}
