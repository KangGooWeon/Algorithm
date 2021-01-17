import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		//나라 정보 입력 받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//인구 이동 횟수 구하기
		int index = 0;
		while(true) {
			boolean flag = false; //
			visited = new boolean[N][N];
			
			//인구 이동 확인
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == true) continue;
					
					if(populationCheck(i,j)) {
						flag = true;
						//연합의 인구수와 개수 체크
						bfs(i,j);
					}
				}
			}
			
			//인구 이동이 발생 안했다면
			if(!flag) break;
			index++;
		}
		
		System.out.println(index);
	}

	private static void bfs(int i, int j) {
		Queue<Pos> qu = new LinkedList<>();
		List<Pos> list = new ArrayList<>();
		qu.offer(new Pos(i,j));
		visited[i][j] = true;
		int popul = map[i][j];
		int num =1;
		
		//국경 연결할 곳 찾기
		while(!qu.isEmpty()) {
			Pos pos = qu.poll();
			list.add(new Pos(pos.x , pos.y));
			
			for(int k=0; k<4; k++) {
				int x = pos.x+dx[k];
				int y = pos.y+dy[k];
				
				if(x<0 || x>=N || y<0 || y>=N || visited[x][y]) continue;
				
				int a = Math.abs(map[pos.x][pos.y] - map[x][y]);
				if(L<=a && a<=R) {
					visited[x][y] = true;
					qu.offer(new Pos(x,y));
					popul += map[x][y];
					num++;
				}
			}
		}
		
		//연합 인구수 분배
		int a = popul/num;
		for(int k=0; k<list.size(); k++) {
			Pos pos = list.get(k);
			map[pos.x][pos.y] = a;
		}
	}

	private static boolean populationCheck(int i, int j) {
		for(int k=0; k<4; k++) {
			int x = i+dx[k];
			int y = j+dy[k];
			
			if(x<0 || x>=N || y<0 || y>=N || visited[x][y]) continue;
			
			//인구수 차이 구하기
			int a = Math.abs(map[i][j] - map[x][y]);
			if(L<=a && a<=R) return true;
		}
		return false;
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