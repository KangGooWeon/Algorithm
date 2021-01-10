import java.io.*;
import java.util.*;

public class Main {
	static int N, M, diceX, diceY, K, bottom;
	static int[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] dice = {{0,0,0},{0,0,0},{0,0,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		diceX = Integer.parseInt(st.nextToken());
		diceY = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int dis = Integer.parseInt(st.nextToken())-1;
			
			int x = diceX + dx[dis];
			int y = diceY + dy[dis];
			
			//범위 오버 무시
			if( x< 0 || x>=N || y<0 || y>=M) continue;
			
			diceX = x;
			diceY = y;
			
			changeDice(dis);
			checkBottom();
			
			System.out.println(dice[1][1]);
		}
	}

	private static void checkBottom() {
		if(map[diceX][diceY] == 0) map[diceX][diceY] = bottom;
		else {
			bottom = map[diceX][diceY];
			map[diceX][diceY] =0;
		}
	}

	private static void changeDice(int dis) {
		int temp =0;
		
		//방향에 따라 주사위 값 이동
		switch(dis) {
		case 0:
			temp = bottom;
			bottom = dice[1][2];
			for(int i=2; i>0; i--) {
				dice[1][i] = dice[1][i-1];
			}
			dice[1][0] = temp;
			break;
		case 1:
			temp = bottom;
			bottom = dice[1][0];
			for(int i=0; i<2; i++) {
				dice[1][i] = dice[1][i+1];
			}
			dice[1][2] = temp;
			break;
		case 2:
			temp = bottom;
			bottom = dice[0][1];
			for(int i=0; i<2; i++) {
				dice[i][1] = dice[i+1][1];
			}
			dice[2][1] = temp;
			break;
		case 3:
			temp = bottom;
			bottom = dice[2][1];
			for(int i=2; i>0; i--) {
				dice[i][1] = dice[i-1][1];
			}
			dice[0][1] = temp;
			break;
		}
		
	}
}
