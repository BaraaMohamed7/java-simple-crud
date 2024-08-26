package com.stdbsy.stdbsy;

import com.stdbsy.stdbsy.models.Product;
import javafx.collections.ObservableList;

public interface Observer {
    public void update(ObservableList<Product> observableList);
}
