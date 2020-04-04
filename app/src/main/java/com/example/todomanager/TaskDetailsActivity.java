package com.example.todomanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class TaskDetailsActivity extends AppCompatActivity {

    TextView title, description, startDate, deadline;
    CheckBox checkBox;
    Task task;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        final Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.details_checkbox);

            position = intent.getIntExtra("position", -1);

            Button edit = findViewById(R.id.details_edit_btn);
            Button save = findViewById(R.id.details_save_btn);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(TaskDetailsActivity.this, CreateTaskActivity.class);
                    intent1.putExtra("position", position);
                    startActivity(intent1);
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = TaskHolder.tasks.get(position);
        title.setText(task.title);
        description.setText(task.description);
        checkBox.setChecked(task.isDone);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        startDate.setText(format.format(task.startDate));
        deadline.setText(format.format(task.deadline));
    }
}