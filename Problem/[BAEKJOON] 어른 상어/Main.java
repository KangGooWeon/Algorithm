
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N, M, K, cnt, result;
	static int[][] small, position, curr; 
	static int[][][] priorShake;
	static List<Pos> shake;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) +1;
		curr = new int[N][N];
		small = new int[N][N];
		position = new int[N][N];
		priorShake = new int[5][5][M+1];
		cnt = M;
		shake = new ArrayList<>();
		
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				curr[i][j] = n;
				if( n > 0) {
					small[i][j] = K-1;
					position[i][j] = n;
					shake.add(new Pos(i,j,n,0));
				}
			}
		}
		
		Collections.sort(shake);
		
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<M; i++) {
			shake.get(i).dis = Integer.parseInt(st.nextToken());
		}
		
		for(int k=1; k<=M; k++) {
			for(int i =1; i<=4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =1; j<=4; j++) {
					int n = Integer.parseInt(st.nextToken());
					priorShake[i][j][k] = n;
				}
			}
		}
		
		int check;
		for(check=1; check<=1000; check++) {
			
			// 상어 방향 결정
			shakeDis();
			if(cnt == 1) break;
			
			// 냄새 1칸 낮추고 0이면 자리도 삭
			smallDown();
		}
		
		if (check == 1001) System.out.println(-1);
		else System.out.println(check);
	}
	private static void shakeDis() {
		for(int i =0; i<M; i++) {
			Pos pos = shake.get(i);
			int num = pos.num;
			
			if (num == -1) continue;
			
			int dis = pos.dis;
			
			boolean check = false;
			for (int k = 1; k <= 4; k++) { // 주변에 빈칸이 있는지 체크
				int a = pos.x + dx[priorShake[dis][k][num]];
				int b = pos.y + dy[priorShake[dis][k][num]];
				if (a < 0 || a >= N || b < 0 || b >= N) continue;
				if (small[a][b] == 0) {
					dis = priorShake[dis][k][num];
					check = true;
					break;
				}
			}

			if (!check) { // 주변에 빈칸이 없기때문에 자신의 냄새로 간다. 우선순위에 따라
				for (int k = 1; k <= 4; k++) { // 주변에 빈칸이 있는지 체크
					int a = pos.x + dx[priorShake[dis][k][num]];
					int b = pos.y + dy[priorShake[dis][k][num]];
					if (a < 0 || a >= N || b < 0 || b >= N)
						continue;
					if (position[a][b] == num) {
						dis = priorShake[dis][k][num];
						break;
					}
				}
			}
			
			// 상어 이동
			int x = pos.x + dx[dis];
			int y = pos.y + dy[dis];
			
			if(curr[x][y] !=0 && curr[x][y] < num) {
				shake.get(i).num = -1;
				curr[pos.x][pos.y] = 0;
				cnt--;
			}
			else {
				curr[x][y] = num;
				curr[pos.x][pos.y] = 0;
				position[x][y] = num;
				shake.get(i).x = x;
				shake.get(i).y = y;
				shake.get(i).dis = dis;
			}
		}
		
		for(int i =0; i<M; i++) {
			Pos pos = shake.get(i);
			if (pos.num == -1) continue;
			small[pos.x][pos.y] = K;
		}
	}

	private static void smallDown() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if( small[i][j] > 0) {
					small[i][j]--;
					if (small[i][j] ==0) {
						position[i][j] =0;
					}
				}
			}
		}
	}
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int num;
		int dis;
		public Pos(int x, int y, int num, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dis = dis;
		}
		@Override
		public int compareTo(Pos o) {
			return this.num - o.num;
		}
	}
}
