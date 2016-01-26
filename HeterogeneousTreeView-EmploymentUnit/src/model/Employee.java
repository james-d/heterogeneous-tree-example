package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee extends EmploymentUnit<Role> {

    public Employee(String name, ObservableList<Role> subUnits) {
        super(name, subUnits, Role::new);
    }

   public Employee(String name) {
        this(name, FXCollections.observableArrayList());
    }

}
