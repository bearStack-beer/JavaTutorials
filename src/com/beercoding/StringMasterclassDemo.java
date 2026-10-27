package com.beercoding;

public class StringMasterclassDemo {

    public static void main(String[] args) {

        System.out.println("=== 1. STRING CONSTANT POOL (SCP) VS HEAP (& == vs .equals) ===");
        String s1 = "Java";              // Created in String Constant Pool (SCP)
        String s2 = "Java";              // Reuses existing "Java" reference from SCP!
        String s3 = new String("Java");  // Creates a brand-new object in regular Heap memory

        System.out.println("s1 == s2 (Both in SCP): " + (s1 == s2));         // true
        System.out.println("s1 == s3 (SCP vs Heap): " + (s1 == s3));         // false
        System.out.println("s1.equals(s3) (Value):  " + s1.equals(s3));      // true

        System.out.println("\n=== 2. STRING IMMUTABILITY TRAP ===");
        String brand = "Bear";
        brand.concat("Stack"); // Creates "BearStack" in memory, but brand is NOT modified!
        System.out.println("Without reassignment: " + brand);                // Bear

        brand = brand.concat("Stack"); // Now brand points to the new "BearStack" object
        System.out.println("With reassignment:    " + brand);                // BearStack

        System.out.println("\n=== 3. COMPILE-TIME VS RUNTIME CONCATENATION ===");
        String a = "BearStack";
        String b = "Bear" + "Stack"; // Resolved at COMPILE TIME -> points to SCP!
        String part = "Bear";
        String c = part + "Stack";   // Variable involved -> resolved at RUNTIME on Heap!

        System.out.println("a == b (Compile-time constants): " + (a == b));  // true
        System.out.println("a == c (Runtime variable):       " + (a == c));  // false

        System.out.println("\n=== 4. HOW String.intern() WORKS ===");
        String heapStr = new String("Java");
        String pooledStr = heapStr.intern(); // Fetches canonical reference from SCP!

        System.out.println("s1 == heapStr:   " + (s1 == heapStr));           // false
        System.out.println("s1 == pooledStr: " + (s1 == pooledStr));         // true

        System.out.println("\n=== 5. LEFT-TO-RIGHT '+' EVALUATION TRAP ===");
        System.out.println(10 + 20 + "Java" + 10 + 20);                      // 30Java1020
    }
}

