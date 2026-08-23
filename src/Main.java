import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Homework1.*;
import Homework2.*;
import static Homework2.Book.of;

void main() {
    int HOMEWORK_NUMBER = 2;

    switch (HOMEWORK_NUMBER)
    {
        case 1 ->
        {
            Animal animal = new Animal("New animal name");
            animal.getAnimalName().sayName();
            IO.println(animal);
        }
        case 2 ->
        {
            Path path = Paths.get("src/Homework2/Students.xml");

            Student testStudent1 = new Student("Leha", List.of(
                    of("BookNull", null, null),
                    of("Book2", 20, 1995),
                    of("Book3", 20, 2022),
                    of("Book4", 15, 2021),
                    of("Book5", 20, 1990)
            ));
            Student testStudent2 = new Student("Sasha", List.of(
                    of("Book6", 66, 2006),
                    of("Book7", 77, 2007),
                    of("Book8", 88, 2008),
                    of("Book9", 99, 2009),
                    of("Book10", 10, 2010)
            ));
            Student testStudent3 = new Student("Zhorik", List.of(
                    of("BookABCD", 333, 233),
                    of("BookABBB", 212, 333),
                    of("Book8", 88, 2008),
                    of("Book9", 99, 2009),
                    of("Book10", 10, 2010)
            ));


            List<Student> writeStudents = new ArrayList<>();
            writeStudents.add(testStudent1);
            writeStudents.add(testStudent2);
            writeStudents.add(testStudent3);

            StudentParser.write(path, writeStudents);

            List<Student> studentList = new ArrayList<>();
            StudentParser.read(path, studentList, true);

            var stream = studentList.stream();

            stream
                    .peek(IO::println)
                    //.peek(s -> IO.println(s.getBookList()))
                    .peek(s -> s.getBookList())
                    .flatMap((el) -> { return el.getBookList().stream(); } )
                    .sorted(Comparator.comparing( Book::getNumberOfPages, Comparator.nullsLast(Comparator.naturalOrder())))
                    .distinct()
                    .filter((b) -> {return b.getReleaseYear() != null && b.getReleaseYear() > 2000; } )
                    .limit(3)
                    .map(Book::getReleaseYear)
                    .findAny()
                    .ifPresentOrElse(IO::println, () -> System.out.println("The book doesn't have a valid ReleaseYear") );
        }
    }



}
