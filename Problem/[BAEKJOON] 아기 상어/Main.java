import java.io.*;
import java.util.*;

public class Main {
	static int N, eatNum, shakeSize, result;
	static Pos shake;
	static int[][] map;
	static boolean[][] visited;
	static List<Pos> list;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		shakeSize = 2;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shake = new Pos(i,j);
			}
		}
		
		while(true) {
			//이동한 위치에서 먹을 물고기 찾기
			if(!bfs()) break;
		}
		
		System.out.println(result);
	}
	
	private static boolean bfs() {
		Queue<Pos> qu = new LinkedList<>();
		visited = new boolean[N][N];
		qu.offer(new Pos(shake.x, shake.y));
		visited[shake.x][shake.y] = true;
		int index =0;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			list = new ArrayList<>();
			for(int i=0; i<size; i++) {
				Pos p = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					
					//범위와 자기보다 큰 물고기는 못 지나간다.
					if(x<0 || x>=N || y<0 || y>=N || shakeSize < map[x][y] || visited[x][y]) continue;
					
					visited[x][y] = true;
					qu.offer(new Pos(x,y));
					
					//똑같이 이동한 거리 중에 먹을 수 있는 물고기 저장
					if(map[x][y] !=0 && map[x][y] < shakeSize) list.add(new Pos(x,y));
				}
			}
			
			index++;
			//먹을 수 있는 물고기 존재
			if(list.size()!=0) {
				//조건에 맞는 가장 위중에서 가장 왼쪽에 있는 물고기로 정렬
				Collections.sort(list);
				
				//위치 변경
				map[shake.x][shake.y] =0;
				shake.x = list.get(0).x;
				shake.y = list.get(0).y;
				map[shake.x][shake.y] =9;
				eatNum++;
				
				//상어 사이즈 변경
				if(eatNum == shakeSize) {
					eatNum = 0;
					shakeSize++;
				}
				
				//이동 거리 추가
				result += index;
				return true;
			}
		}
		
		return false;
	}

	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Pos o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
		
	}
}