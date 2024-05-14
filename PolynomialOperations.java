// class Example {
//   public static void main(String args[]) {
//     System.out.println("This is a simple Java program.");
//   }
// }

// class Animal {
//   public void makeSound() {
//     // Leave the implementation empty as it will be overridden by subclasses
//   }
// }

// class Dog extends Animal {
//   public void makeSound() {
//     System.out.println("Woof! Woof!");
//   }
// }

// class Cat extends Animal {
//   public void makeSound() {
//     System.out.println("Meow! Meow!");
//   }
// }

// public class Main {
//   public static void main(String[] args) {
//     Dog myDog = new Dog();
//     myDog.makeSound(); // prints "Woof! Woof!"

//     Cat myCat = new Cat();
//     myCat.makeSound(); // prints "Meow! Meow!"
//   }
// }

// class Example2 {
//   public static void main(String args[]) {
//     int num; // this declares a variable called
//     num = 100; // this assigns num the value 100
//     System.out.println("This is num: " + num);
//     num = num * 2;
//     System.out.print("The value of num * 2 is ");
//     System.out.println(num);
//   }
// }

// class IfSamp1e {
//   public static void main(String args[]) {
//     int x, y;
//     x = 10;
//     y = 20;
//     if (x < y)
//       System.out.println("x is less than y");

//     if (x < y)
//       System.out.println("x is less than y");

//     if (x < y)
//       System.out.println("x is less than y");

//     if (x < y)
//       System.out.println("x is less than y");

//   }
// }

/*-----------------------------------------------------------------------------------------------------------*/

import java.util.Scanner;

// Node class represents a single term in the polynomial
class Node {
    int coefficient; // numerical coefficient of the term
    int exponent; // exponent of the term
    Node next; // reference to the next term in the polynomial

    // Constructor to create a new term
    public Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

// Polynomial class represents a polynomial as a linked list of Nodes
class Polynomial {
    Node head; // reference to the first term of the polynomial

    // Constructor to create an empty polynomial
    public Polynomial() {
        this.head = null;
    }

