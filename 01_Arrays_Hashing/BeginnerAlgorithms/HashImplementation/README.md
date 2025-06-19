# Hash Table Implementation

This folder contains custom implementations of a hash table data structure in various programming languages. Hash tables are fundamental data structures that provide O(1) average time complexity for insertions, deletions, and lookups.

## Implementation Details

These implementations demonstrate the core concepts behind hash tables, including:

- Hash function design
- Collision resolution with open addressing (linear probing)
- Dynamic resizing when load factor reaches 0.5
- Common hash table operations (put, get, remove)

## Solutions

### C# Solution
- File: `HashTable.cs`
- Run using:
  - Code Runner: Right-click in editor and select "Run Code"
  - Terminal: `cd /path/to/HashImplementation && dotnet run`

### Java Solution
- File: `MainHashMap.java`
- Run using:
  - Code Runner: Right-click in editor and select "Run Code"
  - Terminal: `cd /path/to/HashImplementation && javac MainHashMap.java && java MainHashMap`

### Python Solution
- File: `hash_table.py`
- Run using:
  - Code Runner: Right-click in editor and select "Run Code"
  - Terminal: `cd /path/to/HashImplementation && python3 hash_table.py`

## Key Concepts

1. **Hash Function**: Converts keys into array indices
2. **Collision Handling**: Resolves conflicts when multiple keys hash to the same index
3. **Load Factor**: Ratio of filled slots to table size, triggers resizing
4. **Rehashing**: Process of rebuilding the hash table when it's resized
