package Homework3;

import java.util.Objects;

public class OnlineServicePayment implements PaymentStrategy {
    private final String serviceName;
    private final String paymentID;

    public OnlineServicePayment(String serviceName, String paymentID) {
        this.serviceName = serviceName;
        this.paymentID = paymentID;
    }

    protected static final class OnlineServiceResponse
    {
        String data;

        public static final OnlineServiceResponse ACCEPTED = new OnlineServiceResponse("ACCEPTED");
        public static final OnlineServiceResponse DECLINED = new OnlineServiceResponse("DECLINED");
        public static final OnlineServiceResponse SERVER_NO_RESPONSE = new OnlineServiceResponse("SERVER_NO_RESPONSE");
        public static final OnlineServiceResponse NO_TRANSACTION_FOUND = new OnlineServiceResponse("NO_TRANSACTION_FOUND");

        private OnlineServiceResponse(String data)
        {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            OnlineServiceResponse that = (OnlineServiceResponse) o;
            return Objects.equals(data, that.data);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(data);
        }

        @Override
        public String toString() {
            return data;
        }
    }

    protected OnlineServiceResponse makePaymentViaOnlineService(String destination, int amount)
    {
        //Placeholder
        return OnlineServiceResponse.ACCEPTED;
    }

    protected OnlineServiceResponse queryPaymentStatus(String paymentID)
    {
        //Placeholder
        return OnlineServiceResponse.NO_TRANSACTION_FOUND;
    }


    @Override
    public void makePayment(String destination, int amount) {
        var result = makePaymentViaOnlineService(destination, amount);

        switch (result)
        {
            case OnlineServiceResponse r when r.equals(OnlineServiceResponse.ACCEPTED) -> IO.println("Online service payment: Accepted");
            case OnlineServiceResponse r when r.equals(OnlineServiceResponse.DECLINED) -> IO.println("Online service payment: Declined");
            case OnlineServiceResponse r when r.equals(OnlineServiceResponse.SERVER_NO_RESPONSE) -> IO.println("Online service payment: No response from server");
            case OnlineServiceResponse r when r.equals(OnlineServiceResponse.NO_TRANSACTION_FOUND) -> IO.println("Online service payment: No transaction found");
            case null, default -> IO.println("Online service payment: unknown response");
        }
    }

    @Override
    public String checkStatus() {
        return queryPaymentStatus(this.paymentID).toString();
    }

}
