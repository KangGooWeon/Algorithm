package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N,M;
	static int [][] map, input;
	static boolean [][] mapVisited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[] minEdge;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		mapVisited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 번호 붙이기
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] ==0 || mapVisited[i][j]) continue;
				map[i][j] = cnt+1;
				BFS(i,j,cnt+1);
				cnt++;
			}
		}
		
		minEdge = new int[cnt+1];
		visited = new boolean[cnt+1];
		input = new int[cnt+1][cnt+1];
		// input 초기화 처리
		for (int i = 1; i < cnt+1; i++) {
			Arrays.fill(input[i], Integer.MAX_VALUE);
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		// 다리 연결해서 배열 만들어 주기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] ==0) continue;
				int number = map[i][j];
				bridgeJoin(i,j,number);
			}
		}
		int result = MST(cnt);
		System.out.println(result);
	}

	private static int MST(int cnt) {
		int minVertex, min, result=0;
		// 0번 정점을 시작정점으로 한다고 가정
		minEdge[1] = 0;	// 시작점 최소간정비용은 0
		
		for(int c = 1; c< cnt+1; c++) {
			// 신장트리에 포함되지 않은 정점중 최소 간선비용의 정점 찾기
			min = Integer.MAX_VALUE;
			minVertex = 1;
			
			for (int i = 1; i < cnt+1; i++) {
				if(!visited[i] && min>minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			if (min == Integer.MAX_VALUE) {
				return -1;
			}
			result += min;
			visited[minVertex] = true;
			
			// 선택된 최소비용 정점 기준으로 신장트리에 포함되지 않은 다른 정점의로의 비용 계산하여 최소값 갱신
			for (int i = 1; i < cnt+1; i++) {
				if(!visited[i] && input[minVertex][i] != 10000 && minEdge[i] > input[minVertex][i]) {
					minEdge[i] = input[minVertex][i];
				}
			}
		}
		
		for (int i = 1; i < cnt+1; i++) {
			if(!visited[i]) return -1;
		}
		
		return result;
	}

	private static void bridgeJoin(int i, int j, int number) {
		for(int k =0; k<4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			int count =0;
			
			while(true) {
				if (x < 0 || x >= N || y < 0 || y >= M) break; // 범위 처리
				if(number == map[x][y]) break; // 같은 번호 처리
				
				if(map[x][y] !=0) {
					if(count <2) break;
					if(count < input[number][map[x][y]]) input[number][map[x][y]] = count;
					break;
				}
				count++;
				x += dx[k];
				y += dy[k];
			}
		}
	}

	private static void BFS(int i, int j, int number) {
		
		for(int k =0; k<4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			
			if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 0 || mapVisited[x][y]) continue;
			
			map[x][y] = number;
			mapVisited[x][y] = true;
			BFS(x,y, number);
		}
	}
}
