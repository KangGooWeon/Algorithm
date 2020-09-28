import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] parents;
	static List<Data> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		make();
		
		list = new ArrayList<>();
		
		for(int i = 0; i< M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			list.add(new Data(start,end,num));
		}
		
		Collections.sort(list);
		
		int cnt = 0;
		int min = 0;
		for(int i = 0; i< M; i++) {
			Data data = list.get(i);
			
			if(!union(data.start, data.end)) {
				cnt++;
				min += data.num;
			}
			
			if(cnt == N-1) break;
		}
		
		System.out.println(min);
	}
	
	public static void make() {
		for(int i = 1; i<= N; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if (parents[a] == a ) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if ( aRoot == bRoot) return true;
		
		parents[bRoot] = aRoot;
		
		return false;
	}
	
	static class Data implements Comparable<Data>{
		int start;
		int end;
		int num;
		public Data(int start, int end, int num) {
			super();
			this.start = start;
			this.end = end;
			this.num = num;
		}
		@Override
		public int compareTo(Data o) {
			return this.num - o.num;
		}
	}
}
