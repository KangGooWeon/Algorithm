package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution{
	
	static int N, M;
	static int [][]map;
	static int[] dx = {2,0};
	static int[] dy = {0,2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[M+1][N+1];
			
			int count =0;
			for(int i =1; i<=M; i++) {
				for(int j=1; j<=N; j++) {
					for(int k=0; k<2; k++) {
						int x = i+dx[k];
						int y = j+dy[k];
						if (x > M || y >N || map[i][j] == 1||map[x][y] == 1) continue;
						
						map[x][y] = 1;
						count++;	
					}
				}
			}
			
			int result = (N*M)-count;
			System.out.println("#"+test+" "+result);	
		}
	}
}

