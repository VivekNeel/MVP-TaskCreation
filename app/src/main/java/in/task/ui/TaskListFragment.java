package in.task.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import in.task.R;
import in.task.TaskPresenterImpl;
import in.task.TaskViewLogic;
import in.task.data.Task;
import in.task.data.TaskAdapter;

/**
 * Created by vivek on 08/09/17.
 */

public class TaskListFragment extends Fragment implements TaskViewLogic, TaskAdapter.ITaskCallbacks {

    private Context context;
    private RecyclerView taskRV;
    private TaskAdapter taskAdapter;
    private TaskPresenterImpl taskPresenter;
    private AlertDialog alertDialog;
    private Button saveTaskButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_task_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskRV = (RecyclerView) view.findViewById(R.id.taskRv);
        taskRV.setLayoutManager(new LinearLayoutManager(context));
        taskPresenter = new TaskPresenterImpl(this);
        taskAdapter = new TaskAdapter(taskPresenter.listAllTask(), context, this);
        taskRV.setAdapter(taskAdapter);

        Button createTask = (Button) view.findViewById(R.id.createTaskButton);
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(false, null);
            }
        });
    }

    @Override
    public void onTaskCreated(List<Task> tasks) {
        taskAdapter.updateList(tasks);
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void doEdit(@NonNull String id) {
        taskPresenter.editTask(id);
    }

    @Override
    public void onTaskDeleted(List<Task> tasks) {
        taskAdapter.updateList(tasks);
    }

    @Override
    public void doEditTask(Task task) {
        showDialog(true, task);
    }

    private void showDialog(boolean shouldDoEdit, Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View dialogView = layoutInflater.inflate(R.layout.dialog_task_create, null);

        builder.setView(dialogView);
        alertDialog = builder.create();

        final TextInputEditText taskField = (TextInputEditText) dialogView.findViewById(R.id.taskEt);
        final TextInputEditText taskIdField = (TextInputEditText) dialogView.findViewById(R.id.taskIDet);

        saveTaskButton = (Button) dialogView.findViewById(R.id.saveTaskButton);

        if (shouldDoEdit) {
            taskField.setText(task.getContent());
            taskIdField.setText(task.getId());
            taskIdField.setFocusable(false);
        }
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskIdField.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Please enter id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (taskField.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Please enter task", Toast.LENGTH_SHORT).show();
                    return;
                }
                Task task = new Task();
                task.setId(taskIdField.getText().toString());
                task.setContent(taskField.getText().toString());
                taskPresenter.createTask(task);
                saveTaskButton.setEnabled(false);
                saveTaskButton.setText("Saving task..");
            }
        });

        alertDialog.show();
    }

    @Override
    public void deleteTask(@NonNull String id) {
        taskPresenter.deleteTask(id);
    }

}
