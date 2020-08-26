package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] map, visited;
	static int[] dx = {-1,0,1,1};
	static int[] dy = {1,1,1,0};
	static int[] check = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 19;
		M = 19;
		map = new int[N+1][M+1];
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int curr = map[i][j];
				if (curr == 1 || curr == 2) {
					int result = BFS(i, j, curr);
					if (result == 5) {
						System.out.println(map[i][j]);
						System.out.println(i + " " + j);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

	private static int BFS(int i, int j, int curr) {
		Queue<Pos> qu = new LinkedList<>();
		Arrays.fill(check, 0);
		
		for (int k = 0; k < 4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];

			if (x <= 0 || y <= 0 || x > N || y > M || map[x][y] != curr)
				continue;
			
			check[k]++;
			qu.offer(new Pos(x, y, k));
		}

		while (!qu.isEmpty()) {
			Pos pos = qu.poll();
			int dir = pos.dir;
			int x = pos.x + dx[dir];
			int y = pos.y + dy[dir];

			if (x <= 0 || y <= 0 || x > N || y > M || map[x][y] != curr)
				continue;

			check[dir]++;
			qu.offer(new Pos(x, y, dir));
		}

		int max = 0;
		for (int k = 0; k < 4; k++) {
			if (check[k] == 4) {
				max = check[k];
				if(reversCheck(i,j,curr,k))return max+1;
			}
		}
		return 0;
	}

	private static boolean reversCheck(int i, int j, int curr, int k) {
		int[] nx = {1,0,-1,-1};
		int[] ny = {-1,-1,-1,0};
		
		int x = i + nx[k];
		int y = j + ny[k];

		if (x <= 0 || y <= 0 || x > N || y > M) return true;

		if (map[x][y] == curr) return false;
		
		return true;
	}

	static class Pos {
		int x;
		int y;
		int dir;

		public Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
