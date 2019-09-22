package com.example.gomates.enums;

public enum JobSelector {
    Motorista(1),
    Carona(2);

    public Integer jobSelected;

    JobSelector(Integer valor) {
        jobSelected = valor;
    }

    public Integer getJobSelected() {
        return jobSelected;
    }
}
