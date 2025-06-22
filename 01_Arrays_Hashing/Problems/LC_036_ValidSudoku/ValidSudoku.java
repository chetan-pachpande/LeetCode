import java.util.*;

/**
 * Valid Sudoku - LeetCode Problem #36
 * 
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated
 * according to the following rules:
 * 
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * 
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 */
public class ValidSudoku {

    public static void main(String[] args) {
        // Test Case 1: Valid Sudoku
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        // Test Case 2: Invalid Sudoku (duplicate in column)
        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        // Test all solutions
        System.out.println("Test Case 1 (Valid Sudoku): " + Solution.isValidSudoku(board1));
        System.out.println("Test Case 2 (Invalid Sudoku): " + Solution.isValidSudoku(board2));
    }
}

/**
 * Solution: Using HashMaps and HashSets to track row, column, and 3x3 box constraints
 * 
 * Time Complexity: O(1) - Since the board size is fixed at 9x9, we perform 81 operations
 * Space Complexity: O(1) - The space required is constant regardless of input
 * 
 * The solution uses three HashMaps to track elements in:
 * 1. Each row
 * 2. Each column
 * 3. Each 3x3 sub-box
 * 
 * For each cell, we check if the value already exists in its row, column, or sub-box.
 * If it does, the Sudoku is invalid.
 */
class Solution {
    public static boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<String, Set<Character>> squares = new HashMap<>();

        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                if(board[r][c] == '.') continue;

                String squareKey = (r/3) + "," + (c/3);

                if(rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) 
                || cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c])
                || squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c])) {
                    return false;
                }

                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }
        return true;
    }
}
