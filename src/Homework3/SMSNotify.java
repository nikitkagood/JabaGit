package Homework3;


public class SMSNotify extends NotifyBase
{
    private String smsData;
    private UsualNotify usualNotify;

    public SMSNotify(UsualNotify usualNotify, String smsData)
    {
        this.usualNotify = usualNotify;
        this.smsData = smsData;
    }

    @Override
    public void sendNotify() {
        usualNotify.sendNotify();

        if (smsData.isEmpty()) {
            return;
        }

        IO.println("SMSNotify: " + smsData);
    }
}
