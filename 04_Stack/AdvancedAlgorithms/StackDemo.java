
/**
 * Demonstrates the usage of both the Integer Stack and Generic Stack implementations
 */
public class StackDemo {
    public static void main(String[] args) {
        // Demo of Integer Stack
        System.out.println("=== Integer Stack Demo ===");
        Stack intStack = new Stack();
        
        // Push elements
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        
        System.out.println("Stack after pushing elements: " + intStack);
        System.out.println("Stack size: " + intStack.size());
        
        // Peek at top element
        System.out.println("Top element (peek): " + intStack.peek());
        
        // Pop elements
        System.out.println("Popped element: " + intStack.pop());
        System.out.println("Stack after popping: " + intStack);
        
        // Check if empty
        System.out.println("Is stack empty? " + intStack.isEmpty());
        
        // Clear stack
        intStack.clear();
        System.out.println("Stack after clearing: " + intStack);
        System.out.println("Is stack empty now? " + intStack.isEmpty());
        
        // Demo of Generic Stack
        System.out.println("\n=== Generic Stack Demo ===");
        Stack.GenericStack<String> stringStack = new Stack.GenericStack<>();
        
        // Push elements
        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C#");
        
        System.out.println("Stack after pushing elements: " + stringStack);
        System.out.println("Stack size: " + stringStack.size());
        
        // Peek at top element
        System.out.println("Top element (peek): " + stringStack.peek());
        
        // Pop elements
        System.out.println("Popped element: " + stringStack.pop());
        System.out.println("Stack after popping: " + stringStack);
        
        // Check if empty
        System.out.println("Is stack empty? " + stringStack.isEmpty());
        
        // Example: Using Generic Stack with custom objects
        System.out.println("\n=== Custom Object Stack Demo ===");
        Stack.GenericStack<Person> personStack = new Stack.GenericStack<>();
        
        personStack.push(new Person("Alice", 30));
        personStack.push(new Person("Bob", 25));
        personStack.push(new Person("Charlie", 35));
        
        System.out.println("Stack after pushing people: " + personStack);
        System.out.println("Top person: " + personStack.peek());
        System.out.println("Popped person: " + personStack.pop());
        System.out.println("Stack after popping: " + personStack);
    }
    
    // Simple Person class for demonstration
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}
