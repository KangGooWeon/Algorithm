package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	static int N, M, max;
	static List<Pos> home, homeCopy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max =0;
			home = new ArrayList<>();
			homeCopy = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					if (n == 1) home.add(new Pos(i,j));
				}
			}
			
			for(int i = 0; i<N; i++) {
				for (int j =0; j<N; j++){
					copy();
					int k = 1;
					int homeCnt =0;
					while(true) {
						for(int h =0; h<homeCopy.size(); h++) {
							Pos diff = homeCopy.get(h);
							int d = Math.abs(i - diff.x) + Math.abs(j - diff.y);
							if ( d < k) {
								homeCnt++;
								homeCopy.remove(h);
								h--;
							}
						}
						int cost = k*k+(k-1)*(k-1);
						int num = (M*homeCnt) - cost;
						if (num >=0) {
							if (max < homeCnt) max = homeCnt;
						}
						
						if (homeCopy.size() == 0) break;
						k++;
					}
				}
			}
			
			System.out.println("#"+t+" "+max);
		}
	}
	
	private static void copy() {
		for(int i = 0; i<homeCopy.size(); i++) {
			homeCopy.remove(i);
			i--;
		}
		
		for(int i = 0; i<home.size(); i++) {
			homeCopy.add(home.get(i));
		}
	}

	static class Pos{
		int x;
		int y;
		public Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
