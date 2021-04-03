import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine())-1;
		List<List> list = new ArrayList<>();
		
		for(int i=0; i<V; i++) {
			list.add(new ArrayList<Data>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken());
			
			List data = list.get(x);
			
			data.add(new Data(y,e));
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		int[] distance = new int[V];
		
		//초기값 무한대로 설정
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//첫번째 노드 넣어주기
		pq.add(new Vertex(start,0));
		distance[start] = 0;
		
		Vertex current = null;
		
		while(!pq.isEmpty()) {
			// 방문하지 않은 정점들 중에 출발지에서 자신까지 오는 비용이 최단인 정점을 고려할 경유지로 선택
			current = pq.poll();
			
			//이미 처리가 끝남
			if(visited[current.no]) continue;
			
			//visited는 처리가 끝난 정점을 검사하는거라 꺼내는 순간에 true
			visited[current.no] = true;
			
			//current 정점을 경유지로 해서 아직 방문하지 않은 다른 정점으로의 최단거리 비용 계산하여 유리하면 update
			List tmp = list.get(current.no);
			for(int i=0; i<tmp.size(); i++) {
				Data data = (Data) tmp.get(i);
				if(distance[data.end] > current.totalInstance + data.wight 
						&& !visited[data.end]) {
					distance[data.end] = current.totalInstance + data.wight ;
					pq.add(new Vertex(data.end, distance[data.end]));
				}
			}
		}
		
		//distance 찍어주기
		for(int i=0; i<distance.length; i++) {
			if(distance[i] == Integer.MAX_VALUE)System.out.println("INF");
			else System.out.println(distance[i]);
		}
		
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no;
		int totalInstance;
		public Vertex(int Vertex, int totalInstance) {
			super();
			this.no = Vertex;
			this.totalInstance = totalInstance;
		}
		@Override
		public int compareTo(Vertex o) {
			// totalDistance가 작은 비용이 우선적으로 처리
			return this.totalInstance - o.totalInstance;
		}
	}
	
	static class Data{
		int end;
		int wight;
		
		public Data(int end, int wight) {
			super();
			this.end = end;
			this.wight = wight;
		}
	}
}
