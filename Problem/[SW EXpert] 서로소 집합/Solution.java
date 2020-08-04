package com.ssafy;

import java.util.Scanner;

public class Solution {
	private static int N,M;
	static int[] parents;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t =1; t<=T; t++) {
			N = sc.nextInt();
			parents = new int[N+1];
			make();
			M = sc.nextInt();
			
			System.out.print("#"+t+" ");
			for(int i=0; i<M; i++) {
				int a =sc.nextInt();
				switch(a) {
				case 0:
					int x = sc.nextInt();
					int y = sc.nextInt();
					union(x, y);
					break;
				case 1:
					int x1 = sc.nextInt();
					int x2 = sc.nextInt();
					x1 = find(x1);
					x2 = find(x2);
					if (x1 != x2) {
						System.out.print(0);
						break;
					}
					System.out.print(1);
					break;
				}
			}
			System.out.println();
		}
	}
	
	static void make() {
		for (int i = 0; i < N; i++) {
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
}
