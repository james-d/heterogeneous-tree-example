package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department extends EmploymentUnit<Employee> {

   public Department(String name, ObservableList<Employee> subUnits) {
        super(name, subUnits, Employee::new);
    }

    public Department(String name) {
        this(name, FXCollections.observableArrayList());
    }

}
