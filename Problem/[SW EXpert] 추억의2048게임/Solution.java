
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, DIS;
	static int[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t =1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			map = new int[N][N];
			
			for(int i =0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(s.equals("right")) {
				for(int i = 0; i< N; i++) {
					boolean[] visited = new boolean[N];
					for(int j =N-2; j>=0; j--) {
						int x = j;
						while(true) {
							if (map[i][x] == 0) break;
							if (x >= N-1 || visited[x+1]) break;
							
							if (map[i][x+1] == 0) {
								map[i][x+1] = map[i][x];
								map[i][x] =0;
								x++;
								continue;
							}
							
							if (map[i][x+1] == map[i][x]) {
								map[i][x] = 0;
								map[i][x+1] *=2;
								visited[x+1] = true;
								break;
							}else {
								break;
							}
						}
					}
				}
			}
			else if(s.equals("left")) {
				for(int i = 0; i< N; i++) {
					boolean[] visited = new boolean[N];
					for(int j =1; j<N; j++) {
						int x = j;
						while(true) {
							if (map[i][x] == 0) break;
							if (x <= 0 || visited[x-1]) break;
							
							if (map[i][x-1] == 0) {
								map[i][x-1] = map[i][x];
								map[i][x] =0;
								x--;
								continue;
							}
							
							if (map[i][x-1] == map[i][x]) {
								map[i][x] = 0;
								map[i][x-1] *=2;
								visited[x-1] = true;
								break;
							}else {
								break;
							}
						}
					}
				}
			}
			else if(s.equals("up")) {
				for(int i = 0; i< N; i++) {
					boolean[] visited = new boolean[N];
					for(int j =1; j<N; j++) {
						int x = j;
						while(true) {
							if (map[x][i] == 0) break;
							if (x <= 0 || visited[x-1]) break;
							
							if (map[x-1][i] == 0) {
								map[x-1][i] = map[x][i];
								map[x][i] =0;
								x--;
								continue;
							}
							
							if (map[x-1][i] == map[x][i]) {
								map[x][i] = 0;
								map[x-1][i] *=2;
								visited[x-1] = true;
								break;
							}else {
								break;
							}
						}
					}
				}
			}
			else if(s.equals("down")) { 
				for(int i = 0; i< N; i++) {
					boolean[] visited = new boolean[N];
					for(int j =N-2; j>=0; j--) {
						int x = j;
						while(true) {
							if (map[x][i] == 0) break;
							if (x >= N-1 || visited[x+1]) break;
							
							if (map[x+1][i] == 0) {
								map[x+1][i] = map[x][i];
								map[x][i] =0;
								x++;
								continue;
							}
							
							if (map[x+1][i] == map[x][i]) {
								map[x][i] = 0;
								map[x+1][i] *=2;
								visited[x+1] = true;
								break;
							}else {
								break;
							}
						}
					}
				}
			}
			
			
			// 출력
			StringBuffer sb = new StringBuffer();
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j] + " ");
				}
				if(i!= N-1)sb.append("\n");
			}
			
			System.out.println("#"+t);
			System.out.println(sb.toString());
		}
	}
}
