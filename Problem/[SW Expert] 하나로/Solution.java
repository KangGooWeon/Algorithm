package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import sun.security.provider.certpath.Vertex;

public class Solution{
	static class Pos implements Comparable<Pos>{
		int no;
		int x;
		int y;
		long total;
		public Pos(int x, int y, long total) {
			this.x= x;
			this.y= y;
			this.total = total;
		}
		
		public Pos(int no, int x, int y, long total) {
			super();
			this.no = no;
			this.x = x;
			this.y = y;
			this.total = total;
		}
		@Override
		public int compareTo(Pos o) {
			return Long.compare(this.total, o.total);
		}
	}
	static int N;
	static int[] xPos, yPos;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			xPos = new int[N];
			visited = new boolean[N];
			
			List<Pos> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				xPos[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int y = Integer.parseInt(st.nextToken());
				list.add(new Pos(xPos[i], y, Long.MAX_VALUE));
			}
			
			double E = Double.parseDouble(br.readLine());
			
			PriorityQueue<Pos> pQueue = new PriorityQueue<Pos>();

			Pos pos = list.get(0);
			pQueue.add(new Pos(0,pos.x, pos.y, 0));
			long result=0;
			Pos current = null;
			
			while(!pQueue.isEmpty()) {
				current = pQueue.poll();
				
				if(visited[current.no]) continue; 	// PQ에 남아있던 totalDistance의 최소비용보다 더 큰 상태
				
				visited[current.no] = true;
				result += current.total;
				
				
				// 2단계 : 선택된 current 정점을 경유지로 해서 아직 방문하지 않은 다른 정점으로의 최단거리 비용 계산하여 유리하면 update
				for(int j =0; j<N; j++) {
					// min ==> distance[current]
					if(!visited[j]) {
						Pos p = list.get(j);
						long d = (long) ((Math.pow(p.x - current.x, 2) + Math.pow(p.y - current.y, 2)));
						long a =  Math.round(d * E);
						if(p.total>a) {
							p.total = a;
							pQueue.add(new Pos(j,p.x, p.y, a));
						}
					}
				}
			}
			
			System.out.println("#"+t+" "+result);
		}
	}
}
