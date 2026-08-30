package Homework3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase implements DataBaseInterface
{
    private HashMap<String, String> dataBase = new HashMap<>();

    public DataBase(List<Map.Entry<String, String>> db)
    {
        for (Map.Entry<String, String> entry : db) {
            dataBase.put(entry.getKey(), entry.getValue());
        }
    }

    public String getData(String request)
    {
        return dataBase.get(request);
    }

    //Direct request placeholder
    @Override
    public void makeRequest(String request, StringBuilder responseRef) {
        responseRef.append(dataBase.get(request));
    }
}
