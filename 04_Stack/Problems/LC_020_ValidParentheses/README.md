# LeetCode Problem 20: Valid Parentheses

## Problem Description

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

### Examples

**Example 1:**
```
Input: s = "()"
Output: true
```

**Example 2:**
```
Input: s = "()[]{}"
Output: true
```

**Example 3:**
```
Input: s = "(]"
Output: false
```

**Example 4:**
```
Input: s = "([)]"
Output: false
```

**Example 5:**
```
Input: s = "{[]}"
Output: true
```

### Constraints
- 1 <= s.length <= 10^4
- s consists of parentheses only '()[]{}'

## Solutions

### Solution 1: Using Stack

This is the most intuitive and efficient solution using a stack:
1. Push open brackets onto the stack
2. When encountering a closing bracket, check if it matches the most recent open bracket
3. If the stack is empty at the end, the string is valid

**Time Complexity:** O(n) where n is the length of the string
**Space Complexity:** O(n) for the stack in the worst case

### Solution 2: Using Stack with Map/Dictionary

Similar to Solution 1 but uses a map/dictionary to store bracket mappings:
1. Create a map of closing brackets to their corresponding opening brackets
2. Push open brackets onto the stack
3. For closing brackets, check if the top of the stack matches the expected opening bracket

**Time Complexity:** O(n) where n is the length of the string
**Space Complexity:** O(n) for the stack in the worst case + O(1) for the map

### Solution 3: Alternative Approaches

Java: String replacement approach (less efficient)
- Repeatedly replace valid pairs of brackets until no more replacements can be made
- If the string is eventually empty, it's valid

C#: LINQ-based approach
- Push closing brackets onto the stack when encountering opening brackets
- For each closing bracket, check if it matches the expected closing bracket from the stack

**Time Complexity:** O(n²) for the string replacement approach, O(n) for the LINQ approach
**Space Complexity:** O(n) for both

## How to Run

### Java
```bash
cd /Users/chetanpachpande/LeetCode/04_Stack/Problems/LC_020_ValidParentheses
javac ValidParentheses.java
java ValidParentheses
```

### C#
```bash
cd /Users/chetanpachpande/LeetCode/04_Stack/Problems/LC_020_ValidParentheses
dotnet run --project ValidParentheses.csproj
```

### Python
```bash
cd /Users/chetanpachpande/LeetCode/04_Stack/Problems/LC_020_ValidParentheses
python3 valid_parentheses.py
```

## Related Problems

- LeetCode #22: Generate Parentheses
- LeetCode #32: Longest Valid Parentheses
- LeetCode #921: Minimum Add to Make Parentheses Valid
- LeetCode #1249: Minimum Remove to Make Valid Parentheses
