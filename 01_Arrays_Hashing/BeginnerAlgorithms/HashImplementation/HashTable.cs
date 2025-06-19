using System;
using System.Collections.Generic;

/**
 * A simple key-value pair class for the HashTable implementation
 */
public class Pair
{
    public string Key { get; set; }
    public string Value { get; set; }

    public Pair(string key, string value)
    {
        Key = key;
        Value = value;
    }
}

/**
 * Custom HashTable implementation with open addressing for collision resolution
 */
public class HashTable
{
    private int size;
    private int capacity;
    private Pair[] map;

    /**
     * Constructor initializes the HashTable with a default capacity of 2
     */
    public HashTable()
    {
        size = 0;
        capacity = 2;
        map = new Pair[2];
    }

    /**
     * Hash function that converts a string key into an array index
     * Uses simple character code addition and modulo
     * @param key The key to hash
     * @return Array index for the key
     */
    public int Hash(string key)
    {
        int index = 0;
        foreach (char c in key)
        {
            index += (int)c;
        }
        return index % capacity;
    }

    /**
     * Retrieves the value for a given key
     * Uses linear probing for collision resolution
     * @param key The key to look up
     * @return The associated value, or null if key not found
     */
    public string Get(string key)
    {
        int index = Hash(key);
        while (map[index] != null)
        {
            if (map[index].Key == key)
            {
                return map[index].Value;
            }
            index += 1;
            index = index % capacity;
        }
        return null;
    }

    /**
     * Inserts or updates a key-value pair in the HashTable
     * Uses linear probing for collision resolution
     * Triggers rehash if load factor threshold is reached
     * @param key The key to insert
     * @param value The value to associate with the key
     */
    public void Put(string key, string value)
    {
        int index = Hash(key);

        while (true)
        {
            if (map[index] == null)
            {
                map[index] = new Pair(key, value);
                size += 1;
                if (size >= capacity / 2)
                {
                    Rehash();
                }
                return;
            }
            else if (map[index].Key == key)
            {
                map[index].Value = value;
                return;
            }
            index += 1;
            index = index % capacity;
        }
    }

    /**
     * Removes a key-value pair from the HashTable
     * @param key The key to remove
     */
    public void Remove(string key)
    {
        if (Get(key) == null)
        {
            return;
        }

        int index = Hash(key);
        while (true)
        {
            if (map[index].Key == key)
            {
                // Removing an element using open-addressing actually causes a bug,
                // because we may create a hole in the list, and our get() may 
                // stop searching early when it reaches this hole.
                map[index] = null;
                size -= 1;
                return;
            }
            index += 1;
            index = index % capacity;
        }
    }

    /**
     * Doubles the capacity of the HashTable and rehashes all existing elements
     * This is triggered automatically when load factor reaches 0.5
     */
    public void Rehash()
    {
        capacity = 2 * capacity;
        Pair[] newMap = new Pair[capacity];

        Pair[] oldMap = map;
        map = newMap;
        size = 0;
        foreach (Pair p in oldMap)
        {
            if (p != null)
            {
                Put(p.Key, p.Value);
            }
        }
    }

    /**
     * Prints all key-value pairs in the HashTable
     */
    public void Print()
    {
        foreach (Pair p in map)
        {
            if (p != null)
            {
                Console.WriteLine($"{p.Key} {p.Value}");
            }
        }
    }

    /**
     * Main method to test the HashTable implementation
     */
    public static void Main(string[] args)
    {
        Console.WriteLine("Custom HashTable Implementation in C#");

        HashTable table = new HashTable();

        // Test adding elements
        Console.WriteLine("\nAdding elements:");
        table.Put("apple", "red");
        table.Put("banana", "yellow");
        table.Put("grape", "purple");
        table.Print();

        // Test retrieving elements
        Console.WriteLine("\nRetrieving elements:");
        Console.WriteLine($"apple: {table.Get("apple")}");
        Console.WriteLine($"banana: {table.Get("banana")}");
        Console.WriteLine($"grape: {table.Get("grape")}");
        Console.WriteLine($"cherry: {table.Get("cherry")}");

        // Test updating elements
        Console.WriteLine("\nUpdating elements:");
        table.Put("apple", "green");
        table.Print();

        // Test removing elements
        Console.WriteLine("\nRemoving elements:");
        table.Remove("banana");
        table.Print();

        // Test rehashing
        Console.WriteLine("\nAdding more elements (triggers rehash):");
        table.Put("orange", "orange");
        table.Put("blueberry", "blue");
        table.Put("strawberry", "red");
        table.Put("kiwi", "brown");
        table.Print();
    }
}
