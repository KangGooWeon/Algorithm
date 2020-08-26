
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int K;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			map = new int[5][8];

			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int number = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				solution(number, dir);
			}

			int sum = 0;
			int[] s = { 0, 1, 2, 4, 8 };
			for (int i = 1; i < 5; i++) {
				if (map[i][0] == 1) {
					sum += s[i];
				}
			}
			System.out.println("#" + t + " " + sum);
		}
	}

	private static void solution(int number, int dir) {
		int[] check = new int[5];
		check[number] = dir;

		// 왼쪽 검사
		for (int i = number; i > 1; i--) {
			if (map[i][6] != map[i - 1][2]) {
				if (check[i] != 0) {
					check[i - 1] = check[i] * -1;
				}
			}
		}

		// 오른쪽 검사
		for (int i = number; i < 4; i++) {
			if (map[i][2] != map[i + 1][6]) {
				if (check[i] != 0) {
					check[i + 1] = check[i] * -1;
				}
			}
		}

		// 회전
		for (int i = 1; i < 5; i++) {
			// 반시계
			if (check[i] == -1) {
				int temp = map[i][0];
				for (int j = 7; j >= 0; j--) {
					int num = map[i][j];
					map[i][j] = temp;
					temp = num;
				}
			}

			// 시계
			if (check[i] == 1) {
				int temp = map[i][7];
				for (int j = 0; j < 8; j++) {
					int num = map[i][j];
					map[i][j] = temp;
					temp = num;
				}
			}
		}
	}
}
