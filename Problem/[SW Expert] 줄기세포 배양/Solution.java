package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	static class Cell{
		int x;
		int y;
		int num;
		int vital;
		
		public Cell (int x, int y, int num, int vital) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.vital = vital;
		}
	}

	static class CellComparator implements Comparator<Cell> {
		@Override
		public int compare(Cell o1, Cell o2) {
			if (o1.num == o2.num) return (o1.vital - o2.vital) * -1;
			
			return o1.num - o2.num;
		}

	}
	
	private static int N, M, K;
	private static int[][] map;
	private static List<Cell> onVital;
	private static List<Cell> offVital;
	private static int[] dx = { 0,0,-1,1};
	private static int[] dy = { 1,-1,0,0};
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[15000][15000];
			onVital = new ArrayList<Cell>();
			offVital = new ArrayList<Cell>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int a = Integer.parseInt(st.nextToken());
					map[7500-N+i][7500-M+j] = a;
					if( a > 0) offVital.add(new Cell(7500-N+i, 7500-M+j, a, a));
				}
			}
			
			for(int i =0; i<K-1; i++) {
				if (onVital.size() != 0) Collections.sort(onVital, new CellComparator());
				offCheck();
				onCheck();
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(onVital.size() + offVital.size());
			System.out.println(sb.toString());
		}
	}

	private static void offCheck() {
		for(int i =0; i<offVital.size(); i++) {
			Cell cell = offVital.get(i);
			cell.vital -= 1;
			
			if (cell.vital == 0 )
			{
				onVital.add(new Cell(cell.x, cell.y, cell.num, cell.num));
				offVital.remove(i);
				i--;
			}
		}
	}

	private static void onCheck() {
		for(int i = 0; i<onVital.size(); i++) {
			Cell cell = onVital.get(i);
			if (cell.num == cell.vital) {
				for(int j = 0; j<4; j++) {
					int x = cell.x +dx[j];
					int y = cell.y +dy[j];
					
					if(map[x][y] == 0) {
						offVital.add(new Cell(x,y,cell.num, cell.num));
						map[x][y] = cell.num;
					}
				}
			}
			cell.vital -=1;
			if (cell.vital == 0) {
				onVital.remove(i);
				i--;
			}
		}
	}
}