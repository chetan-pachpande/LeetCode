
class HashTable:
    """
    Custom HashTable implementation with open addressing for collision resolution
    """
    
    def __init__(self):
        """
        Constructor initializes the HashTable with a default capacity of 2
        """
        self.size = 0
        self.capacity = 2
        self.map = [None] * 2
        
    def hash(self, key):
        """
        Hash function that converts a string key into an array index
        Uses simple character code addition and modulo
        
        Args:
            key: The key to hash
        Returns:
            Array index for the key
        """
        index = 0
        for char in key:
            index += ord(char)
        return index % self.capacity
    
    def get(self, key):
        """
        Retrieves the value for a given key
        Uses linear probing for collision resolution
        
        Args:
            key: The key to look up
        Returns:
            The associated value, or None if key not found
        """
        index = self.hash(key)
        
        while self.map[index] is not None:
            if self.map[index][0] == key:
                return self.map[index][1]
            index += 1
            index = index % self.capacity
            
        return None
    
    def put(self, key, value):
        """
        Inserts or updates a key-value pair in the HashTable
        Uses linear probing for collision resolution
        Triggers rehash if load factor threshold is reached
        
        Args:
            key: The key to insert
            value: The value to associate with the key
        """
        index = self.hash(key)
        
        while True:
            if self.map[index] is None:
                self.map[index] = (key, value)
                self.size += 1
                if self.size >= self.capacity // 2:
                    self.rehash()
                return
            elif self.map[index][0] == key:
                self.map[index] = (key, value)  # Update value
                return
            index += 1
            index = index % self.capacity
    
    def remove(self, key):
        """
        Removes a key-value pair from the HashTable
        
        Args:
            key: The key to remove
        """
        if self.get(key) is None:
            return
        
        index = self.hash(key)
        
        while True:
            if self.map[index][0] == key:
                # Removing an element using open-addressing actually causes a bug,
                # because we may create a hole in the list, and our get() may 
                # stop searching early when it reaches this hole.
                self.map[index] = None
                self.size -= 1
                return
            index += 1
            index = index % self.capacity
    
    def rehash(self):
        """
        Doubles the capacity of the HashTable and rehashes all existing elements
        This is triggered automatically when load factor reaches 0.5
        """
        self.capacity = 2 * self.capacity
        old_map = self.map
        self.map = [None] * self.capacity
        self.size = 0
        
        for pair in old_map:
            if pair is not None:
                self.put(pair[0], pair[1])
    
    def print_table(self):
        """
        Prints all key-value pairs in the HashTable
        """
        for pair in self.map:
            if pair is not None:
                print(f"{pair[0]} {pair[1]}")


if __name__ == "__main__":
    print("Custom HashTable Implementation in Python")
    
    table = HashTable()
    
    # Test adding elements
    print("\nAdding elements:")
    table.put("apple", "red")
    table.put("banana", "yellow")
    table.put("grape", "purple")
    table.print_table()
    
    # Test retrieving elements
    print("\nRetrieving elements:")
    print(f"apple: {table.get('apple')}")
    print(f"banana: {table.get('banana')}")
    print(f"grape: {table.get('grape')}")
    print(f"cherry: {table.get('cherry')}")
    
    # Test updating elements
    print("\nUpdating elements:")
    table.put("apple", "green")
    table.print_table()
    
    # Test removing elements
    print("\nRemoving elements:")
    table.remove("banana")
    table.print_table()
    
    # Test rehashing
    print("\nAdding more elements (triggers rehash):")
    table.put("orange", "orange")
    table.put("blueberry", "blue")
    table.put("strawberry", "red")
    table.put("kiwi", "brown")
    table.print_table()
