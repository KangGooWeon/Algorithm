import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D, cnt;
	static int[][] startMap;
	static int[][][] endMap;
	static Pos taxi;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		startMap = new int[N+1][N+1];
		endMap = new int[N+1][N+1][M+1];
		taxi = new Pos();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if( a == 1) {
					startMap[i][j] = a;
					for(int k = 1; k<=M; k++) {
						endMap[i][j][k] = startMap[i][j];
					}
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi.x = Integer.parseInt(st.nextToken());
		taxi.y = Integer.parseInt(st.nextToken());
        taxi.pass =1;
		
		for(int i =1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x =Integer.parseInt(st.nextToken());
			int y =Integer.parseInt(st.nextToken());
			startMap[x][y] = i+1;
			x =Integer.parseInt(st.nextToken());
			y =Integer.parseInt(st.nextToken());
			endMap[x][y][i] = i+1;
		}
		
		boolean check = false;
		while(true) {
			// 승객 태우기
			if(BFS(0)) {
				check = true;
				break;
			}
			// 도착지 이동
			if(BFS(1)){
				check = true;
				break;
			}
			if(cnt == M) break;
		}
		if (check) System.out.println(-1);
		else System.out.println(D);
	}
	
	private static boolean BFS(int in) {
		boolean[][][] start_visited = new boolean[N+1][N+1][((N*N)+1)];
		boolean[][][] end_visited = new boolean[N+1][N+1][((N*N)+1)];
		Queue<Pos> qu = new LinkedList<>();
		PriorityQueue<Pos> pQueue = new PriorityQueue<>();
		qu.offer(taxi);
		int pass = taxi.pass-1;
		int move = 0;
		start_visited[taxi.x][taxi.y][pass] = true;
		end_visited[taxi.x][taxi.y][pass] = true;
		
		while (!qu.isEmpty()) {
			int size = qu.size();
			for (int i = 0; i < size; i++) {
				Pos pos = qu.poll();
				for (int k = 0; k < 4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					if( in == 0) {
						if(startMap[pos.x][pos.y] >=2) {    //제자리 도착했을때 손님 있을 경우
                            taxi.x = pos.x;
				            taxi.y = pos.y;
				            taxi.pass = startMap[pos.x][pos.y];
				            startMap[pos.x][pos.y] = 0;
							return false;
						}
						if (x <= 0 || x > N || y <= 0 || y > N || startMap[x][y] == 1 || start_visited[x][y][pass])
							continue;
						
						if(startMap[x][y] >=2) {
							pQueue.offer(new Pos(x, y, startMap[x][y]));
						}
						else {
							qu.add(new Pos(x, y, 0));
						}
						start_visited[x][y][pass] = true;
					}
					else {
						if (x <= 0 || x > N || y <= 0 || y > N || endMap[x][y][pass] == 1 || end_visited[x][y][pass])
							continue;
						if(endMap[x][y][pass] >=2) {
							if (endMap[x][y][pass] == taxi.pass) {
								move++;
								D--;
								taxi.x = x;
								taxi.y = y;
								D += (move * 2);
								cnt++;
								return false;
							}
						}
						else {
							qu.add(new Pos(x, y, pass));
						}
						end_visited[x][y][pass] = true;
					}
				}
			}
			//-----------------------------
			move++;
			D--;
			if(D == 0) return true;
			if (pQueue.size() > 0) {
				Pos pos = pQueue.poll();
				taxi.x = pos.x;
				taxi.y = pos.y;
				taxi.pass = pos.pass;
				startMap[taxi.x][taxi.y] =0;
				return false;
			}
		}
		return true;
	}

	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int pass;
		public Pos() {};
		public Pos(int x, int y, int pass) {
			super();
			this.x = x;
			this.y = y;
			this.pass = pass;
		}
		public int compareTo(Pos o) {
			if (x == o.x)
                return Integer.compare(y, o.y);
            return Integer.compare(x, o.x);
		}
	}
}
