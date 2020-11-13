import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<List<Integer>> dragonDir;
	static Pos lastDragon[];
	static int[] sede;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static int[][] map = new int[101][101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		lastDragon = new Pos[N];
		sede = new int[N];
		dragonDir = new ArrayList<>();
		for(int i =0; i<N; i++) {
			dragonDir.add(new ArrayList<>());
		}
		
		for(int i =0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			sede[i] = Integer.parseInt(st.nextToken());
			
			map[x][y] =1;
			map[x+dx[dir]][y+dy[dir]] = 1;
			lastDragon[i] = new Pos(x+dx[dir], y+dy[dir]);
			dragonDir.get(i).add(dir);
		}
		
		for(int i =0; i<Arrays.stream(sede).max().getAsInt(); i++) {
			for(int j = 0; j<N; j++) {
				if(sede[j] <= i) continue;
				
				List<Integer> dra = dragonDir.get(j);
				List<Integer> temp = new ArrayList<>();
				for(int d =0; d<dra.size(); d++) {
					int draDir = dra.get(d);
					temp.add((draDir+1)%4);
				}
				
				//커브 뒤부터 추가 하기
				Pos pos = lastDragon[j];
				int x= pos.x;
				int y= pos.y;
				for(int d =temp.size()-1; d>=0; d--) {
					dragonDir.get(j).add(temp.get(d));
					x += dx[temp.get(d)];
					y += dy[temp.get(d)];
					
					map[x][y] = 1;
				}
				lastDragon[j] = new Pos(x,y);
			}
		}
		
		int answer =0;
		
		for(int i =0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] == 0) continue;
				
				if(check(i,j)) answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean check (int i, int j) {
		if(map[i][j+1] == 0 || map[i+1][j] == 0 || map[i+1][j+1] == 0) return false;
		return true;
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
