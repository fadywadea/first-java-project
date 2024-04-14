class Example {
  public static void main(String args[]) {
    System.out.println("This is a simple Java program.");
  }
}

class Animal {
  public void makeSound() {
    // Leave the implementation empty as it will be overridden by subclasses
  }
}

class Dog extends Animal {
  public void makeSound() {
    System.out.println("Woof! Woof!");
  }
}

class Cat extends Animal {
  public void makeSound() {
    System.out.println("Meow! Meow!");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog myDog = new Dog();
    myDog.makeSound(); // prints "Woof! Woof!"

    Cat myCat = new Cat();
    myCat.makeSound(); // prints "Meow! Meow!"
  }
}

class Example2 {
  public static void main(String args[]) {
    int num; // this declares a variable called
    num = 100; // this assigns num the value 100
    System.out.println("This is num: " + num);
    num = num * 2;
    System.out.print("The value of num * 2 is ");
    System.out.println(num);
  }
}

class IfSamp1e {
  public static void main(String args[]) {
    int x, y;
    x = 10;
    y = 20;
    if (x < y)
      System.out.println("x is less than y");

    if (x < y)
      System.out.println("x is less than y");

    if (x < y)
      System.out.println("x is less than y");

    if (x < y)
      System.out.println("x is less than y");

  }
}