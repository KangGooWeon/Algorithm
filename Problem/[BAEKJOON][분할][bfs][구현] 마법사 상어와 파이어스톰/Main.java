import java.io.*;
import java.util.*;

public class Main {
	static int[][] map, rotate;
	static int[] dy = { 1,-1,0,0 };
	static int[] dx = { 0, 0, 1, -1 };
	static boolean[][] visited;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);
		map = new int[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int k = 0; k < Q; k++) {
			int L = Integer.parseInt(st.nextToken());

			// 회전하기
			rotate = new int[size][size];
			divide(0, 0, N, size, L);

			// 복사
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					map[i][j] = rotate[i][j];
				}
			}
			// 얼음 줄이기
			decreaseIce(size);
		}
		
		//닶 구하기
		int sum =0;
		visited = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				sum += map[i][j];
				if(visited[i][j] || map[i][j] == 0) continue;
				bfs(i,j);
			}
		}
		
		System.out.println(sum);
		System.out.println(max);
	}

	private static void bfs(int i, int j) {
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(i,j));
		visited[i][j] = true;
		int iceCnt=1;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			
			for(int q=0; q<size; q++) {
				Pos pos = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					if( x < 0 || x>=map.length || y<0 || y>=map.length || visited[x][y]) continue;
					if(map[x][y] == 0) continue;
					
					visited[x][y] = true;
					qu.offer(new Pos(x,y));
					iceCnt++;
				}
			}
		}
		
		max = Math.max(max, iceCnt);
	}

	private static void decreaseIce(int size) {
		int[][] decMap = new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] == 0) continue;
				if(!checkIce(i,j, size)) decMap[i][j]--;
			}
		}
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[i][j] += decMap[i][j];
			}
		}
	}

	private static boolean checkIce(int i, int j, int size) {
		int count =0;
		for(int k=0; k<4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			
			if( x < 0 || x>=size || y<0 || y>=size) continue;
			if(map[x][y] == 0) continue;
			
			count++;
		}
		
		if(count >=3) return true;
		return false;
	}

	public static void divide(int x, int y, int cnt, int size, int limit) {
		if (cnt == limit) {
			int mapSize = (int) Math.pow(2, limit);
			// 회전
			for(int i=x; i<x+mapSize; i++) {
				for(int j=y; j<y+mapSize; j++) {
					rotate[j-y+x][(y+mapSize)-1-i+x] = map[i][j];
				}
			}

			return;
		}

		int newSize = size / 2;

		divide(x, y, cnt - 1, newSize, limit);

		divide(x, y + newSize, cnt - 1, newSize, limit);

		divide(x + newSize, y, cnt - 1, newSize, limit);

		divide(x + newSize, y + newSize, cnt - 1, newSize, limit);
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
