import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates common applications of stack data structure:
 * 1. Checking for balanced parentheses
 * 2. Infix to Postfix conversion
 * 3. Postfix expression evaluation
 * 4. Implementing Undo functionality
 */
public class StackApplications {
    
    public static void main(String[] args) {
        // 1. Balanced Parentheses
        System.out.println("=== Balanced Parentheses ===");
        String[] parenthesesExpressions = {
            "{}",
            "({[]})",
            "({)}",
            "([](){([])})",
            "())({}}{()][]["
        };
        
        for (String expr : parenthesesExpressions) {
            System.out.println("\"" + expr + "\" is " + 
                (isBalancedParentheses(expr) ? "balanced" : "not balanced"));
        }
        
        // 2. Infix to Postfix
        System.out.println("\n=== Infix to Postfix ===");
        String[] infixExpressions = {
            "a+b",
            "a+b*c",
            "(a+b)*c",
            "a+b+c+d",
            "a*(b+c)/d"
        };
        
        for (String expr : infixExpressions) {
            System.out.println("Infix: " + expr + " -> Postfix: " + infixToPostfix(expr));
        }
        
        // 3. Evaluate Postfix
        System.out.println("\n=== Evaluate Postfix ===");
        String[] postfixExpressions = {
            "23+",
            "23*4+",
            "23+4*",
            "52*3+"
        };
        
        for (String expr : postfixExpressions) {
            System.out.println("Postfix: " + expr + " = " + evaluatePostfix(expr));
        }
        
        // 4. Simple Undo Functionality
        System.out.println("\n=== Text Editor with Undo ===");
        TextEditor editor = new TextEditor();
        editor.insertText("Hello");
        editor.insertText(" World");
        editor.deleteText(5);  // Delete " World"
        editor.insertText(" Java");
        System.out.println("Current text: " + editor.getText());
        
        editor.undo();
        System.out.println("After undo: " + editor.getText());
        
        editor.undo();
        System.out.println("After undo: " + editor.getText());
        
        editor.undo();
        System.out.println("After undo: " + editor.getText());
    }
    
    /**
     * Checks if a string has balanced parentheses
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean isBalancedParentheses(String s) {
        Stack.GenericStack<Character> stack = new Stack.GenericStack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
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
        
        return stack.isEmpty();
    }
    
    /**
     * Converts infix expression to postfix expression
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack.GenericStack<Character> stack = new Stack.GenericStack<>();
        
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);
        
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Remove '('
                }
            } else { // Operator
                while (!stack.isEmpty() && stack.peek() != '(' &&
                       precedence.getOrDefault(c, 0) <= precedence.getOrDefault(stack.peek(), 0)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        // Pop remaining operators
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            postfix.append(stack.pop());
        }
        
        return postfix.toString();
    }
    
    /**
     * Evaluates a postfix expression
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int evaluatePostfix(String postfix) {
        Stack stack = new Stack();
        
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convert char to int
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                
                switch (c) {
                    case '+': 
                        stack.push(val2 + val1);
                        break;
                    case '-': 
                        stack.push(val2 - val1);
                        break;
                    case '*': 
                        stack.push(val2 * val1);
                        break;
                    case '/': 
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        
        return stack.pop();
    }
    
    /**
     * Text editor with undo functionality using stack
     */
    static class TextEditor {
        private StringBuilder text = new StringBuilder();
        private Stack.GenericStack<Command> undoStack = new Stack.GenericStack<>();
        
        // Insert text at the end
        public void insertText(String str) {
            text.append(str);
            undoStack.push(new InsertCommand(str.length()));
        }
        
        // Delete text from the end
        public void deleteText(int count) {
            if (count > text.length()) {
                count = text.length();
            }
            
            String deleted = text.substring(text.length() - count);
            text.delete(text.length() - count, text.length());
            undoStack.push(new DeleteCommand(deleted));
        }
        
        // Undo the last operation
        public void undo() {
            if (undoStack.isEmpty()) {
                System.out.println("Nothing to undo");
                return;
            }
            
            Command command = undoStack.pop();
            command.undo(this);
        }
        
        public String getText() {
            return text.toString();
        }
        
        // Command interface for Command pattern
        interface Command {
            void undo(TextEditor editor);
        }
        
        // Insert command
        class InsertCommand implements Command {
            private int count;
            
            public InsertCommand(int count) {
                this.count = count;
            }
            
            @Override
            public void undo(TextEditor editor) {
                editor.text.delete(editor.text.length() - count, editor.text.length());
            }
        }
        
        // Delete command
        class DeleteCommand implements Command {
            private String deleted;
            
            public DeleteCommand(String deleted) {
                this.deleted = deleted;
            }
            
            @Override
            public void undo(TextEditor editor) {
                editor.text.append(deleted);
            }
        }
    }
}
