import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Stack implementation using an ArrayList as the underlying data structure.
 * This implementation includes both a type-specific (Integer) version and a generic version.
 * 
 * Time Complexity:
 * - push: O(1) amortized
 * - pop: O(1)
 * - peek: O(1)
 * - isEmpty: O(1)
 * - size: O(1)
 * 
 * Space Complexity:
 * - O(n) where n is the number of elements in the stack
 */
public class Stack {

    // Integer-specific implementation
    private ArrayList<Integer> stack = new ArrayList<Integer>();

    public Stack() {   
    }

    /**
     * Push a value onto the top of the stack
     * @param n The integer value to push
     */
    public void push(int n) {
        stack.add(n);
    }

    /**
     * Remove and return the top value from the stack
     * @return The integer at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }
    
    /**
     * Return the top value from the stack without removing it
     * @return The integer at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }
    
    /**
     * Check if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Get the number of elements in the stack
     * @return The size of the stack
     */
    public int size() {
        return stack.size();
    }
    
    /**
     * Clear all elements from the stack
     */
    public void clear() {
        stack.clear();
    }
    
    @Override
    public String toString() {
        return stack.toString();
    }
    
    /**
     * A generic implementation of Stack
     * @param <T> The type of elements stored in the stack
     */
    public static class GenericStack<T> {
        private ArrayList<T> stack = new ArrayList<>();
        
        public void push(T item) {
            stack.add(item);
        }
        
        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return stack.remove(stack.size() - 1);
        }
        
        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return stack.get(stack.size() - 1);
        }
        
        public boolean isEmpty() {
            return stack.isEmpty();
        }
        
        public int size() {
            return stack.size();
        }
        
        public void clear() {
            stack.clear();
        }
        
        @Override
        public String toString() {
            return stack.toString();
        }
    }
}
