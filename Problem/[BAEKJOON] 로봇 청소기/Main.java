import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Pos robot;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		robot = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		for(int i =0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count =0;
		while(true) {
			//현 위치 청소
			map[robot.x][robot.y] = 2;
			
			//c번
			if(count==4) {
				int d =0;
				if(robot.dis == 0) d =2;
				else if(robot.dis == 1) d =3;
				else if(robot.dis == 2) d =0;
				else if(robot.dis == 3) d =1;
				
				//d번
				if(map[robot.x +dx[d]][robot.y+dy[d]] == 1) break;
				
				robot.x = robot.x +dx[d];
				robot.y = robot.y +dy[d];
				count =0;
				continue;
			}
			
			//현재 위치에서 방향 기준 움직인다.
			//a번
			int dis = 0;
			if(robot.dis == 0) dis =3;
			else if(robot.dis == 1) dis =0;
			else if(robot.dis == 2) dis =1;
			else if(robot.dis == 3) dis =2;
			
			if(map[robot.x +dx[dis]][robot.y+dy[dis]] == 0) {
				robot.x = robot.x +dx[dis];
				robot.y = robot.y +dy[dis];
				robot.dis = dis;
				count =0;
				continue;
			}else {
				//b번
				robot.dis = dis;
				count++;
				continue;
			}
		}
		
		int cnt =0;
		for(int i =1; i<N-1; i++) {
			for(int j =1; j<M-1; j++) {
				if(map[i][j] == 2) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static class Pos{
		int x;
		int y;
		int dis;
		public Pos(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
}
