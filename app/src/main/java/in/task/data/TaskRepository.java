package in.task.data;

import java.util.List;

import in.task.db.StorageService;
import in.task.TaskListener;

/**
 * Created by vivek on 07/09/17.
 */

public class TaskRepository {

    public void addNewTask(Task task, TaskListener taskListener) {
        StorageService.getInstance().storeTask(task);
        taskListener.onTaskCreated(getAllTasks());
    }

    public void deleteTask(String id, TaskListener taskListener) {
        StorageService.getInstance().deleteTask(id, Task.class);
        taskListener.onTaskDeleted(getAllTasks());
    }

    public Task editTask(String id){
        return StorageService.getInstance().getTaskByID(id);
    }

    public List<Task> getAllTasks() {
        return StorageService.getInstance().getAllTasks();
    }
}
