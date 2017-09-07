package in.task;

import android.support.annotation.NonNull;

import java.util.List;

import in.task.data.Task;
import in.task.data.TaskRepository;

/**
 * Created by vivek on 07/09/17.
 */

public class TaskPresenterImpl implements TaskPresenterLogic, TaskListener {

    private final TaskViewLogic taskViewLogic;
    private final TaskRepository taskRepository;

    public TaskPresenterImpl(TaskViewLogic taskViewLogic) {
        this.taskViewLogic = taskViewLogic;
        this.taskRepository = new TaskRepository();
    }

    @Override
    public void createTask(Task task) {
        taskRepository.addNewTask(task, this);
    }

    @Override
    public void editTask(String id) {
        getTaskViewLogic().doEditTask(taskRepository.editTask(id));
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteTask(id, this);
    }

    @Override
    public List<Task> listAllTask() {
        return taskRepository.getAllTasks();
    }

    @Override
    public void onTaskCreated(List<Task> tasks) {
        getTaskViewLogic().onTaskCreated(tasks);
    }

    @Override
    public void onTaskDeleted(List<Task> tasks) {
        getTaskViewLogic().onTaskDeleted(tasks);
    }

    @NonNull
    private TaskViewLogic getTaskViewLogic() {
        if (taskViewLogic == null) {
            // view is not there
        }
        return taskViewLogic;
    }
}
