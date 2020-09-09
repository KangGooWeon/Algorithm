package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N, sumA, sumB, min;
	static int[][] map;
	static int[] weight;
	static boolean[] isSelected, visited;
	static int[] areaA, areaB;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		weight = new int[N + 1];
		isSelected = new boolean[N + 1];
		visited = new boolean[N + 1];
		areaA = new int[N + 1];
		areaB = new int[N + 1];
		min = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= num; j++) {
				int end = Integer.parseInt(st.nextToken());
				map[i][end] = 1;
			}
		}

		subset(1);
		if (min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	private static void subset(int cnt) {
		if (cnt == N + 1) {
			int indexA = 0;
			int indexB = 0;
			sumA = 0;
			sumB = 0;
			Arrays.fill(visited, false);

			for (int i = 1; i <= N; i++) {
				if (isSelected[i])
					areaA[(indexA++) +1] = i;
				else
					areaB[(indexB++) +1] = i;
			}
			if (indexA == 0 || indexB == 0)
				return; // 구역이 없는경우

			// A 체크
			sumA += weight[areaA[1]];
			visited[areaA[1]] = true;
			if (!check(areaA[1], indexA, 1))
				return;

			// B 체크
			sumB += weight[areaB[1]];
			visited[areaB[1]] = true;
			if (!check(areaB[1], indexB, 2))
				return;

			int result = Math.abs(sumA - sumB);

			System.out.println("여기" + result);
			if (result < min)
				min = result;
			return;
		}

		// 부분 집합 구성에 포함
		isSelected[cnt] = true;
		subset(cnt + 1);
		// 부분 집합 구성에 비포함
		isSelected[cnt] = false;
		subset(cnt + 1);
	}

	private static boolean check(int start, int index, int type) {
		int count = 1;
		for (int i = 1; i <= index; i++) {
			for (int j = 1; j <= N; j++) {
				if (type == 1) {
					if (map[areaA[i]][j] == 1 && !visited[j]) {
						if (isSelected[j]) {
							visited[j] = true;
							sumA += weight[j];
							count++;
						}
					}
				}
				if (type == 2) {
					if (map[areaB[i]][j] == 1 && !visited[j]) {
						if (!isSelected[j]) {
							visited[j] = true;
							sumB += weight[j];
							count++;
						}
					}
				}
			}
		}

		if (count == index)
			return true;
		else
			return false;
	}
}
