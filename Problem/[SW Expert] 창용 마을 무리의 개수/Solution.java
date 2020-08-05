package swexpert;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int N,M;
	static int[] parents;
	static int[] result;
	
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
//		return find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 1; i <=T; i++) {
			N = sc.nextInt();
			parents = new int[N+1];
			result = new int[N+1];
			make();
			M = sc.nextInt();
			
			for(int j =0; j<M; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				union(a,b);
			}
			
			for(int j =1; j<=N; j++) {
				result[find(j)]++;
			}
			
			int count =0;
			for(int j =1; j<=N; j++) {
				if(result[j] == 0) continue;
				count++;
			}
			System.out.println("#"+i+" "+count);
		}
	}
}




















