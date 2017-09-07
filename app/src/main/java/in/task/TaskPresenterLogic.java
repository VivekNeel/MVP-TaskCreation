package in.task;

import android.support.annotation.NonNull;

import java.util.List;

import in.task.data.Task;

/**
 * Created by vivek on 07/09/17.
 */

public interface TaskPresenterLogic {

    void createTask(Task task);

    void editTask(@NonNull String id);

    void deleteTask(@NonNull String id);

    List<Task> listAllTask();
}
