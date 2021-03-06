package in.task;

import java.util.List;

import in.task.data.Task;

/**
 * Created by vivek on 07/09/17.
 */

public interface TaskViewLogic {

    void onTaskCreated(List<Task> tasks);

    void onTaskDeleted(List<Task> tasks);

    void doEditTask(Task task);
}
