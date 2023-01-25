public class Task {
    private int taskValue;
    private String task;
    private boolean done;

    public Task() {
        taskValue = 0;
        task = "";
        done = false;
    }

    public Task(String task, int taskValue, boolean done) {
        this.task = task;
        setTaskValue(taskValue);
        this.done = done;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        return task;
    }
}
