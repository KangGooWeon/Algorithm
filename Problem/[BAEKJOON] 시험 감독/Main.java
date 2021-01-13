import java.io.*;
import java.util.*;

public class Main{
	static int N,M, min, cctvIndex;
	static int[][] map, mapCopy;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[] isSelected;
	static List<Pos> cctvList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cctvList = new ArrayList<>();
		map = new int[N][M];
		mapCopy = new int[N][M];
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(0<map[i][j] && map[i][j] <6) {
					cctvList.add(new Pos(i,j));
					cctvIndex++;
				}
			}
		}
		
		isSelected = new int[cctvIndex];
		permutation(0);
		
		System.out.println(min);
	}
	private static void permutation(int cnt) {
		if(cnt == cctvIndex) {
			//맵 카피
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					mapCopy[i][j] = map[i][j]; 
				}
			}
			
			//cctv종류에 따라 맵에 체크
			for(int i=0; i<cctvList.size(); i++) {
				Pos pos = cctvList.get(i);
				int cctvNum = map[pos.x][pos.y];
				
				int dis = isSelected[i];
				
				switch(cctvNum) {
				case 1:
					checkCctv(pos.x, pos.y, dis);
					break;
				case 2:
					checkCctv(pos.x, pos.y, dis);
					checkCctv(pos.x, pos.y, (dis+2)%4);
					break;
				case 3:
				case 4:
				case 5:
					for(int j=0; j<cctvNum-1; j++) {
						int a = (dis+j)%4;
						checkCctv(pos.x, pos.y, a);
					}
					break;
				}
			}
			
			//사각지대 갯수 체크 and 최소값
			int area =0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(mapCopy[i][j] == 0) area++; 
				}
			}
			
			min = Math.min(min, area);
			return;
		}
		
		for(int i=0; i<4; i++) {
			isSelected[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	public static void checkCctv(int x, int y, int dis) {
		while(true) {
			x += dx[dis];
			y += dy[dis];
			
			if(x<0 || x>=N || y<0 || y>=M || mapCopy[x][y] == 6) break;
			// cctv
			if(0<mapCopy[x][y] && mapCopy[x][y] <6) continue;
			
			mapCopy[x][y] = 9;
		}
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
