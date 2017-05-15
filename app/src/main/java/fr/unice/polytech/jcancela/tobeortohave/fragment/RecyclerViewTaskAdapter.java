package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.database.Task;

/**
 * Created by Joel CANCELA VAZ on 15/05/2017.
 */

public class RecyclerViewTaskAdapter extends RecyclerView.Adapter<RecyclerViewTaskAdapter.RecyclerViewHolders> {
    private List<Task> task;
    protected Context context;

    public RecyclerViewTaskAdapter(Context context, List<Task> task) {
        this.task = task;
        this.context = context;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, task);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.categoryTitle.setText(task.get(position).getTask());
    }
    @Override
    public int getItemCount() {
        return this.task.size();
    }


    public class RecyclerViewHolders extends RecyclerView.ViewHolder{
        public ImageView markIcon;
        public TextView categoryTitle;
        public ImageView deleteIcon;
        private List<Task> taskObject;
        public RecyclerViewHolders(final View itemView, final List<Task> taskObject) {
            super(itemView);
            this.taskObject = taskObject;
            categoryTitle = (TextView)itemView.findViewById(R.id.task_title);
            markIcon = (ImageView)itemView.findViewById(R.id.task_icon);
            deleteIcon = (ImageView)itemView.findViewById(R.id.task_delete);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Delete icon has been clicked", Toast.LENGTH_LONG).show();
                    String taskTitle = taskObject.get(getAdapterPosition()).getTask();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query applesQuery = ref.orderByChild("task").equalTo(taskTitle);
                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Sky", "onCancelled", databaseError.toException());
                        }
                    });
                }
            });
        }
    }
}

