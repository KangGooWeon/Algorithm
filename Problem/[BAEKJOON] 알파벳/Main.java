import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int R, C, max;
	static char[][] map;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int[][] visited;
	static int[] alpha = new int[26];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		max = 1;
		
		for(int i= 0; i<R; i++) {
			String s = br.readLine();
			for(int j =0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		alpha[map[0][0]-'A']++;
		visited[0][0] = 1;
		DFS(0,0,1);
		System.out.println(max);
	}
	private static void DFS(int i, int j, int cnt) {
		if (max < cnt) max =cnt;
		
		for(int k =0; k<4; k++ ) {
			int x = i +dx[k];
			int y = j +dy[k];
			
			if (x<0 || y<0 || x>=R || y >=C || visited[x][y] ==1 || alpha[map[x][y]-'A'] ==1) continue;
			
			alpha[map[x][y]-'A']++;
			visited[x][y] = 1;
			DFS(x,y,cnt+1);
			visited[x][y] = 0;
			alpha[map[x][y]-'A']--;
		}
	}
}
