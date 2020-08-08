import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int[][] map;
	private static int[] visited;
	private static int count;
	private static int vertex;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		vertex = sc.nextInt();
		map = new int[vertex+1][vertex+1];
		visited = new int[vertex+1];

		int start = sc.nextInt();
		int end = sc.nextInt();

		int N = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = 1;
			map[y][x] = 1;
		}

		count = -1;
		DFS(start, end, 0);
		System.out.println(count);
	}

	private static void DFS(int start, int end, int cnt) {
		if ( start == end ) {
			count = cnt;
			return;
		}
		
		for(int i =1; i <= vertex; i++) {
			if (map[start][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				DFS(i,end,cnt+1);
			}
		}
	}
}

