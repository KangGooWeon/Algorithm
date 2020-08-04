import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int[][] map;
	private static int[][] visited;
	private static int result, N, M;
	private static int[] dx = {0,1,-1,0};
	private static int[] dy = {1,0,0,-1};
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String[] a = sc.nextLine().split(" ");
		N = Integer.parseInt(a[0]);
		M = Integer.parseInt(a[1]);
		
		map = new int [N+1][M+1];
		visited = new int [N+1][M+1];
		result = 1000000;
		
		for(int i = 1; i<=N; i++) {
			String str = sc.next();
			for(int j = 1; j<=M; j++) {
				map[i][j] = str.charAt(j-1)-'0';
			}
		}
		BFS(new Pos(1,1));
		System.out.println(visited[N][M]);
	}

	private static void BFS(Pos pos) {
		Queue<Pos> qu = new LinkedList<Pos>();
		qu.offer(pos);
		visited[pos.x][pos.y] = 1;
		
		while(!qu.isEmpty()) {
			Pos p = qu.poll();
			for(int i =0; i<4; i++) {
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				
				if( x<1 || y <1 || x>N || y>M || map[x][y] == 0 || visited[x][y] > 0) continue;
				qu.offer(new Pos(x,y));
				visited[x][y] = visited[p.x][p.y] +1;
			}
		}
	}
}

class Pos{
	public int x;
	public int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
