package com.beercoding;

public class OopsPillarsDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. ENCAPSULATION ===");
        Wallet myWallet = new Wallet();
        myWallet.addMoney(500);
        System.out.println("Wallet Balance: Rs." + myWallet.getBalance());

        System.out.println("\n=== 2. INHERITANCE ===");
        Developer dev = new Developer();
        dev.CheckIn();     // Reused from parent Employee class
        dev.writeCode();   // Specific to Developer class

        System.out.println("\n=== 3. POLYMORPHISM ===");
        Notification alert = new EmailNotification(); // Upcasting
        alert.send("Welcome to BearStack!");          // Calls EmailNotification's version at runtime

        System.out.println("\n=== 4. ABSTRACTION ===");
        PaymentGateway payment = new UpiPayment();
        payment.processPayment(999); // We only call processPayment(), internal bank logic is hidden!
    }
}

// 1. ENCAPSULATION: Data hiding using private fields + controlled public methods
class Wallet {
    private double balance; // Locked! Outside classes cannot do myWallet.balance = -1000;

    public void addMoney(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }
}

// 2. INHERITANCE: Child class acquires properties and behaviors of Parent class
class Employee {
    void CheckIn() {
        System.out.println("Employee checked into the office");
    }
}

//INHERITANCE does not support Multiple Inheritance.
//privae members are not directly inherited, bust access them by creating public members.
//final keybord will stop the class inheritance and overriding methods

class Developer extends Employee {
    void writeCode() {
        System.out.println("Developer is building Java microservices");
    }
}

// 3. POLYMORPHISM: Same method name behaves differently based on the actual object
class Notification {
    void send(String msg) {
        System.out.println("Sending generic notification: " + msg);
    }
}

class EmailNotification extends Notification {
    @Override
    void send(String msg) {
        System.out.println("Sending EMAIL: " + msg);
    }
}

// 4. ABSTRACTION: Hiding complex implementation and exposing only essential actions
abstract class PaymentGateway {
    abstract void processPayment(double amount);
}

class UpiPayment extends PaymentGateway {
    @Override
    void processPayment(double amount) {
        System.out.println("Paid Rs." + amount + " via UPI (Encryption & Bank settlement hidden)");
    }
}