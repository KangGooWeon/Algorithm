import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static List<Data> list;
	static int[][] fireBallCheck;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	static int[][] fireDx = {{-1,0,1,0},{-1,1,1,-1}};
	static int[][] fireDy = {{0,1,0,-1},{1,1,-1,-1}};
	static int[][] fireDis = {{0,2,4,6},{1,3,5,7}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list.add(new Data(r,c,m,s,d));
		}
		
		for(int i=0; i<K; i++) {
			fireBallCheck = new int[N][N];
			//이동
			fireMove();
			fireBallSpread();
		}
		
		int sum =0;
		for(int i=0; i<list.size(); i++) {
			sum += list.get(i).mass;
		}
		
		System.out.println(sum);
	}
	
	private static void fireBallSpread() {
		List<Data> temp = new ArrayList<>();
		
		// 파이어볼 2개이상 확인
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(fireBallCheck[i][j] < 2) continue;
				
				//파이어볼 4개로 나누기 위해 필요한 값들
				int index =0;
				int fireCnt =0;
				int evenCnt =0;
				int mass =0;
				int speed = 0;
				
				//자기가 같은 파이어볼 검사
				while(true) {
					if(index == list.size()) break;
					
					Data data = list.get(index++);
					if( i == data.x && j == data.y) {
						mass += data.mass;
						speed += data.speed;
						fireCnt++;
						if(data.dis % 2 == 0)evenCnt++;
						list.remove(--index);
					}
				}
				//질량이 0이면 소멸
				if(mass/5 ==0) continue;
				
				//방향 정해주는 홀수 짝수
				int disCheck = 1;
				if(evenCnt ==0 || evenCnt == fireCnt) disCheck =0;
				
				//파이어볼 나눠주기
				for(int k =0; k<4; k++) {
					list.add(new Data(i,j,mass/5,speed/fireCnt,fireDis[disCheck][k]));
				}
			}
		}
	}

	private static void fireMove() {
		int size = list.size();
		int index =0;
		while(true) {
			if(index == size) break;
			index++;
			
			Data data = list.get(0);
			list.remove(0);
			int x = data.x;
			int y = data.y;
			for(int k =0; k<data.speed; k++) {
				x += dx[data.dis];
				y += dy[data.dis];
				
				if(x<0) x=N-1;
				else if(x>=N) x=0;
				
				if(y<0) y=N-1;
				else if(y>=N) y=0;
			}
			
			//파이어볼 겹치는거 확인하기 위해 갯수 체크
			fireBallCheck[x][y]++;
			list.add(new Data(x,y,data.mass, data.speed, data.dis));
		}
	}

	static class Data{
		int x;
		int y;
		int mass;
		int speed;
		int dis;
		public Data(int x, int y, int mass, int speed, int dis) {
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.speed = speed;
			this.dis = dis;
		}
	}
}