    // Method to add a new term to the polynomial
    public void addTerm(int coefficient, int exponent) {
        Node newNode = new Node(coefficient, exponent);

        // If the polynomial is empty or the new term has the highest exponent
        if (head == null || exponent > head.exponent) {
            newNode.next = head;
            head = newNode;
        } else {
            // Find the correct position for the new term
            Node current = head;
            while (current.next != null && exponent < current.next.exponent) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // Method to add two polynomials and return the result
    public Polynomial add(Polynomial poly) {
        Polynomial result = new Polynomial();
        Node p1 = this.head;
        Node p2 = poly.head;

        // Traverse both polynomials and add matching exponents
        while (p1 != null && p2 != null) {
            if (p1.exponent == p2.exponent) {
                result.addTerm(p1.coefficient + p2.coefficient, p1.exponent);
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.exponent > p2.exponent) {
                result.addTerm(p1.coefficient, p1.exponent);
                p1 = p1.next;
            } else {
                result.addTerm(p2.coefficient, p2.exponent);
                p2 = p2.next;
            }
        }

        // Add any remaining terms from the first polynomial
        while (p1 != null) {
            result.addTerm(p1.coefficient, p1.exponent);
            p1 = p1.next;
        }

        // Add any remaining terms from the second polynomial
        while (p2 != null) {
            result.addTerm(p2.coefficient, p2.exponent);
            p2 = p2.next;
        }

        return result;
    }

    // Method to subtract one polynomial from another and return the result
    public Polynomial subtract(Polynomial poly) {
        Polynomial result = new Polynomial();
        Node p1 = this.head;
        Node p2 = poly.head;

        // Traverse both polynomials and subtract matching exponents
        while (p1 != null && p2 != null) {
            if (p1.exponent == p2.exponent) {
                result.addTerm(p1.coefficient - p2.coefficient, p1.exponent);
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.exponent > p2.exponent) {
                result.addTerm(p1.coefficient, p1.exponent);
                p1 = p1.next;
            } else {
                result.addTerm(-p2.coefficient, p2.exponent);
                p2 = p2.next;
            }
        }

        // Add any remaining terms from the first polynomial
        while (p1 != null) {
            result.addTerm(p1.coefficient, p1.exponent);
            p1 = p1.next;
        }

        // Subtract any remaining terms from the second polynomial
        while (p2 != null) {
            result.addTerm(-p2.coefficient, p2.exponent);
            p2 = p2.next;
        }

        return result;
    }

    // Method to multiply two polynomials and return the result
    public Polynomial multiply(Polynomial poly) {
        Polynomial result = new Polynomial();
        Node p1 = this.head;

        // Traverse the first polynomial
        while (p1 != null) {
            Node p2 = poly.head;
            // Traverse the second polynomial
            while (p2 != null) {
                int coefficient = p1.coefficient * p2.coefficient;
                int exponent = p1.exponent + p2.exponent;
                result.addTerm(coefficient, exponent);
                p2 = p2.next;
            }
            p1 = p1.next;
        }

        return result;
    }

    // Method to display the polynomial in a readable format
    public void display() {
        Node current = head;
        if (current == null) {
            System.out.println("Polynomial is empty.");
            return;
        }

        StringBuilder polynomial = new StringBuilder();
        while (current != null) {
            if (current.coefficient > 0 && current != head) {
                polynomial.append(" + ");
            } else if (current.coefficient < 0) {
                polynomial.append(" - ");
            }
            polynomial.append(Math.abs(current.coefficient));
            polynomial.append("x^");
            polynomial.append(current.exponent);
            current = current.next;
        }

        // Remove leading " + " if it exists
        if (polynomial.indexOf(" + ") == 0) {
            polynomial.delete(0, 3);
        }

        System.out.println("Polynomial: " + polynomial.toString());
    }

    // Method to evaluate the polynomial at a given value of x
    public int evaluate(int x) {
        int result = 0;
        Node current = head;

        // Calculate the polynomial's value at x
        while (current != null) {
            result += current.coefficient * Math.pow(x, current.exponent);
            current = current.next;
        }

        return result;
    }
}

// Main class to perform polynomial operations
public class PolynomialOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();

        // Input for the first polynomial
        System.out.println("Enter the number of terms in polynomial 1:");
        int numTerms1 = scanner.nextInt();

        System.out.println("Enter the terms of polynomial 1 (coefficient exponent):");
        for (int i = 0; i < numTerms1; i++) {
            int coefficient = scanner.nextInt();
            int exponent = scanner.nextInt();
            poly1.addTerm(coefficient, exponent);
        }

        // Input for the second polynomial
        System.out.println("Enter the number of terms in polynomial 2:");
        int numTerms2 = scanner.nextInt();

        System.out.println("Enter the terms of polynomial 2 (coefficient exponent):");
        for (int i = 0; i < numTerms2; i++) {
            int coefficient = scanner.nextInt();
            int exponent = scanner.nextInt();
            poly2.addTerm(coefficient, exponent);
        }

        // Display the input polynomials
        System.out.println("Polynomial 1:");
        poly1.display();

        System.out.println("Polynomial 2:");
        poly2.display();

        // Perform addition and display the result
        Polynomial sum = poly1.add(poly2);
        System.out.println("Sum of the polynomials:");
        sum.display();

        // Perform subtraction and display the result
        Polynomial difference = poly1.subtract(poly2);
        System.out.println("Difference of the polynomials:");
        difference.display();

        // Perform multiplication and display the result
        Polynomial product = poly1.multiply(poly2);
        System.out.println("Product of the polynomials:");
        product.display();

        // Evaluate the first polynomial at a given value of x
        System.out.println("Enter the value of x for evaluation:");
        int x = scanner.nextInt();
        int result = poly1.evaluate(x);
        System.out.println("Polynomial 1 evaluated at x = " + x + ": " + result);

        scanner.close();
    }
}
