package com.example.task2;

public interface Aggregate {
    public Iterator getIterator();
    boolean hasNext(int i);
    Object next();
}