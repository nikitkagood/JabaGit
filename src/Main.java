import java.util.List;
import java.util.ArrayList;

import Homework1.*;
import Homework2.*;
import static Homework2.Book.of;
import Homework3.*;
import com.sun.net.httpserver.Request;

void main() {
    int HOMEWORK_NUMBER = 4;

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
        case 4 ->
        {
            class Deadlock {
                public synchronized void deadlockExample(Deadlock other) {
                    IO.println("Print something");

                    //to avoid StackOverflow even before deadlock
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    other.deadlockExample(this);
                }
            }

            Deadlock dl1 = new Deadlock();
            Deadlock dl2 = new Deadlock();

            Thread th_dl1 = new Thread(() -> dl1.deadlockExample(dl2));
            Thread th_dl2 = new Thread(() -> dl2.deadlockExample(dl1));

            //th_dl1.start();
            //th_dl2.start();


            //Livelock
            ReentrantLock lock = new ReentrantLock();

            Runnable task_ll = () -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {

                        IO.println("Prepare to work");

                        if(lock.tryLock())
                        {
                            lock.lock();

                            Thread.sleep(10);
                            IO.println("\n\n" + "ACTUALLY WORK" + "\n\n");


                            lock.unlock();
                        }
                    }


                }
                catch (IllegalThreadStateException e) {
                    System.err.println(e.getMessage());
                }
                catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            };



            Thread th_ll1 = new Thread(task_ll);
            Thread th_ll2 = new Thread(task_ll);

//            th_ll1.start();
//            th_ll2.start();


            //Synchronized 1 - 2
            Semaphore semaphore = new Semaphore(1, true);

            Runnable task1 = () -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        semaphore.acquire();
                        IO.println("1");
                        Thread.sleep(400);
                        semaphore.release();
                    }

                }
                catch (IllegalThreadStateException e) {
                    System.err.println(e.getMessage());
                }
                catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                finally {
                    if(semaphore.availablePermits() <= 0) {
                        semaphore.release();
                    }
                }
            };

            Runnable task2 = () -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        semaphore.acquire();
                        IO.println("2");
                        semaphore.release();
                    }
                }
                catch (IllegalThreadStateException e) {
                    System.err.println(e.getMessage());
                }
                catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                finally {
                    if(semaphore.availablePermits() <= 0) {
                        semaphore.release();
                    }
                }
            };

            Thread th1 = new Thread(task1);
            Thread th2 = new Thread(task2);

//           th1.start();
//           th2.start();


        }

    }
}
