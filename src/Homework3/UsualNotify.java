package Homework3;

public class UsualNotify extends NotifyBase
{
    private String notifyData;

    public UsualNotify(String notifyData)
    {
        this.notifyData = notifyData;
    }

    public String getNotifyData() {
        return notifyData;
    }

    @Override
    public void sendNotify() {
        if (notifyData.isEmpty()) {
            return;
        }

        IO.println("UsualNotify: " + notifyData);
    }
}

