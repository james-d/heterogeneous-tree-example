package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Company extends EmploymentUnit<Department> {

    public Company(String name, ObservableList<Department> subUnits) {
        super(name, subUnits, Department::new);
    }

    public Company(String name) {
        this(name, FXCollections.observableArrayList());
    }

}
