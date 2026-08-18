package Homework2;

import java.util.List;
import java.util.ArrayList;

public class Student {
    Student() {}

    Student(String name, List<Book> bookList)
    {

    }

    String name;
    List<Book> bookList = new ArrayList<>();

    public String toString() {
        return name;
    }
}


