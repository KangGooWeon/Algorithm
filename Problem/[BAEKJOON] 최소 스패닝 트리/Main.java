package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	static int N,M,result;
	static PriorityQueue<Data> list;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new PriorityQueue<>();
		parents = new int[N+1];
		make();
		
		for(int i =0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.offer(new Data(start, end, weight));
		}
		
		int select = 0;
		while(!list.isEmpty()) {
			Data data = list.poll();
			if(union(data.start, data.end)) {
				result += data.weight;
				select++;
			}
			
			if (select == N-1) break;
		}
		
		System.out.println(result);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int find(int n) {
		if (parents[n] == n) return n;
		return parents[n] = find(parents[n]);
	}
	static void make() {
		for(int i =1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	static class Data implements Comparable<Data> {
		int start;
		int end;
		int weight;
		

		@Override
		public int compareTo(Data o) {
			return this.weight - o.weight;
		}


		public Data(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
	}
}
