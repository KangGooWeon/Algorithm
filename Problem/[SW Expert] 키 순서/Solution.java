
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, map[][], rmap[][], cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t =1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			rmap = new int[N+1][N+1];
			
			for(int i =0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				map[start][end] = 1;
				rmap[end][start] = 1;
			}
			
			int answer = 0;
			for(int i =1; i<=N; i++) {
				cnt=0;
				dfs(i,map, new boolean[N+1]);
				dfs(i,rmap, new boolean[N+1]);
				if(cnt == N-1) answer++;
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void dfs(int k,int[][] adj, boolean[] visited) { // 현재 기준이 되는 학생번호 
		visited[k] = true;
		for (int i = 1; i <= N; i++) {
			if(adj[k][i]==1 && !visited[i]) {
				cnt++;
				dfs(i,adj,visited);
			}
		}
	}
}
