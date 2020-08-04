
import java.util.Scanner;

public class Solution {
	private static int[][] map;
	private static int N;
	private static int[] result = { 0, 0 };
	private static int[] last = { 1000, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					solution(i, j, 1);
					if (result[1] >= last[1]) {						
						if (result[1] == last[1]) {
							if(result[0] < last[0]) last[0] = result[0];
						}
						else 
							last[0] = result[0];
						
						last[1] = result[1];
					}
				}
			}

			System.out.println("#" + t + " " + last[0] + " " + last[1]);
			last[0] = 1000;
			last[1] = 0;
			result[0] = 0;
			result[1] = 0;
		}
	}

	private static void solution(int i, int j, int count) {
		if (count == 1) {
			result[0] = map[i][j];
		}
		result[1] = count;

		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			if (x < 0 || y < 0 || x >= N || y >= N)
				continue;
			if (map[i][j] + 1 == map[x][y])
				solution(x, y, count + 1);
		}
	}
}
