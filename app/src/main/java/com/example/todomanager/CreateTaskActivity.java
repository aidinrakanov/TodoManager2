package com.example.todomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity {

    EditText title, description;
    Button deadline, save, cancel;
    TextView deadlinetext;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    Date taskDeadline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        deadline = findViewById(R.id.task_deadline_button);
        save = findViewById(R.id.task_save);
        cancel = findViewById(R.id.task_cancel);
        deadlinetext = findViewById(R.id.task_deadline_text);
    }

    public void onChooseDate(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadlinetext.setText(dayOfMonth+"."+ (month+1) +"."+year);
                taskDeadline = new GregorianCalendar(year,month,dayOfMonth).getTime();
            }
        },year,month,day);
        picker.show();
    }

    public void onSave(View view) {
        String tasktitle = title.getText().toString();

        if (tasktitle.equals("")){
            Toast.makeText(this,"Enter task title", Toast.LENGTH_SHORT);
            return;
        }

        String taskDescription = description.getText().toString();
        if (taskDescription.equals("")){
            Toast.makeText(this,"Enter task description", Toast.LENGTH_SHORT);
            return;
        }

       Task task = new Task();
       task.deadline = taskDeadline;
       task.title = tasktitle;
       task.descpription = taskDescription;
       task.startDate = new Date();
       task.isDone = false;

       TaskHolder.tasks.add(task);

        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK,intent);
        finish();

    }

    public void onCancel(View view) {

        setResult(RESULT_CANCELED);
        finish();

    }
}
