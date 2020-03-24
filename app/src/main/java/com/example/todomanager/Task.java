package com.example.todomanager;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public String title;
    public String descpription;
    public Date startDate;
    public Date deadline;
    public boolean isDone;
}
