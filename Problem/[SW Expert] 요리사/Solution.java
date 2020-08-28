import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N];
			min = 100000;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			combi(0, 0);
			System.out.println("#" + t + " " + min);
		}
	}

	private static void combi(int cnt, int start) {
		if (cnt == N/2) {
			int result = solution();
			if (result < min)
				min = result;
			return;
		}

		for (int i = start; i < N; i++) {
			visited[i] = true;
			combi(cnt + 1, i + 1);
			visited[i] = false;
		}
	}

	private static int solution() {
		List<Integer> aFood = new ArrayList<>();
		List<Integer> bFood = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				aFood.add(i);
			} else {
				bFood.add(i);
			}
		}

		int a = 0;
		int b = 0;

		for (int i = 0; i < aFood.size() - 1; i++) {
			for (int j = i; j < bFood.size(); j++) {
				a += map[aFood.get(i)][aFood.get(j)];
				a += map[aFood.get(j)][aFood.get(i)];
				b += map[bFood.get(i)][bFood.get(j)];
				b += map[bFood.get(j)][bFood.get(i)];
			}
		}

		return Math.abs(a - b);
	}
}
