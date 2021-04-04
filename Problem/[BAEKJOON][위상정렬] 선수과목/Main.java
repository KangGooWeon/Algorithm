import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] topological = new int[N];
		int[] order = new int[N];
		List<Integer>[] list = new List[N];
		
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			
			list[start].add(end);
			topological[end]++;
		}
		
		// 위상정렬
		Queue<Integer> qu = new LinkedList<Integer>();
		
		// 초기 진입0인 정점 넣어주기
		for(int i = 0; i<N; i++) {
			if (topological[i] == 0) {
				qu.offer(i);
				order[i]++;
			}
		}
		
		for (int i = 0; i < N; i++) {
			// 정점을 다 방문 하기 전에 큐가 빈다면 사이클 발생
			if (qu.isEmpty()) break;
			
			int curr = qu.poll();
			int size = list[curr].size();
			List Edge = list[curr];
			
			for (int j=0; j<size; j++) {
				int vertex = (int) Edge.get(j);
				topological[vertex]--;
				
				if(topological[vertex] == 0) {
					qu.offer(vertex);
					if(order[vertex] < order[curr]+1) order[vertex] = order[curr]+1;
				}
			}
		}
		
		for(int i=0; i<order.length; i++) {
			System.out.print(order[i]+" ");
		}
	}
}
