package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {

    EditText editTextTask;
    Button buttonAdd, buttonLogout;
    ListView listViewTasks;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonLogout = findViewById(R.id.buttonLogout);
        listViewTasks = findViewById(R.id.listViewTasks);

        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        // Add task
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    taskList.add("⬜ " + task);
                    adapter.notifyDataSetChanged();
                    editTextTask.setText("");
                } else {
                    Toast.makeText(ToDoActivity.this,
                            "Please enter a task!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Mark task as done on single click
        listViewTasks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        String task = taskList.get(position);
                        if (task.startsWith("⬜")) {
                            taskList.set(position,
                                    task.replace("⬜", "✅"));
                        } else {
                            taskList.set(position,
                                    task.replace("✅", "⬜"));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        // Delete task on long press
        listViewTasks.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, long id) {
                        taskList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ToDoActivity.this,
                                "Task deleted!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

        // Logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToDoActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}