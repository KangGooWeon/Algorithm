
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;
		
		for(int i =0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combi(0,0);
		System.out.println(min);
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
		List<Integer> start = new ArrayList<>();
		List<Integer> link = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				start.add(i);
			} else {
				link.add(i);
			}
		}

		int a = 0;
		int b = 0;

		for (int i = 0; i < start.size() - 1; i++) {
			for (int j = i; j < link.size(); j++) {
				a += map[start.get(i)][start.get(j)];
				a += map[start.get(j)][start.get(i)];
				b += map[link.get(i)][link.get(j)];
				b += map[link.get(j)][link.get(i)];
			}
		}

		return Math.abs(a - b);
	}
}
