package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.adapter.RecyclerViewTaskAdapter;
import fr.unice.polytech.jcancela.tobeortohave.model.Task;

/**
 * Created by Joel CANCELA VAZ on 15/05/2017.
 */

public class ShoppingListFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewTaskAdapter recyclerViewAdapter;
    private EditText addTaskBox;
    private DatabaseReference databaseReference;
    private List<Task> allTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        allTask = new ArrayList<Task>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        addTaskBox = (EditText) view.findViewById(R.id.add_task_box);
        recyclerView = (RecyclerView) view.findViewById(R.id.task_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Button addTaskButton = (Button) view.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTask = addTaskBox.getText().toString();
                if (TextUtils.isEmpty(enteredTask)) {
                    Toast.makeText(getActivity(), "You must enter a task first", Toast.LENGTH_LONG).show();
                    return;
                }
                Task taskObject = new Task(enteredTask);
                databaseReference.push().setValue(taskObject);
                addTaskBox.setText("");
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllTask(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                taskDeletion(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getAllTask(DataSnapshot dataSnapshot) {
        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String taskTitle = singleSnapshot.getValue(String.class);
            allTask.add(new Task(taskTitle));
            recyclerViewAdapter = new RecyclerViewTaskAdapter(getActivity(), allTask);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        updateCount();
    }

    private void taskDeletion(DataSnapshot dataSnapshot) {
        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
            String taskTitle = singleSnapshot.getValue(String.class);
            for (int i = 0; i < allTask.size(); i++) {
                if (allTask.get(i).getTask().equals(taskTitle)) {
                    allTask.remove(i);
                }
            }
            recyclerViewAdapter.notifyDataSetChanged();
            recyclerViewAdapter = new RecyclerViewTaskAdapter(getActivity(), allTask);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        updateCount();
    }

    private void updateCount() {
        if (getActivity() != null) {
            SharedPreferences settings = this.getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("todolist_size", allTask.size());
            editor.apply();
        }
    }
}
