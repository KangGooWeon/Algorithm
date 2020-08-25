package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, min;
	private static int[] select;
	private static List<Pos> chicken;
	private static List<Pos> home;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		select = new int[M];
		min = 100000;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 1) home.add(new Pos(i,j));
				if (a == 2) chicken.add(new Pos(i,j));
			}
		}

		combination(0,0);
		System.out.println(min);
	}
	
	private static void combination(int cnt, int start) {
		if(cnt == M) {
			int minDis = 0;
			for (int j=0; j<home.size(); j++) {
				Pos posHome = home.get(j);
				int homeMin = 1000;
				for(int i =0; i<M; i++) {
					Pos posChic = chicken.get(select[i]);
					int dis = Math.abs(posHome.x -posChic.x) + Math.abs(posHome.y - posChic.y);
					if(dis<homeMin) homeMin = dis;
				}
				minDis += homeMin;
			}
			
			if (minDis<min) min = minDis;
			return;
		}
		
		
		for(int i = start; i<chicken.size(); i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}