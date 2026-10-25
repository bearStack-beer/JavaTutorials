package com.beercoding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class ObjectCreation {

	public static void main(String[] args) throws CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, ClassNotFoundException {
		//1st
		Student student = new Student();
		
		System.out.println("First student is created through new keyword");
		
		//2nd
		Student student2 = (Student) student.clone();
		
		System.out.println("Second student is created through clone keyword");

		// 3rd 
		Student byReflection = Student.class.getDeclaredConstructor().newInstance();
		
		System.out.println("third student is created through reflection");
		
		// 4. Deserialization: rebuilds the object from bytes
		Teacher original = new Teacher("Asha");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(original);
        }

        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
        	Teacher byDeserialization = (Teacher) in.readObject();
            System.out.println(original == byDeserialization); // false: a new object
        }

	}

}

class Student implements Cloneable {
	
	String name = "Unknown";
	Student() {
		System.out.println("Student is created");
	}

	Student(String name) {
		this.name = name;
		System.out.println("Student is created: " + name);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}




class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    String name = "Unknown";

    Teacher() {
        System.out.println("Teacher is created (no-arg)");
    }

    Teacher(String name) {
        this.name = name;
        System.out.println("Teacher is created: " + name);
    }
}
