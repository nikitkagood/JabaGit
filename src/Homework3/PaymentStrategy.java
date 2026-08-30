package Homework3;

public interface PaymentStrategy {
    void makePayment(String destination, int amount);
    String checkStatus();
}

