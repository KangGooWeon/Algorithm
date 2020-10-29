import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, cusDis, bamleng;
	static int[][] map;
	static int[][] bam;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] data;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		bam = new int[N][N];
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i =0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 1;
		}
		
		int K = Integer.parseInt(br.readLine());
		data = new int[K][2];
		
		for(int i =0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String b = st.nextToken();
			data[i][0] = t;
			if(b.equals("D")) data[i][1] = 1;
			else data[i][1] = -1;
		}
		
		int time = 0;
		int index = 0;
		
		Deque<Pos> dq = new ArrayDeque<Pos>();
		dq.addLast(new Pos(0,0));
		bam[0][0] = 1;
		while(true) {
			time++;
			Pos pos = dq.peekLast();
			int x = pos.x +dx[cusDis];
			int y = pos.y +dy[cusDis];
			
			if (x < 0 || x>=N || y<0 || y>=N || bam[x][y]==1) break;
			
			bam[x][y] = 1;
			dq.addLast(new Pos(x,y));
			
			if(map[x][y] == 1) {
				map[x][y] = 0;
			}
			else {
				Pos p = dq.pollFirst();
				bam[p.x][p.y] = 0;
			}
			
			if(index < K && data[index][0] == time) {
				cusDis += data[index][1];
				if(cusDis == -1) cusDis = 3;
				if(cusDis == 4) cusDis = 0;
				index++;
			}
		}
		
		System.out.println(time);
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
