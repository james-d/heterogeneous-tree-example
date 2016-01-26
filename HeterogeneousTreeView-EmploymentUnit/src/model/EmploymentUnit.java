package model;

import java.util.function.Function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmploymentUnit<T extends EmploymentUnit<?>> {
    
    private final StringProperty name = new SimpleStringProperty();
    
    private final Function<String, T> subUnitSupplier ;
    
    private final ObservableList<T> subUnits ;

    public EmploymentUnit(String name, ObservableList<T> subUnits, Function<String, T> subUnitSupplier) {
        this.subUnits = subUnits ;
        this.subUnitSupplier = subUnitSupplier ;
        setName(name);
    }

    public EmploymentUnit(String name, ObservableList<T> subUnits) {
        this(name, subUnits, n -> null);
    }
    
    public EmploymentUnit(String name, Function<String, T> subUnitSupplier) {
        this(name, FXCollections.observableArrayList(), subUnitSupplier);
    }
    
    public EmploymentUnit(String name) {
        this(name, FXCollections.observableArrayList(), n -> null) ;
    }

    public final StringProperty nameProperty() {
        return this.name;
    }
    
    public final String getName() {
        return this.nameProperty().get();
    }
    
    public final void setName(final String name) {
        this.nameProperty().set(name);
    }

    public ObservableList<T> getSubUnits() {
        return subUnits;
    }
    
    public void createAndAddSubUnit(String name) {
        getSubUnits().add(subUnitSupplier.apply(name));
    }

}
