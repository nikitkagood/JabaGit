package Homework2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StudentParser {
    public static void write(Path path, List<Student> writeStudents) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element root = doc.createElement("students");
            doc.appendChild(root);

            for (var student : writeStudents) {
                Element studentNode = doc.createElement("Student");
                root.appendChild(studentNode);

                Element studentName = doc.createElement("studentName");
                studentName.appendChild(doc.createTextNode(student.getStudentName()));
                studentNode.appendChild(studentName);

                Element booksContainer = doc.createElement("bookList");
                studentNode.appendChild(booksContainer);

                for (Book book : student.getBookList()) {
                    Element bookNode = doc.createElement("book");

                    Element title = doc.createElement("bookName");
                    title.appendChild(doc.createTextNode(book.getBookName()));
                    bookNode.appendChild(title);

                    if (book.getReleaseYear() != null) {
                        Element year = doc.createElement("numberOfPages");
                        year.appendChild(doc.createTextNode(book.getNumberOfPages().toString()));
                        bookNode.appendChild(year);
                    }

                    if (book.getReleaseYear() != null) {
                        Element year = doc.createElement("releaseYear");
                        year.appendChild(doc.createTextNode(book.getReleaseYear().toString()));
                        bookNode.appendChild(year);
                    }

                    booksContainer.appendChild(bookNode);
                }
            }

            // Запись структуры в файл
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Делает XML красивым с отступами
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path.toString()));

            transformer.transform(source, result);
            System.out.println("XML write success");

        } catch (Exception e) {
            System.err.println("XML write error: " + e.getMessage());
        }
    }

    public static void read(Path path, List<Student> destination, boolean clearDest) {
        if(clearDest) {
            destination.clear();
        }

        try {
            File file = new File(path.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            NodeList studentNodes = doc.getElementsByTagName("Student");

            for (int i = 0; i < studentNodes.getLength(); i++) {
                Element studentElement = (Element) studentNodes.item(i);

                String studentName = "Unknown student";
                NodeList studentNameList = studentElement.getElementsByTagName("studentName");
                if (studentNameList.getLength() > 0) {
                    studentName = studentNameList.item(0).getTextContent();
                }

                List<Book> bookList = new ArrayList<>();

                NodeList bookListNodes = studentElement.getElementsByTagName("bookList");
                if (bookListNodes.getLength() > 0) {
                    Element bookListElement = (Element) bookListNodes.item(0);

                    NodeList bookNodes = bookListElement.getElementsByTagName("book");

                    for (int j = 0; j < bookNodes.getLength(); j++) {
                        Element bookElement = (Element) bookNodes.item(j);

                        String bookName = "Unknown name";
                        NodeList bookNameList = bookElement.getElementsByTagName("bookName");
                        if (bookNameList.getLength() > 0) {
                            bookName = bookNameList.item(0).getTextContent();
                        }

                        int numberOfPages = 0;
                        NodeList pagesList = bookElement.getElementsByTagName("numberOfPages");
                        if (pagesList.getLength() > 0) {
                            numberOfPages = Integer.parseInt(pagesList.item(0).getTextContent());
                        }

                        Integer releaseYear = null;
                        NodeList yearList = bookElement.getElementsByTagName("releaseYear");
                        if (yearList.getLength() > 0 && !yearList.item(0).getTextContent().isEmpty()) {
                            releaseYear = Integer.parseInt(yearList.item(0).getTextContent());
                        }

                        bookList.add(new Book(bookName, numberOfPages, releaseYear));
                    }
                }

                destination.add(new Student(studentName, bookList));
            }
        } catch (Exception e) {
            System.err.println("XML read error: " + e.getMessage());
        }
    }
}