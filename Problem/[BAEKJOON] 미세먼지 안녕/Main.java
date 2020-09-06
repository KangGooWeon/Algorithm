import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int R, C, T;
	static int[][] map;
	static Pos[] airPos;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		airPos = new Pos[2];

		int cnt = 0;
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1)
					airPos[cnt++] = new Pos(i, j);
			}
		}

		for (int i = 0; i < T; i++) {
			diffuse();
			airClean();
		}
		
		int sum =0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void airClean() {
		// 위쪽
		int x = airPos[0].x;
		int y = airPos[0].y;
		int temp = 0;
		int count = 0;
		dx[1] = -1;
		dx[3] = 1;

		while (true) {
			x += dx[count];
			y += dy[count];

			if (x < 1 || y < 1 || x > R || y > C) {
				x -= dx[count];
				y -= dy[count];
				count++;
				continue;
			}
			if (map[x][y] == -1)
				break;

			int a = map[x][y];
			map[x][y] = temp;
			temp = a;

		}

		// 아래쪽
		x = airPos[1].x;
		y = airPos[1].y;
		temp = 0;
		count = 0;
		dx[1] = 1;
		dx[3] = -1;

		while (true) {
			x += dx[count];
			y += dy[count];
			
			if (x < 1 || y < 1 || x > R || y > C) {
				x -= dx[count];
				y -= dy[count];
				count++;
				continue;
			}
			
			if (map[x][y] == -1)
				break;

			int a = map[x][y];
			map[x][y] = temp;
			temp = a;

		}
	}

	private static void diffuse() {
		int[][] mapCopy = new int[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == 0)
					continue;

				int dif = map[i][j] / 5;
				int count = 0;

				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];

					if (x <= 0 || y <= 0 || x > R || y > C || map[x][y] == -1)
						continue;

					mapCopy[x][y] += dif;
					count++;
				}
				mapCopy[i][j] += (dif * count)* -1;
			}
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (airPos[0].x == i && airPos[0].y == j || airPos[1].x == i && airPos[0].y == j)
					continue;
				map[i][j] += mapCopy[i][j];
				if (map[i][j] < 0)
					map[i][j] = 0;
			}
		}
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
