import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int[][] map;
	private static int[] visited;
	private static int vertex, edge, count;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		vertex = sc.nextInt();
		edge = sc.nextInt();

		map = new int[vertex + 1][vertex + 1];
		visited = new int[vertex + 1];

		for (int i = 1; i <= edge; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = 1;
			map[y][x] = 1;
		}
		BFS(1);
		System.out.println(	count );
	}

	private static void BFS(int start) {
		Queue<Integer> qu = new LinkedList<Integer>();
		qu.offer(start);
		visited[start] = 1;
        
		while (!qu.isEmpty()) {
			int com = qu.poll();
			for (int i = 1; i <= vertex; i++) {
				if (map[com][i] == 1 && visited[i] == 0)
				{
					qu.offer(i);
					visited[i] = 1;		
					count++;
				}
			}
		}
	}
}
