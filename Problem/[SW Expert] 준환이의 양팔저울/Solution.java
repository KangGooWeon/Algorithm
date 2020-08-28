
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N, totalCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			N = Integer.parseInt(br.readLine());
			int [] map = new int[N];
			boolean[] isLeftChecked = new boolean[N];
			int[] isLeftSelected = new int[N];
			totalCnt =0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}

			leftPerm(0, map, isLeftChecked, isLeftSelected);

			System.out.println("#" + t + " " + totalCnt);
		}
	}

	private static void leftPerm(int cnt, int[] map, boolean[] isLeftChecked, int[] isLeftSelected) {
		if(cnt == N) {
			subset(0,0,0, isLeftSelected);
		}
		
		for(int i =0; i<N; i++) {
			if (isLeftChecked[i]) continue;
			
			isLeftSelected[cnt] = map[i];
			isLeftChecked[i] = true;
			leftPerm(cnt+1, map, isLeftChecked, isLeftSelected);
			isLeftChecked[i] = false;
		}
	}

	private static void subset(int left, int right, int cnt, int[] check) {
		if (cnt == N) {
			totalCnt++;
			return;
		}
         
		subset(left + check[cnt], right , cnt+1, check);
        if(left >= right + check[cnt] )
        	subset(left, right + check[cnt] , cnt+1, check);
	}
}
