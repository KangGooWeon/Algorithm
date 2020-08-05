import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int[][][] map;
	private static int[][][] visited;
	private static int M,N,H;
	private static Queue<Pos> qu = new LinkedList<Pos>();
	private static int[] dx = {0,0,1,-1,0,0};
	private static int[] dy = {1,-1,0,0,0,0};
	private static int[] dh = {0,0,0,0,1,-1};

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		map = new int[H][N][M];
		visited = new int[H][N][M];

		for(int i =0; i<H; i++) {					// 입력 받고 익은 토마토 Queue에 저장
			for(int j =0; j<N; j++) {
				for(int k =0; k<M; k++) {
					int a = sc.nextInt();
					map[i][j][k] = a;
					if ( a == 1) qu.offer(new Pos(i,j,k));
				}
			}
		}
		
		if (tomatoCheck()) {				// 검사 전 토마토가 다 익어 있다면 0
			System.out.println(0);
			return;
		}
		
		DFS();
		
		if (!tomatoCheck()) {				// 검사 후 안 익은 토마토가 있다면 -1
			System.out.println(-1);
			return;
		}
		
		int answer = 0;
		for(int i =0; i<H; i++) {					// visited에 제일 큰값이 마지막에 익은 토마토
			for(int j =0; j<N; j++) {
				for(int k =0; k<M; k++) {
					if(answer < visited[i][j][k] ) {
						answer = visited[i][j][k];
					}
				}
			}
		}
		System.out.println(answer);
	}
	
	private static boolean tomatoCheck() {
		for(int i =0; i<H; i++) {					
			for(int j =0; j<N; j++) {
				for(int k =0; k<M; k++) {
					if (map[i][j][k] == 0) return false;
				}
			}
		}
		return true;
	}

	private static void DFS() {
		while(!qu.isEmpty()) {
			Pos q = qu.poll();
			for(int i =0; i<6; i++){        // 6방향 체크
				int h = q.h +dh[i];
				int x = q.x +dx[i];
				int y = q.y +dy[i];
				
				if (x<0 || y<0 || h<0 || h>=H || x>=N || y>=M || map[h][x][y] != 0 || visited[h][x][y] > 0) continue;
				
				map[h][x][y] = 1;
				visited[h][x][y] = visited[q.h][q.x][q.y] +1;
				qu.offer(new Pos(h,x,y));
			}
		}
	}
}

class Pos{
	int h;
	int x;
	int y;
	
	Pos(int h, int x, int y){
		this.h = h;
		this.x = x;
		this.y = y;
	}
}
