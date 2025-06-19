
/**
 * A simple key-value pair class for the HashMap implementation
 */
class Pair {
    String key;
    String val;

    public Pair(String key, String val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Custom HashMap implementation with open addressing for collision resolution
 */
class HashMap {
    int size;
    int capacity;  
    Pair[] map;

    /**
     * Constructor initializes the HashMap with a default capacity of 2
     */
    public HashMap() {
        this.size = 0;
        this.capacity = 2;
        this.map = new Pair[2];
    }

    /**
     * Hash function that converts a string key into an array index
     * Uses simple character code addition and modulo
     * @param key The key to hash
     * @return Array index for the key
     */
    public int hash(String key) {
        int index = 0;
        for (int i = 0; i < key.length(); i++) {
            index += (int) key.charAt(i);
        }
        return index % this.capacity;
    }

    /**
     * Retrieves the value for a given key
     * Uses linear probing for collision resolution
     * @param key The key to look up
     * @return The associated value, or null if key not found
     */
    public String get(String key) {
        int index = this.hash(key);
        while (this.map[index] != null) {
            if (this.map[index].key.equals(key)) {
                return this.map[index].val;
            }  
            index += 1;
            index = index % this.capacity;
        }    
        return null;
    }

    /**
     * Inserts or updates a key-value pair in the HashMap
     * Uses linear probing for collision resolution
     * Triggers rehash if load factor threshold is reached
     * @param key The key to insert
     * @param val The value to associate with the key
     */
    public void put(String key, String val) {
        int index = this.hash(key);

        while (true) {
            if (this.map[index] == null) {
                this.map[index] = new Pair(key, val);
                this.size += 1;
                if (this.size >= this.capacity / 2) {
                    this.rehash();
                }
                return;       
            } else if (this.map[index].key.equals(key)) {
                this.map[index].val = val;
                return;
            }
            index += 1;
            index = index % this.capacity;
        }    
    }

    /**
     * Removes a key-value pair from the HashMap
     * @param key The key to remove
     */
    public void remove(String key) {
        if (this.get(key) == null) {
            return;
        }
        
        int index = this.hash(key);
        while (true) {
            if (this.map[index].key.equals(key)) {
                // Removing an element using open-addressing actually causes a bug,
                // because we may create a hole in the list, and our get() may 
                // stop searching early when it reaches this hole.
                this.map[index] = null;
                this.size -= 1;
                return;
            }    
            index += 1;
            index = index % this.capacity;
        }
    }

    /**
     * Doubles the capacity of the HashMap and rehashes all existing elements
     * This is triggered automatically when load factor reaches 0.5
     */
    public void rehash() {
        this.capacity = 2 * this.capacity;
        Pair[] newMap = new Pair[this.capacity];

        Pair[] oldMap = this.map;
        this.map = newMap;
        this.size = 0;
        for (Pair p: oldMap) {
            if (p != null) {
                this.put(p.key, p.val);
            }
        }
    }

    /**
     * Prints all key-value pairs in the HashMap
     */
    public void print() {
        for (Pair p : this.map) {
            if (p != null) {
                System.out.println(p.key + " " + p.val);
            }
        }
    }
}

/**
 * Main class to test the HashMap implementation
 */
public class MainHashMap {
    public static void main(String[] args) {
        System.out.println("Custom HashMap Implementation in Java");
        
        HashMap map = new HashMap();
        
        // Test adding elements
        System.out.println("\nAdding elements:");
        map.put("apple", "red");
        map.put("banana", "yellow");
        map.put("grape", "purple");
        map.print();
        
        // Test retrieving elements
        System.out.println("\nRetrieving elements:");
        System.out.println("apple: " + map.get("apple"));
        System.out.println("banana: " + map.get("banana"));
        System.out.println("grape: " + map.get("grape"));
        System.out.println("cherry: " + map.get("cherry"));
        
        // Test updating elements
        System.out.println("\nUpdating elements:");
        map.put("apple", "green");
        map.print();
        
        // Test removing elements
        System.out.println("\nRemoving elements:");
        map.remove("banana");
        map.print();
        
        // Test rehashing
        System.out.println("\nAdding more elements (triggers rehash):");
        map.put("orange", "orange");
        map.put("blueberry", "blue");
        map.put("strawberry", "red");
        map.put("kiwi", "brown");
        map.print();
    }
}
