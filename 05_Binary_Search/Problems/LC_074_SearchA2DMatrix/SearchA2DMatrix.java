import java.util.*;

/**
 * Search a 2D Matrix - LeetCode Problem #74
 * 
 * You are given an m x n integer matrix matrix with the following two properties:
 * 
 * - Each row is sorted in non-decreasing order.
 * - The first element of each row is greater than the last element of the previous row.
 * 
 * Given an integer target, return true if target is in matrix or false otherwise.
 * 
 * You must write a solution in O(log(m * n)) time complexity.
 */
public class SearchA2DMatrix {

    public static void main(String[] args) {
        System.out.println("Running SearchA2DMatrix main method...");
        // Test cases
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target1 = 3;  // Expected: true
        
        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target2 = 13;  // Expected: false
        
        int[][] matrix3 = {{1}};
        int target3 = 1;  // Expected: true
        
        int[][] matrix4 = {
            {1, 3, 5},
            {7, 9, 11}
        };
        int target4 = 9;  // Expected: true
        
        // Test solution 1
        System.out.println("Solution1 (Two Binary Searches) Test Results:");
        System.out.println("Test 1: " + SearchA2DMatrix1.searchMatrix(matrix1, target1));  // true
        System.out.println("Test 2: " + SearchA2DMatrix1.searchMatrix(matrix2, target2));  // false
        System.out.println("Test 3: " + SearchA2DMatrix1.searchMatrix(matrix3, target3));  // true
        System.out.println("Test 4: " + SearchA2DMatrix1.searchMatrix(matrix4, target4));  // true
        
        // Test solution 2
        System.out.println("\nSolution2 (Single Binary Search) Test Results:");
        System.out.println("Test 1: " + SearchA2DMatrix2.searchMatrix(matrix1, target1));  // true
        System.out.println("Test 2: " + SearchA2DMatrix2.searchMatrix(matrix2, target2));  // false
        System.out.println("Test 3: " + SearchA2DMatrix2.searchMatrix(matrix3, target3));  // true
        System.out.println("Test 4: " + SearchA2DMatrix2.searchMatrix(matrix4, target4));  // true
    }
}

/**
 * Solution 1: Row Binary Search Followed by Column Binary Search
 * 
 * This approach uses two binary searches:
 * 1. First binary search to find the row which might contain the target
 * 2. Second binary search to find the target within that row
 * 
 * Time Complexity: O(log(m) + log(n)) = O(log(m*n)) - where m is number of rows and n is number of columns
 *   - O(log(m)) for the first binary search to find the row
 *   - O(log(n)) for the second binary search to find the target in the row
 * 
 * Space Complexity: O(1) - constant extra space
 */
class SearchA2DMatrix1 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int top = 0, bot = ROWS - 1;
        while(top <= bot){
            int row = (top + bot) / 2;
            if(target > matrix[row][COLS - 1]) {
                top = row + 1;
            } else if ( target < matrix[row][0]) {
                bot = row - 1;
            } else {
                break;
            }
        }
        
        if(!(top <= bot)) {
            return false;
        }

        int row = (top + bot) / 2;
        int l = 0, r = COLS - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(target > matrix[row][m]) {
                l = m + 1;
            } else if(target < matrix[row][m]) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

/**
 * Solution 2: Binary Search on Flattened Matrix
 * 
 * This approach treats the 2D matrix as a flattened 1D array.
 * Since the matrix is sorted row by row, we can map any position in the 
 * flattened array back to its 2D coordinates using division and modulo operations.
 * 
 * Time Complexity: O(log(m*n)) - where m is number of rows and n is number of columns
 *   - Performs a single binary search on the entire matrix treated as a 1D array
 * 
 * Space Complexity: O(1) - constant extra space
 * 
 * Advantages:
 * - Simpler implementation than the two binary searches approach
 * - More elegant solution that leverages the sorted property of the matrix
 */
class SearchA2DMatrix2 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length, COLS = matrix[0].length;

        int l = 0, r = ROWS * COLS - 1;
        while(l <= r) {
            int m = l + (r - l)/ 2;
            int row = m / COLS, col = m % COLS;
            if (target > matrix[row][col]) {
                l = m + 1;
            } else if (target < matrix[row][col]) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
