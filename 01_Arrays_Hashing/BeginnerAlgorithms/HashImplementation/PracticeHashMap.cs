using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Text;

// This is a practice file for implementing a HashMap in C# step by step
// We'll start with the basic structure and build it up gradually

// Using the existing Pair class from HashTable.cs

public class HashMap
{
    private int size;
    private int capacity;
    private Pair[] map;

    public HashMap()
    {
        size = 0;
        capacity = 2;
        map = new Pair[capacity];
        for (int i = 0; i < capacity; i++)
        {
            map[i] = new Pair("", "");
        }
    }

    public int Hash(string key)
    {
        int index = 0;
        foreach (char c in key)
        {
            index += (int)c;
        }
        return index % capacity;
    }

    public string Get(string key)
    {
        int index = Hash(key);

        while (map[index].Key != "")
        {
            if (map[index].Key == key)
            {
                return map[index].Value;
            }

            index = (index + 1) % capacity;
        }

        return "";
    }

    public void Put(string key, string value)
    {
        int index = Hash(key);

        // Find spot to insert or update
        while (true)
        {
            // Found empty spot
            if (map[index].Key == "")
            {
                map[index].Key = key;
                map[index].Value = value;
                size++;
                
                // Check if we need to resize
                if (size >= capacity / 2)
                {
                    Rehash();
                }
                return;
            }
            // Found same key - update value
            else if (map[index].Key == key)
            {
                map[index].Value = value;
                return;
            }
            
            // Linear probing - move to next spot
            index = (index + 1) % capacity;
        }
    }
    
    public void Remove(string key)
    {
        // If key doesn't exist, return
        if (Get(key) == "")
        {
            return;
        }
        
        int index = Hash(key);
        
        // Find the key
        while (true)
        {
            if (map[index].Key == key)
            {
                // Set key and value to empty string to mark as deleted
                map[index].Key = "";
                map[index].Value = "";
                size--;
                return;
            }
            
            // Continue linear probing
            index = (index + 1) % capacity;
        }
    }
    
    public void Rehash()
    {
        // Double capacity
        capacity *= 2;
        
        // Save old map
        Pair[] oldMap = map;
        
        // Create new map with larger capacity
        map = new Pair[capacity];
        for (int i = 0; i < capacity; i++)
        {
            map[i] = new Pair("", "");
        }
        
        // Reset size since we'll reinsert everything
        size = 0;
        
        // Reinsert all non-empty entries
        foreach (Pair pair in oldMap)
        {
            if (pair.Key != "")
            {
                Put(pair.Key, pair.Value);
            }
        }
    }
    
    public void Print()
    {
        Console.WriteLine("HashMap contents:");
        foreach (Pair pair in map)
        {
            if (pair.Key != "")
            {
                Console.WriteLine($"Key: {pair.Key}, Value: {pair.Value}");
            }
        }
    }
    
    public static void Main(string[] args)
    {
        Console.WriteLine("HashMap Practice Implementation");
        
        HashMap map = new HashMap();
        
        // Test adding elements
        Console.WriteLine("\nAdding elements:");
        map.Put("apple", "red");
        map.Put("banana", "yellow");
        map.Put("grape", "purple");
        map.Print();
        
        // Test retrieving elements
        Console.WriteLine("\nRetrieving elements:");
        Console.WriteLine($"apple: {map.Get("apple")}");
        Console.WriteLine($"banana: {map.Get("banana")}");
        Console.WriteLine($"grape: {map.Get("grape")}");
        Console.WriteLine($"cherry: {map.Get("cherry")}"); // Should return null
        
        // Test updating elements
        Console.WriteLine("\nUpdating elements:");
        map.Put("apple", "green");
        map.Print();
        
        // Test removing elements
        Console.WriteLine("\nRemoving elements:");
        map.Remove("banana");
        map.Print();
        
        // Test rehashing
        Console.WriteLine("\nAdding more elements (triggers rehash):");
        map.Put("orange", "orange");
        map.Put("blueberry", "blue");
        map.Put("strawberry", "red");
        map.Put("kiwi", "brown");
        map.Print();
    }
}