
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static boolean[][] visited;
	static Pos manhol;
	static int N,M,R,C,time;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i =0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" + t + " " + bfs());
		}
	}
	
	static int bfs() {
		Queue<Pos> qu = new LinkedList<>();
		int cnt = 1;
		int timeChk = 1;
		qu.offer(new Pos(R,C));
		visited[R][C] = true;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
            if ( timeChk == time) break;
			for(int i =0; i<size; i++) {
				Pos pos = qu.poll();
				
				for(int k = 0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					int cur = map[pos.x][pos.y];
					
					if ( x < 0 || x >=N || y < 0 || y>=M || visited[x][y] || map[x][y] == 0) continue;
					
					if(!check(cur, map[x][y] ,k)) continue;
					
					qu.offer(new Pos(x,y));
					visited[x][y] = true;
					cnt++;
				}
			}
			timeChk++;
		}
		
		return cnt;
	}
	
	
	private static boolean check(int cur, int next, int dir) {
		switch(cur) {
		case 1:
			if (dir == 0 && (next == 2 || next == 4 || next == 5)) return false;
			if (dir == 1 && (next == 2 || next == 6 || next == 7)) return false;
			if (dir == 2 && (next == 3 || next == 4 || next == 7)) return false;
			if (dir == 3 && (next == 3 || next == 5 || next == 6)) return false;
			break;
		case 2:
			if (dir == 0 || dir == 1) return false;
			if (dir == 2 && (next == 3 || next == 4 || next == 7)) return false;
			if (dir == 3 && (next == 3 || next == 5 || next == 6)) return false;
			break;
		case 3:
			if (dir == 2 || dir == 3) return false;
			if (dir == 0 && (next == 2 || next == 4 || next == 5)) return false;
			if (dir == 1 && (next == 2 || next == 6 || next == 7)) return false;
			break;
		case 4:
			if (dir == 1 || dir == 3) return false;
			if (dir == 0 && (next == 2 || next == 4 || next == 5)) return false; // 오른쪽
			if (dir == 2 && (next == 3 || next == 4 || next == 7)) return false; // 위쪽
			break;
		case 5:
			if (dir == 1 || dir == 2) return false;
			if (dir == 0 && (next == 2 || next == 4 || next == 5)) return false; // 오른쪽
			if (dir == 3 && (next == 3 || next == 5 || next == 6)) return false; // 아래
			break;
		case 6:
			if (dir == 0 || dir == 2) return false;
			if (dir == 1 && (next == 2 || next == 6 || next == 7)) return false; // 왼쪽
			if (dir == 3 && (next == 3 || next == 5 || next == 6)) return false; // 아래
			break;
		case 7:
			if (dir == 0 || dir == 3) return false;
			if (dir == 1 && (next == 2 || next == 6 || next == 7)) return false; // 왼쪽
			if (dir == 2 && (next == 3 || next == 4 || next == 7)) return false; // 위쪽
			break;
		}
		return true;
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
