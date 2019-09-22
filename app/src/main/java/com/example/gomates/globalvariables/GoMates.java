package com.example.gomates.globalvariables;

import android.app.Application;

import com.example.gomates.enums.JobSelector;

public class GoMates extends Application {

    private JobSelector jobSelected;

    public JobSelector getJobSelected() {
        return jobSelected;
    }

    public void setJobSelected(JobSelector jobSelected) {
        this.jobSelected = jobSelected;
    }
}
