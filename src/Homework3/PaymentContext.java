package Homework3;

public class PaymentContext{
    String destination;
    int amount;
    PaymentStrategy currentPaymentStrategy;
    boolean isPaymentProcessing = false;

    public PaymentContext(String destination, int amount)
    {
        this.destination = destination;
        this.amount = amount;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        if(isPaymentProcessing)
        {
            throw new RuntimeException("Can't change strategy: payment is already processing");
        }

        this.currentPaymentStrategy = paymentStrategy;
    }

    public void makePayment()
    {
        if(currentPaymentStrategy == null)
        {
            throw new RuntimeException("Invalid payment strategy");
        }

        if(isPaymentProcessing)
        {
            throw new RuntimeException("Can't make payment: payment is already processing");
        }

        if(currentPaymentStrategy == null)
        {
            throw new RuntimeException("Invalid payment strategy");
        }

        isPaymentProcessing = true;

        currentPaymentStrategy.makePayment(destination, amount);
    }

    public void checkStatus() {
        if(currentPaymentStrategy == null)
        {
            IO.println(this + " No strategy assigned");
            return;
        }

        IO.println(this + " Current status: " + currentPaymentStrategy.checkStatus());
    }

    public String getDestination() {
        return destination;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PaymentContext@" + hashCode();
    }
}
