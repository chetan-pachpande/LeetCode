import java.util.Arrays;

/**
 * LeetCode #853: Car Fleet
 *
 * Problem: There are n cars traveling to the same destination along a one-lane road.
 * Given a target and two arrays position and speed, count how many car fleets arrive.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public class CarFleet {
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
        double leadTime = (double)(target - cars[0][0]) / cars[0][1];
        for (int i = 1; i < n; i++) {
            double time = (double)(target - cars[i][0]) / cars[i][1];
            if (time > leadTime) {
                fleets++;
                leadTime = time;
            }
        }
        return fleets;
    }

    public static void main(String[] args) {
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};
        System.out.println("Car Fleet Test:");
        System.out.println("Target = " + target + ", positions = " + Arrays.toString(position) + ", speeds = " + Arrays.toString(speed));
        int fleets = new CarFleet().carFleet(target, position, speed);
        System.out.println("Number of Fleets = " + fleets + " (expected 3)");
    }
}
