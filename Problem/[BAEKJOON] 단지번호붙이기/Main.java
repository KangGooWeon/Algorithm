import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int N, count;
	private static int[][] map;
	private static int[][] visited;
	private static Queue<Pos> q = new LinkedList<Pos>();
	private static int[] dx = { 0,0,-1,1 };
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dan = new int [1000];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new int[N][N];
		count = 0;

		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visited[i][j] == 0) {
					BFS(i, j);
					count++;
				}
			}
		}
		System.out.println(count);
		dan = Arrays.copyOf(dan, count);
		Arrays.sort(dan);
		for(int i = 0; i<count; i++) 
			System.out.println(dan[i]);
	}

	private static void BFS(int i, int j) {
		q.offer(new Pos(i, j));
		visited[i][j] = 1;
		dan[count]++;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int k = 0; k < 4; k++) {
				int x = p.x + dx[k];
				int y = p.y + dy[k];

				if (x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 0 || visited[x][y] > 0)
					continue;

				q.offer(new Pos(x, y));
				visited[x][y] = 1;
				dan[count]++;
			}
		}
	}
}

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

