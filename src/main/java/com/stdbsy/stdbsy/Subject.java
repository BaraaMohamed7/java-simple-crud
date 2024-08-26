package com.stdbsy.stdbsy;

import java.util.Observer;

public interface Subject {

    void registerObserver(com.stdbsy.stdbsy.Observer o);

    void removeObserver(com.stdbsy.stdbsy.Observer o);

    void notifyObservers();
}
