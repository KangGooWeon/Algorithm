import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static char[][] map;
	static int result;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i<N; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j =0; j<M; j++) {
				map[i][j] = c[j];
			}
		}
		
		for(int i = 0; i<N; i++) {
			for(int j =0; j<M; j++) {
				if(map[i][j] == 'L') BFS(i,j);
			}
		}
		
		int max = result;
		System.out.println(max); // 리턴값을 수정하세요
	} // end of execute
	
	private static void BFS(int i, int j) {
		boolean[][]visited = new boolean[N][M];
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(i,j,0));
		visited[i][j] = true;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int q =0; q<size; q++) {
				Pos pos = qu.poll();
				int posX = pos.x;
				int posY = pos.y;
				int count = pos.count;
				if(result < count) result = count;
				
				for(int k =0; k<4; k++) {
					int x = posX +dx[k];
					int y = posY +dy[k];
					
					if (x<0 || x>=N || y <0 || y>=M || map[x][y]=='W' || visited[x][y]) continue;
					
					qu.offer(new Pos(x,y,count+1));
					visited[x][y] = true;
				}
			}
		}
	}

	static class Pos{
		int x;
		int y;
		int count;
		public Pos(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
} // end of class
