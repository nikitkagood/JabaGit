package Homework2;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Student {
    String studentName = "default name";

    private List<Book> bookList = new ArrayList<>();

    public Student() {}

    public Student(String studentName, List<Book> bookList)
    {
        this.studentName = studentName;
        this.bookList = bookList;
    }

    public String toString() {
        return studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentName, student.studentName) && Objects.equals(getBookList(), student.getBookList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, getBookList());
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book)
    {
        if(book == null)
        {
            throw new IllegalArgumentException("Null object (Book)");
        }
        bookList.add(book);
    }

    public String getStudentName() {
        return studentName;
    }
}

