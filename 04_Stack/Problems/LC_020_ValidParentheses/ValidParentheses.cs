using System;
using System.Collections.Generic;

/**
 * Valid Parentheses - LeetCode Problem #20
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParentheses
{
    public static void Main(string[] args)
    {
        // Test cases
        string[] testCases = {
            "()",       // Expected: true
            "()[]{}",   // Expected: true
            "(]",       // Expected: false
            "([)]",     // Expected: false
            "{[]}",     // Expected: true
            "]",        // Expected: false
            "["         // Expected: false
        };
        
        // Test all solutions
        Console.WriteLine("Solution1 (Stack) Test Results:");
        for (int i = 0; i < testCases.Length; i++)
        {
            Console.WriteLine($"Test {i+1}: {testCases[i]} -> {ValidParentheses1.IsValid(testCases[i])}");
        }
        
        Console.WriteLine("\nSolution2 (Stack with Dictionary) Test Results:");
        for (int i = 0; i < testCases.Length; i++)
        {
            Console.WriteLine($"Test {i+1}: {testCases[i]} -> {ValidParentheses2.IsValid(testCases[i])}");
        }
        
        Console.WriteLine("\nSolution3 (LINQ) Test Results:");
        for (int i = 0; i < testCases.Length; i++)
        {
            Console.WriteLine($"Test {i+1}: {testCases[i]} -> {ValidParentheses3.IsValid(testCases[i])}");
        }
    }
}

/**
 * Solution 1: Using Stack
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack in worst case
 */
public class ValidParentheses1
{
    public static bool IsValid(string s)
    {
        if (s.Length % 2 != 0)
        {
            return false; // If odd length, it can't be valid
        }
        
        Stack<char> stack = new Stack<char>();
        
        foreach (char c in s)
        {
            if (c == '(' || c == '{' || c == '[')
            {
                stack.Push(c);
            }
            else
            {
                if (stack.Count == 0)
                {
                    return false;
                }
                
                char top = stack.Pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '['))
                {
                    return false;
                }
            }
        }
        
        return stack.Count == 0; // Stack should be empty for valid string
    }
}

/**
 * Solution 2: Using Stack with Dictionary for mapping brackets
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack in worst case + O(1) for the dictionary
 */
public class ValidParentheses2
{
    public static bool IsValid(string s)
    {
        if (s.Length % 2 != 0)
        {
            return false; // If odd length, it can't be valid
        }
        
        Stack<char> stack = new Stack<char>();
        Dictionary<char, char> bracketMapping = new Dictionary<char, char>()
        {
            { ')', '(' },
            { '}', '{' },
            { ']', '[' }
        };
        
        foreach (char c in s)
        {
            // If it's an opening bracket, push to stack
            if (!bracketMapping.ContainsKey(c))
            {
                stack.Push(c);
            }
            else
            {
                // It's a closing bracket
                // If stack is empty or doesn't match the expected opening bracket
                if (stack.Count == 0 || stack.Pop() != bracketMapping[c])
                {
                    return false;
                }
            }
        }
        
        return stack.Count == 0; // Stack should be empty for valid string
    }
}

/**
 * Solution 3: Using LINQ and Stack
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack
 */
public class ValidParentheses3
{
    public static bool IsValid(string s)
    {
        if (string.IsNullOrEmpty(s) || s.Length % 2 != 0)
        {
            return s.Length == 0; // Only empty string is valid
        }
        
        Stack<char> stack = new Stack<char>();
        
        return s.All(c => {
            if (c == '(')
            {
                stack.Push(')');
                return true;
            }
            if (c == '{')
            {
                stack.Push('}');
                return true;
            }
            if (c == '[')
            {
                stack.Push(']');
                return true;
            }
            
            return stack.Count > 0 && stack.Pop() == c;
        }) && stack.Count == 0;
    }
}
