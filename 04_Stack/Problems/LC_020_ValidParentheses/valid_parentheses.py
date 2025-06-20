
class Solution1:
    """
    Solution 1: Using Stack
    Time Complexity: O(n) where n is the length of the string
    Space Complexity: O(n) for the stack in worst case
    """
    @staticmethod
    def is_valid(s: str) -> bool:
        if len(s) % 2 != 0:
            return False  # If odd length, it can't be valid
        
        stack = []
        
        for c in s:
            if c in '({[':
                stack.append(c)
            else:
                if not stack:
                    return False
                
                top = stack.pop()
                if (c == ')' and top != '(') or \
                   (c == '}' and top != '{') or \
                   (c == ']' and top != '['):
                    return False
        
        return len(stack) == 0  # Stack should be empty for valid string


class Solution2:
    """
    Solution 2: Using Stack with Dictionary for mapping brackets
    Time Complexity: O(n) where n is the length of the string
    Space Complexity: O(n) for the stack in worst case + O(1) for the dictionary
    """
    @staticmethod
    def is_valid(s: str) -> bool:
        if len(s) % 2 != 0:
            return False  # If odd length, it can't be valid
        
        stack = []
        bracket_mapping = {')': '(', '}': '{', ']': '['}
        
        for c in s:
            # If it's an opening bracket, push to stack
            if c not in bracket_mapping:
                stack.append(c)
            else:
                # It's a closing bracket
                # If stack is empty or doesn't match the expected opening bracket
                if not stack or stack.pop() != bracket_mapping[c]:
                    return False
        
        return len(stack) == 0  # Stack should be empty for valid string


class Solution3:
    """
    Solution 3: Pythonic solution using replace operations
    This is not an efficient solution but demonstrates an alternative way to think about the problem.
    
    Time Complexity: O(n²) in the worst case due to repeated string replacements
    Space Complexity: O(n) for the string replacements
    
    Note: This is not an optimal solution due to the high time complexity
    """
    @staticmethod
    def is_valid(s: str) -> bool:
        if len(s) % 2 != 0:
            return False  # If odd length, it can't be valid
        
        while '()' in s or '{}' in s or '[]' in s:
            s = s.replace('()', '')
            s = s.replace('{}', '')
            s = s.replace('[]', '')
        
        return s == ''  # If all brackets were paired and removed, s should be empty


if __name__ == "__main__":
    # Test cases
    test_cases = [
        "()",       # Expected: true
        "()[]{}",   # Expected: true
        "(]",       # Expected: false
        "([)]",     # Expected: false
        "{[]}",     # Expected: true
        "]",        # Expected: false
        "["         # Expected: false
    ]
    
    # Test all solutions
    print("Solution1 (Stack) Test Results:")
    for i, test in enumerate(test_cases):
        print(f"Test {i+1}: {test} -> {Solution1.is_valid(test)}")
    
    print("\nSolution2 (Stack with Dictionary) Test Results:")
    for i, test in enumerate(test_cases):
        print(f"Test {i+1}: {test} -> {Solution2.is_valid(test)}")
    
    print("\nSolution3 (String Replacement) Test Results:")
    for i, test in enumerate(test_cases):
        print(f"Test {i+1}: {test} -> {Solution3.is_valid(test)}")
