import java.util.*;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{k, 0});

        boolean[] visited = new boolean[n + 1];
        int maxTime = 0;
        int seen = 0;

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[0];
            int time = current[1];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            seen++;
            maxTime = Math.max(maxTime, time);

            for (int[] next : graph.get(node)) {
                int nei = next[0];
                int weight = next[1];
                if (!visited[nei]) {
                    minHeap.offer(new int[]{nei, time + weight});
                }
            }
        }

        return seen == n ? maxTime : -1;
    }

    public static void main(String[] args) {
        NetworkDelayTime solver = new NetworkDelayTime();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        System.out.println(solver.networkDelayTime(times, n, k));
    }
}
