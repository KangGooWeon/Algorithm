
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Solution {
	static int N = 11, M, BC, sum;
	static int[] moveA, moveB, mA, mB;
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static List<Data> list;
	static Pos curA, curB;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			moveA = new int[M+1];
			moveB = new int[M+1];
			
			mA = new int[M];
			mB = new int[M];
			
			list = new ArrayList<>();
			
			sum =0;
			
			BC = Integer.parseInt(st.nextToken());
			curA = new Pos(1,1);
			curB = new Pos(10,10);
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<M; i++) {
				mA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<M; i++) {
				mB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < BC; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				list.add(new Data(y, x, C, P, i));
			}
			
			for(int i =0; i<=M; i++) {
				sum += check(i);
				if(i == M) break;
				curA.x += dx[mA[i]];
				curA.y += dy[mA[i]];
				
				curB.x += dx[mB[i]];
				curB.y += dy[mB[i]];
			}
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(sum);
			System.out.println(sb.toString());
		}
	}
	
	private static int check(int cur) {
		boolean[][] result = new boolean[2][BC];

		for(int i =0; i<BC; i++) {
			Data data = list.get(i);
			
			int DA = Math.abs(curA.x - data.x) + Math.abs(curA.y - data.y);
			if(DA <= data.C) result[0][i] = true;
			
			int DB = Math.abs(curB.x - data.x) + Math.abs(curB.y - data.y);
			if(DB <= data.C) result[1][i] = true;
		}
		
		int max = 0, value; 
		boolean checkA, checkB; 
		for (int i = 0; i < BC; i++) {
			checkA = result[0][i];
			for (int j = 0; j < BC; j++) {
				checkB = result[1][j];
				value = 0;
				if (i == j && checkA && checkB)
					value = list.get(i).P;
				else {
					if (checkA)
						value += list.get(i).P;
					if (checkB)
						value += list.get(j).P;
				}
				max = Math.max(max, value);
			}
		}
		return max;
	}

	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Data implements Comparable<Data>{
		int x;
		int y;
		int C;
		int P;
		int index;
		
		public Data(int x, int y, int c, int p, int index) {
			super();
			this.x = x;
			this.y = y;
			C = c;
			P = p;
			this.index = index;
		}

		@Override
		public int compareTo(Data o) {
			return (this.P - o.P)*-1;
		}

	}
}
