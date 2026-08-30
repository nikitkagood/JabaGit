package Homework3;

import java.util.Objects;

public class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final int cvv;
    private BankResponse latestBankResponse = BankResponse.NOT_STARTED;

    protected static final class BankResponse
    {
        String data;

        public static final BankResponse NOT_STARTED = new BankResponse("NOT_STARTED");
        public static final BankResponse ACCEPTED = new BankResponse("ACCEPTED");
        public static final BankResponse PROCESSING = new BankResponse("PROCESSING");
        public static final BankResponse DECLINED = new BankResponse("DECLINED");

        private BankResponse(String data)
        {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            BankResponse that = (BankResponse) o;
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

    public CardPayment(String cardNumber, int cvv)
    {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    protected BankResponse makePaymentViaBank(String destination, int amount)
    {
        //Placeholder
        return latestBankResponse = BankResponse.ACCEPTED;
    }

    @Override
    public void makePayment(String destination, int amount)
    {
        var result = makePaymentViaBank(destination, amount);

        switch (result)
        {
            case BankResponse r when r.equals(BankResponse.ACCEPTED) -> IO.println("Bank card payment: Accepted");
            case BankResponse r when r.equals(BankResponse.PROCESSING) -> IO.println("Bank card  payment: Processing");
            case BankResponse r when r.equals(BankResponse.DECLINED) -> IO.println("Bank card payment: Declined");
            case null, default -> IO.println("Bank card payment: unknown response");
        }

        latestBankResponse = result;

    }

    @Override
    public String checkStatus() {
        return latestBankResponse.toString();
    }

}
