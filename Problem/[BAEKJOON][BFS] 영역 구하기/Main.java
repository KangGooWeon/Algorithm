import java.io.*;
import java.util.*;

public class Main {
	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			for(int j=a; j<c; j++) {
				for(int k=b; k<d; k++) {
					map[j][k] = 1;
				}
			}
		}
		
		int result = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] ==0) {
					bfs(i,j);
					result++;
				}
			}
		}
		
		System.out.println(result);
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}
	private static void bfs(int i, int j) {
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(i,j));
		visited[i][j] = true;
		int index = 1;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int q=0; q<size; q++) {
				Pos pos = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					if(x<0 || x>=N || y<0 || y>=M || visited[x][y] || map[x][y] ==1) continue;
					
					qu.offer(new Pos(x,y));
					visited[x][y] = true;
					index++;
				}
			}
		}
		
		list.add(index);
	}
	
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
