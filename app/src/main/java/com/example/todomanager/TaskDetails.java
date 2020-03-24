package com.example.todomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;

public class TaskDetails extends AppCompatActivity{

    TextView title, description, startDate,deadline;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();
        if (intent==null){
            Toast.makeText(this,"Task not found",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.details_checkbox);

            Task task = (Task) intent.getSerializableExtra("task");
             title.setText(task.title);
             description.setText(task.descpription);
             checkBox.setChecked(task.isDone);

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            startDate.setText(format.format(task.startDate));
            deadline.setText(format.format(task.deadline));
        }

    }

    public void details_edit(View view) {
    }
}
