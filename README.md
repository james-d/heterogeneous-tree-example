# heterogeneous-tree-example
Example of a JavaFX tree view that contains heterogeneous (but related) data.

A JavaFX [`TreeView`](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TreeView.html) contains `TreeItem`s 
that are all of the same type (it is homogeneous). Typically, an object model that lends itself to a tree representation
will comprise multiple different classes: in this example a `Company` has a collection of `Department`s, each of which has
a collection of `Employee`s, each of which has a collection of `Role`s. 

The simple way to represent all of these in a `TreeView` is, of course, to make each class a subclass or implementation of 
a common superclass or interface. In this case we use `EmploymentUnit` as the common superclass. Typically this will result
in lots of switching on type (using `instanceof` tests) when displaying the tree items and calculating the child nodes.
By careful use of generics, we can allow each class to use the same method signature for representing it's child objects, and
use listeners on the list of children to maintain the view state (i.e. the `TreeItem`s) if the underlying model changes. 

This example uses a `ModelTree` class to map the model into a `TreeView`. The `ModelTree` constructor takes a number of functions
that describe how to map a model instance to its list of children, to an observable string for display in the cell, and (optionally)
to [CSS `PseudoClass`](http://docs.oracle.com/javase/8/javafx/api/javafx/css/PseudoClass.html) instances to allow for different
types to be displayed in different styles. The model used here lends itself to easy provision of these functions by method
references, but less amenable models should not cause too much trouble.

Obvious enhancements could be made to, for example, allow editing in the tree.
