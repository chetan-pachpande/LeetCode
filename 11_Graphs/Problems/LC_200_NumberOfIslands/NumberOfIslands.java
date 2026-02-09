import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    floodFillIterative(grid, r, c);
                }
            }
        }

        return islands;
    }

    private void floodFillIterative(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{r, c});

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int row = cell[0];
            int col = cell[1];

            if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '1') {
                continue;
            }

            grid[row][col] = '0';
            stack.push(new int[]{row + 1, col});
            stack.push(new int[]{row - 1, col});
            stack.push(new int[]{row, col + 1});
            stack.push(new int[]{row, col - 1});
        }
    }

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        System.out.println(solver.numIslands(grid));
    }
}
