package Homework3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.Deque;

interface DataBaseInterface {
    public void makeRequest(String request, StringBuilder responseRef);
}

public class ProxyDataBaseQueue implements DataBaseInterface {
    record RequestPair(String request, StringBuilder responseRef)
    {}

    private Deque<RequestPair> queue = new ArrayDeque<>();

    private DataBase dataBase;

    public ProxyDataBaseQueue(DataBase db)
    {
        dataBase = db;
    }

    //Indirect request placeholder
    @Override
    public void makeRequest(String request, StringBuilder responseRef) {
        queue.addLast(new RequestPair(request, responseRef));
    }

    public void executeQueue()
    {
        for (RequestPair pair : queue) {
            pair.responseRef.append(dataBase.getData(pair.request));
        }
    }

}
