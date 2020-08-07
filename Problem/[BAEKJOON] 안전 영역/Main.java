package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	private static int N, max = 1;
	private static int[][] map;
	private static int[][] visited;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int height = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > height)
					height = map[i][j];
			}
		}

		for (int k = 1; k <= height; k++) {
			int count = 0;
			visited = new int[N][N];

			UnderWater(k);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 1 && visited[i][j] == 0) {
						visited[i][j] = 1;
						DFS(i, j);
						count++;
					}
				}
			}

			if (max < count)
				max = count;
		}

		System.out.println(max);
	}

	private static void UnderWater(int num) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == num)
					map[i][j] = 0;
			}
		}
	}

	private static void DFS(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];

			if (x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 0 || visited[x][y] > 0)
				continue;

			visited[x][y] = 1;
			DFS(x, y);
		}
	}
}
