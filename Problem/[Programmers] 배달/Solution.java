import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {
		int answer = 1;
		int start = 1;
		int[][] map = new int[N + 1][N + 1];

		for (int i = 0; i < road.length; i++) {
			if (map[road[i][0]][road[i][1]] > 0) {
				map[road[i][0]][road[i][1]] = Math.min(map[road[i][0]][road[i][1]], road[i][2]);
				map[road[i][1]][road[i][0]] = map[road[i][0]][road[i][1]];
			} else {
				map[road[i][0]][road[i][1]] = road[i][2];
				map[road[i][1]][road[i][0]] = road[i][2];
			}
		}

		for (int i = 2; i <= N; i++) {
			int[] distance = new int[N + 1];
			boolean[] visited = new boolean[N + 1];
			int end = i;
			PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();

			Arrays.fill(distance, Integer.MAX_VALUE);

			distance[start] = 0;
			pQueue.offer(new Vertex(start, distance[start]));

			Vertex current = null;

			while (!pQueue.isEmpty()) {

				// 1단계 : 방문하지 않은 정점들 중에 출발지에서 자신까지 오는 비용이 최단인 정점을 고려할 경유지로 선택
				current = pQueue.poll();
				if (visited[current.no])
					continue; // PQ에 남아있던 totalDistance의 최소비용보다 더 큰 상태

				visited[current.no] = true;
				if (current.no == end)
					break;

				// 2단계 : 선택된 current 정점을 경유지로 해서 아직 방문하지 않은 다른 정점으로의 최단거리 비용 계산하여 유리하면 update
				for (int j = 1; j <= N; j++) {
					// min ==> distance[current]
					if (!visited[j] && map[current.no][j] != 0
							&& distance[j] > current.totalDistance + map[current.no][j]) {
						distance[j] = current.totalDistance + map[current.no][j];
						pQueue.offer(new Vertex(j, distance[j]));
					}
				}
			}
			if(distance[end]<=K) answer++;
		}

		return answer;
	}

	static class Vertex implements Comparable<Vertex> {
		int no, totalDistance; // totalDistance : 출발지에서 자신까지 오는 최단거리

		public Vertex(int no, int totalDistance) {
			super();
			this.no = no;
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance; // totalDistance가 작은 비용이 우선적으로 처리
		}
	}
}