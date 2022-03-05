package com.example.dbFirstPrc.Model;

import javafx.beans.property.*;

import java.util.Date;


public class Department {
    private StringProperty Name;
    private StringProperty Num;
    private StringProperty Ssn;
    private ObjectProperty<Date> Date;;

    public Department(String name,
                      String num,
                      String ssn,
                      Date date) {
        Name = new SimpleStringProperty(name);
        Num = new SimpleStringProperty(num);
        Ssn = new SimpleStringProperty(ssn);
        Date = new SimpleObjectProperty<Date>(date);
    }



    public String getName() {
        return Name.get();
    }

    public StringProperty NameProperty() {
        return Name;
    }

    public void setName(String fname) {
        this.Name.set(fname);
    }

    public String getNum() {
        return Num.get();
    }

    public StringProperty NumProperty() {
        return Num;
    }

    public void setNum(String num) {
        this.Num.set(num);
    }

    public String getSsn() {
        return Ssn.get();
    }

    public StringProperty ssnProperty() {
        return Ssn;
    }

    public void setSsn(String ssn) {
        this.Ssn.set(ssn);
    }

    public Date getDate() {
        return Date.get();
    }

    public ObjectProperty<Date> DateProperty() {
        return Date;
    }

    public void setDate(Date bdate) {
        this.Date.set(bdate);
    }

}
