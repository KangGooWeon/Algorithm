import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder result = new StringBuilder();
		int[] distance = new int[N+1];
		List<Integer>[] list = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list[start].add(end);
			distance[end]++;
		}
		
		PriorityQueue<Data> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			if(distance[i] == 0) pq.offer(new Data(i,1));
		}
		
		while(!pq.isEmpty()) {
			Data data = pq.poll();
			result.append(data.no+" ");
			
			for(int i=0; i<list[data.no].size(); i++) {
				distance[list[data.no].get(i)]--;
				
				if(distance[list[data.no].get(i)] == 0) {
					pq.offer(new Data(list[data.no].get(i), 0));
				}
			}
		}
		
		System.out.println(result.toString());
	}
	
	static class Data implements Comparable<Data>{
		int no;
		int priorty;
		public Data(int no, int priorty) {
			super();
			this.no = no;
			this.priorty = priorty;
		}
		@Override
		public int compareTo(Data o) {
			if(this.priorty == o.priorty) return this.no - o.no;
			return this.priorty - o.priorty;
		}
	}
}
