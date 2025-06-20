import java.util.HashMap;
import java.util.Map;
// Note: We use java.util.Stack with fully qualified name to avoid conflicts with our own Stack implementation

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
 * 
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of parentheses only '()[]{}'
 */
public class ValidParentheses {

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "()",       // Expected: true
            "()[]{}",   // Expected: true
            "(]",       // Expected: false
            "([)]",     // Expected: false
            "{[]}",     // Expected: true
            "]",        // Expected: false
            "["         // Expected: false
        };
        
        // Test all solutions
        System.out.println("Solution1 (Stack) Test Results:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test " + (i+1) + ": " + testCases[i] + " -> " + 
                ValidParentheses1.isValid(testCases[i]));
        }
        
        System.out.println("\nSolution2 (Stack with Map) Test Results:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test " + (i+1) + ": " + testCases[i] + " -> " + 
                ValidParentheses2.isValid(testCases[i]));
        }
        
        System.out.println("\nSolution3 (Character Replacement) Test Results:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test " + (i+1) + ": " + testCases[i] + " -> " + 
                ValidParentheses3.isValid(testCases[i]));
        }
    }
}

/**
 * Solution 1: Using Stack
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack in worst case
 */
class ValidParentheses1 {
    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false; // If odd length, it can't be valid
        }
        
        java.util.Stack<Character> stack = new java.util.Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty(); // Stack should be empty for valid string
    }
}

/**
 * Solution 2: Using Stack with HashMap for mapping brackets
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack in worst case + O(1) for the map
 */
class ValidParentheses2 {
    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false; // If odd length, it can't be valid
        }
        
        java.util.Stack<Character> stack = new java.util.Stack<>();
        Map<Character, Character> bracketMapping = new HashMap<>();
        bracketMapping.put(')', '(');
        bracketMapping.put('}', '{');
        bracketMapping.put(']', '[');
        
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (!bracketMapping.containsKey(c)) {
                stack.push(c);
            } else {
                // It's a closing bracket
                // If stack is empty or doesn't match the expected opening bracket
                if (stack.isEmpty() || stack.pop() != bracketMapping.get(c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty(); // Stack should be empty for valid string
    }
}

/**
 * Solution 3: String replacement approach
 * This is not an efficient solution but demonstrates an alternative way to think about the problem.
 * 
 * Time Complexity: O(n²) in the worst case due to repeated string replacements
 * Space Complexity: O(n) for the string replacements
 * 
 * Note: This is not an optimal solution due to the high time complexity
 */
class ValidParentheses3 {
    public static boolean isValid(String s) {
        int originalLength = s.length();
        if (originalLength % 2 != 0) {
            return false; // If odd length, it can't be valid
        }
        
        // Keep replacing valid pairs until no more changes or string is empty
        String previousStr;
        do {
            previousStr = s;
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        } while (s.length() != previousStr.length());
        
        return s.isEmpty(); // If all brackets were paired and removed, s should be empty
    }
}
