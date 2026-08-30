import java.util.List;
import java.util.ArrayList;

import Homework1.*;
import Homework2.*;
import static Homework2.Book.of;
import Homework3.*;
import com.sun.net.httpserver.Request;

void main() {
    int HOMEWORK_NUMBER = 3;

    switch (HOMEWORK_NUMBER) {
        case 1 -> {
            Animal animal = new Animal("New animal name");
            animal.getAnimalName().sayName();
            IO.println(animal);
        }
        case 2 -> {
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
                    .flatMap((el) -> {
                        return el.getBookList().stream();
                    })
                    .sorted(Comparator.comparing(Book::getNumberOfPages, Comparator.nullsLast(Comparator.naturalOrder())))
                    .distinct()
                    .filter((b) -> {
                        return b.getReleaseYear() != null && b.getReleaseYear() > 2000;
                    })
                    .limit(3)
                    .map(Book::getReleaseYear)
                    .findAny()
                    .ifPresentOrElse(IO::println, () -> System.out.println("The book doesn't have a valid ReleaseYear"));
        }
        case 3 -> {
            IO.println("Strategy");

            PaymentContext payment1 = new PaymentContext("somewhere", 30);
            payment1.setPaymentStrategy(new OnlineServicePayment("PayPal", "a1B3c45dE67"));
            payment1.checkStatus();
            payment1.makePayment();

            PaymentContext payment2 = new PaymentContext("somewhere else", 20);
            payment2.checkStatus();
            payment2.setPaymentStrategy(new CardPayment("1111-1111-1111-1111", 123));
            payment2.makePayment();


            IO.println("\n" + "CoR");

            Window window = new Window();
            Panel panel = new Panel(window);
            Button button = new Button(panel);

            UICommonInterface.UIRequest dummyRequest = new UICommonInterface.UIRequest();
            panel.doesHandle = true;
            button.handle(dummyRequest);

            IO.println("\n" + "Builder");

            VehicleBuilder vb = new VehicleBuilder("Just a car");
            vb.setFrame("Some frame");
            vb.setEngine("Some engine");
            vb.setWheels(4);
            Vehicle v = vb.getObject();
            IO.println(v);


            IO.println("\n" + "Proxy");

            DataBase db = new DataBase(List.of(
                    Map.entry("Request1", "Response1_AAA"),
                    Map.entry("Request2", "Response2_BBB"),
                    Map.entry("Request3", "Response3"),
                    Map.entry("Request4", "Response4")
            ));

            ProxyDataBaseQueue proxyDb = new ProxyDataBaseQueue(db);

            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();

            proxyDb.makeRequest("Request1", str1);
            proxyDb.makeRequest("Request2", str2);
            proxyDb.executeQueue();

            IO.println(str1.toString());
            IO.println(str2.toString());


            IO.println("\n" + "Decorator");

            UsualNotify notify = new UsualNotify("SomeNotify");
            SMSNotify smsNotify = new SMSNotify(notify, "OtherNotify");
            smsNotify.sendNotify();

            IO.println("\n" + "Adapter");

            PicturePainter pp = new PicturePainter();

            SimplePicture sp = new SimplePicture("PictureData");
            ComplexPicture cp = new ComplexPicture(sp);

            pp.paintPicture(cp); //only accepts complex pictures
        }


    }
}
