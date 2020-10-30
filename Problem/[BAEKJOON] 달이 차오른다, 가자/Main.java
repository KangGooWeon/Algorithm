import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,min;
	static char[][] map;
	static boolean[][][] visited;
	static Pos start, end;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		min = Integer.MAX_VALUE;
		visited = new boolean[N][M][100];
		
		for(int i =0; i<N; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = c[j];
				if( map[i][j] == '0') start = new Pos(i,j, 0,0);
				else if( map[i][j] == '1') end = new Pos(i,j, 0,0);
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Pos> qu = new LinkedList<>();
		qu.add(new Pos(start.x, start.y, 0,0));
		visited[start.x][start.y][0] = true;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i =0; i<size; i++) {
				Pos pos = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					int index =  pos.index;
					
					if( x < 0 || x>=N || y<0 || y>=M || map[x][y] == '#' || visited[x][y][index])
						continue;
					
					if (map[x][y] == '1') return pos.dis +1;
					
					if( 97<= map[x][y] && map[x][y] <= 102) { // a~f 일때
						index = index | (1 << map[x][y] -'a');
					}
					
					if( 65<= map[x][y] && map[x][y] <= 70) {	// A~F 일때
						if ((index & (1 << map[x][y] -'A'))>0) {
						}else {
							continue;
						}
					}
					
					qu.offer(new Pos(x,y,index,pos.dis+1));
					visited[x][y][index] = true;
				}
			}
		}
		
		return -1;
	}

	static class Pos{
		int x;
		int y;
		int index;
		int dis;
		
		public Pos(int x, int y, int index, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.index = index;
			this.dis = dis;
		}
	}
}
