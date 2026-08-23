package Homework2;

import java.util.Objects;

public class Book {
    private String bookName;
    private Integer numberOfPages;
    private Integer releaseYear;

    public Book(){}

    public Book(String bookName, Integer numberOfPages, Integer releaseYear){
        this.bookName = bookName;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
    }

    public static Book of(String name, Integer numberOfPages, Integer releaseYear) {
        return new Book(name, numberOfPages, releaseYear);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", releaseYear=" + releaseYear +
                '}';
    }

    //full equality check, same name doesn't mean same book
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getBookName(), book.getBookName()) && Objects.equals(getNumberOfPages(), book.getNumberOfPages()) && Objects.equals(getReleaseYear(), book.getReleaseYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookName(), getNumberOfPages(), getReleaseYear());
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }


}
