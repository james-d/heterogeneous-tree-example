package ui;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ModelTree<T> {

    private final TreeView<T> treeView ;
    
    private final Function<T, ObservableList<? extends T>> children ;
    
    public ModelTree(T rootItem, Function<T, ObservableList<? extends T>> children, 
            Function<T, ObservableValue<String>> text,
            Function<T, PseudoClass> pseudoClassMap) {
        
        this.children = children ;
        
        treeView = new TreeView<>(createTreeItem(rootItem));
        
        treeView.setCellFactory(tv -> new TreeCell<T>() {
            
            private Set<PseudoClass> pseudoClassesSet = new HashSet<>();
            
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                textProperty().unbind();
                
                pseudoClassesSet.forEach(pc -> pseudoClassStateChanged(pc, false));
                if (empty) {
                    setText("");
                } else {
                    textProperty().bind(text.apply(item));
                    PseudoClass itemPC = pseudoClassMap.apply(item);
                    if (itemPC != null) {
                        pseudoClassStateChanged(itemPC, true);
                        pseudoClassesSet.add(itemPC);
                    }
                }
            }
        });
    }
    
    public ModelTree(T t, Function<T, ObservableList<? extends T>> children, Function<T, ObservableValue<String>> text) {
        this(t, children, text, x -> null);
    }

    public TreeView<T> getTreeView() {
        return treeView ;
    }
    
    private TreeItem<T> createTreeItem(T t) {
        TreeItem<T> item = new TreeItem<>(t);
        children.apply(t).stream().map(this::createTreeItem).forEach(item.getChildren()::add);
        
        children.apply(t).addListener((Change<? extends T> change) -> {
            while (change.next()) {
                
                if (change.wasAdded()) {
                    item.getChildren().addAll(change.getAddedSubList().stream()
                            .map(this::createTreeItem).collect(toList()));
                }
                if (change.wasRemoved()) {
                    item.getChildren().removeIf(treeItem -> change.getRemoved()
                            .contains(treeItem.getValue()));
                }
            }
        });
        
        return item ;
    }
    
}
