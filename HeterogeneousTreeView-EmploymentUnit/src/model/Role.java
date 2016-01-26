package model;

import javafx.collections.FXCollections;

public class Role extends EmploymentUnit<EmploymentUnit<?>> {

    public Role(String name) {
        super(name, FXCollections.emptyObservableList());
    }

    @Override
    public void createAndAddSubUnit(String name) {
        throw new UnsupportedOperationException("Role has no sub units");
    }
}
