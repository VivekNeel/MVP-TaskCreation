package in.task.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import in.task.R;

/**
 * Created by vivek on 07/09/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private final Context mContext;
    private final ITaskCallbacks iTaskCallbacks;

    public TaskAdapter(List<Task> taskList, Context context, ITaskCallbacks iTaskCallbacks) {
        this.taskList = taskList;
        this.mContext = context;
        this.iTaskCallbacks = iTaskCallbacks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        holder.taskTv.setText(taskList.get(holder.getAdapterPosition()).getContent());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTaskCallbacks.deleteTask(taskList.get(holder.getAdapterPosition()).getId());
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTaskCallbacks.doEdit(taskList.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    public void updateList(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskTv;
        private final ImageButton editButton, deleteButton;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskTv = (TextView) itemView.findViewById(R.id.task);
            editButton = (ImageButton) itemView.findViewById(R.id.editButton);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface ITaskCallbacks {
        void deleteTask(@NonNull String id);

        void doEdit(@NonNull String id);
    }
}
