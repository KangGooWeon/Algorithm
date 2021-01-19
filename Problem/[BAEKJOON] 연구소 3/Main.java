import java.io.*;
import java.util.*;

public class Main {
	static int N, M, min, zeroCnt;
	static int[][] map;
	static boolean[][] visited;
	static List<Pos> list;
	static int[] isSelected;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new ArrayList<>();
		min = Integer.MAX_VALUE;
		isSelected = new int[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) list.add(new Pos(i,j));
				
				//모든 빈칸에 바이러스 퍼뜨릴 수 있는지 검사하기위해
				if(map[i][j] == 0) zeroCnt++;
			}
		}
		
		//빈칸이 없는 경우
		if(zeroCnt ==0) {
			System.out.println(0);
			return;
		}
		
		combi(0,0);
		
		//모든 빈칸에 바이러스 퍼뜨릴 수 없을때
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			Queue<Pos> qu = new LinkedList<>();
			visited = new boolean[N][N];
			
			//활성화 비활성화 선택
			int index =0;
			for(int i=0; i<list.size(); i++) {
				Pos pos = list.get(i);
				if(isSelected[index] == i) {
					//활성화
					qu.offer(pos);
					visited[pos.x][pos.y] = true;
					index++;
					if(index == M) break;
				}
				
			}
			
			// 바이퍼스 퍼뜨리기
			bfs(qu);
			
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			isSelected[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

	private static void bfs(Queue<Pos> qu) {
		int index =0;
		int zero = zeroCnt;
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i=0; i<size; i++) {
				Pos pos = qu.poll();
				
				// 4방향으로 바이퍼스 퍼뜨리기
				for(int k=0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					// 범위를 벗어나고 벽 또는 방문했을 경우
					if(x<0 || x>=N || y<0 || y>=N || map[x][y] ==1 || visited[x][y]) continue;
					
					qu.offer(new Pos(x,y));
					visited[x][y] = true;
					//빈칸을 바이러스로 활성화 했을 경우
					if(map[x][y] == 0)zero--;
				}
			}
			index++;
			//더이상 검사할 필요가 없으므로
			if(zero == 0) break;
			//더 이상 최소값이 될 수 없으므로
			if(min<index) return;
		}
		
		//모든 빈칸에 바이러스가 퍼졌다면
		if(zero == 0) min = Math.min(min, index);
	}

	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}