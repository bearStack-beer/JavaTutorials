package com.beercoding;

public class InterfaceVsAbstractDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. ABSTRACT CLASS + INTERFACES IN ACTION ===");
        StripePayment stripe = new StripePayment("TXN-9001");
        stripe.processPayment(250.00);
        stripe.issueRefund(50.00);
        stripe.printReceipt(); // Resolves the Diamond Problem between Refundable & Auditable!

        System.out.println("\n=== 2. INTERFACE CONSTANTS & STATIC METHOD ===");
        System.out.println("Max Retries Allowed: " + Refundable.MAX_RETRIES);
        Refundable.printPolicy(); // Static method called directly on Interface name!
    }
}

// 1. ABSTRACT CLASS: For closely related classes (IS-A), shared state & Constructor!
abstract class PaymentProcessor {
    protected String transactionId; // Non-final instance variable (State is allowed!)

    // YES! Abstract classes CAN have a Constructor (invoked via super() by child class)
    public PaymentProcessor(String transactionId) {
        this.transactionId = transactionId;
        System.out.println("[Abstract Constructor] Initialized ID: " + transactionId);
    }

    // Abstract method: Child class MUST provide implementation
    public abstract void processPayment(double amount);

    // Concrete helper method shared by all payment processors
    public void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }
    }
}

// 2. INTERFACE #1: Defines a capability contract (CAN-DO)
interface Refundable {
    // Implicitly: public static final int MAX_RETRIES = 3;
    int MAX_RETRIES = 3;

    // Implicitly: public abstract void issueRefund(double amount);
    void issueRefund(double amount);

    // Java 8+ default method (has a body!)
    default void printReceipt() {
        System.out.println("[Refundable Default] Refund receipt generated.");
    }

    // Java 8+ static utility method
    static void printPolicy() {
        System.out.println("[Interface Static] Refunds process within 5 business days.");
    }
}

// 3. INTERFACE #2: Second interface with the SAME default method name (Diamond Problem demo!)
interface Auditable {
    default void printReceipt() {
        System.out.println("[Auditable Default] Audit log receipt generated.");
    }
}

// 4. CONCRETE CLASS: Extends 1 Abstract Class + Implements 2 Interfaces!
class StripePayment extends PaymentProcessor implements Refundable, Auditable {

    public StripePayment(String transactionId) {
        super(transactionId); // Calls PaymentProcessor's abstract class constructor!
    }

    @Override
    public void processPayment(double amount) {
        validateAmount(amount);
        System.out.println("Charged $" + amount + " via Stripe for " + transactionId);
    }

    // MUST be marked 'public' because Interface methods are public by default!
    @Override
    public void issueRefund(double amount) {
        System.out.println("Refunded $" + amount + " back to customer card.");
    }

    // Resolving the Java 8 Diamond Problem when both interfaces have default printReceipt()!
    @Override
    public void printReceipt() {
        Refundable.super.printReceipt(); // Explicitly calling Refundable's default method
        Auditable.super.printReceipt();  // Explicitly calling Auditable's default method
    }
}