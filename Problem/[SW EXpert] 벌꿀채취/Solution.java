
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, C, empAMax, empBMax, toResult;
	private static int[][] map;
	private static int[] empA, empB;
	private static boolean[] isSelected;
	private static boolean moon;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			empA = new int[M];
			empB = new int[M];
			isSelected = new boolean[M];
			empAMax = 0;
			empBMax = 0;
			toResult = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					moon = true;
					for (int x = i; x < N; x++) {
						for (int y = 0; y <= N - M; y++) {
							if (x == i && y < j + M)
								continue;
							check(i, j, x, y);
							moon = false;
						}
					}
				}
			}

			System.out.println("#" + t + " " + (toResult));
		}
	}

	private static void check(int i, int j, int x, int y) {
		for (int a = 0; a < M; a++) {
			empA[a] = map[i][j + a];
		}
		generateSubset(0, empA, 0);

		for (int a = 0; a < M; a++) {
			empB[a] = map[x][y + a];
		}
		generateSubset(0, empB, 1);

		int result = empAMax + empBMax;
		if(toResult < result) toResult = result;
		empAMax = 0;
		empBMax = 0;
	}

	private static void generateSubset(int cnt, int[] emp, int check) {
		if (cnt == M) {
			int sum = 0;
			boolean ch = false;
			for (int i = 0; i < M; i++) {
				if (isSelected[i]) {
					sum += emp[i];
					ch = true;
					if (sum > C)
						return;
				}
			}
			if (!ch)
				return;

			sum = 0;
			for (int i = 0; i < M; i++) {
				if (isSelected[i]) {
					sum += emp[i] * emp[i];
				}
			}

			if (check == 0) {
				if (empAMax < sum)
					empAMax = sum;
			}

			if (check == 1) {
				if (empBMax < sum)
					empBMax = sum;
			}
			return;
		}

		// 부분 집합 구성에 포함
		isSelected[cnt] = true;
		generateSubset(cnt + 1, emp, check);
		// 부분 집합 구성에 비포함
		isSelected[cnt] = false;
		generateSubset(cnt + 1, emp, check);
	}
}
