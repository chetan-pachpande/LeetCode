/**
 * Test class for the PrefixSum implementation
 */
public class PrefixSumTest {
    public static void main(String[] args) {
        testHappyPath();
        testNegativeValues();
        testInvalidRanges();
        testNullInput();

        System.out.println("All PrefixSum tests passed.");
    }

    private static void testHappyPath() {
        PrefixSum prefixSum = new PrefixSum(new int[] {1, 2, 3, 4, 5});
        assertEquals(6, prefixSum.rangeSum(0, 2), "sum[0,2]");
        assertEquals(9, prefixSum.rangeSum(1, 3), "sum[1,3]");
        assertEquals(12, prefixSum.rangeSum(2, 4), "sum[2,4]");
        assertEquals(15, prefixSum.rangeSum(0, 4), "sum[0,4]");
        assertEquals(4, prefixSum.rangeSum(3, 3), "single element");
    }

    private static void testNegativeValues() {
        PrefixSum prefixSum = new PrefixSum(new int[] {-2, 5, -1, 3});
        assertEquals(7, prefixSum.rangeSum(1, 3), "sum with negatives");
        assertEquals(2, prefixSum.rangeSum(0, 2), "prefix with negatives");
    }

    private static void testInvalidRanges() {
        PrefixSum prefixSum = new PrefixSum(new int[] {10, 20, 30});
        expectIllegalArgument(() -> prefixSum.rangeSum(-1, 1), "negative left");
        expectIllegalArgument(() -> prefixSum.rangeSum(0, 3), "right out of bounds");
        expectIllegalArgument(() -> prefixSum.rangeSum(2, 1), "left > right");
    }

    private static void testNullInput() {
        expectIllegalArgument(() -> new PrefixSum(null), "null input");
    }

    private static void assertEquals(int expected, int actual, String label) {
        if (expected != actual) {
            throw new AssertionError(
                label + " expected " + expected + " but found " + actual
            );
        }
    }

    private static void expectIllegalArgument(Runnable action, String label) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException for " + label);
    }
}
