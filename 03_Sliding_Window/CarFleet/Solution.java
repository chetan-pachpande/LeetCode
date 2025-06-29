import java.util.Arrays;

/**
 * LeetCode #853: Car Fleet
 *
 * Problem: There are n cars traveling to the same destination along a one-lane road. 
 * You are given an integer target and two integer arrays position and speed, both of length n.
 * position[i] is the position of the i-th car and speed[i] is the speed of the i-th car (in the same unit per hour).
 * A car can never pass another car ahead of it, but it can catch up and join the fleet.
 * A car fleet is some non-empty set of cars driving at the same position and same speed.
 * The leading car determines the position and speed of the fleet.
 * The fleet is considered to have arrived when the leading car reaches the destination.
 * Return the number of car fleets that will arrive at the destination.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;

        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleets = 1;
        double leadingTime = (double)(target - cars[0][0]) / cars[0][1];

        for (int i = 1; i < n; i++) {
            double time = (double)(target - cars[i][0]) / cars[i][1];
            if (time > leadingTime) {
                fleets++;
                leadingTime = time;
            }
        }
        return fleets;
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};
        System.out.println("Car Fleet Test:");
        System.out.println("Target = " + target + ", position = " + Arrays.toString(position) + ", speed = " + Arrays.toString(speed));
        int fleets = sol.carFleet(target, position, speed);
        System.out.println("Number of Fleets = " + fleets + " (expected 3)");
    }
}
