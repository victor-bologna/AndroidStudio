package com.example.gomates.globalvariables;

import com.example.gomates.enums.JobSelector;

public class GoMates {

    private JobSelector jobSelected;
    private static GoMates instance = null;

    protected GoMates() {}

    public static GoMates getInstance() {
        if(instance == null)
            instance = new GoMates();
        return instance;
    }

    public JobSelector getJobSelected() {
        return jobSelected;
    }

    public void setJobSelected(JobSelector jobSelected) {
        this.jobSelected = jobSelected;
    }
}
