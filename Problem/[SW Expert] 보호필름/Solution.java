
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int D, W, K;
	private static boolean check;
	private static int[] isSelected, numbers;
	private static int[][] map, mapCopy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			mapCopy = new int[D][W];
			check = false;
			isSelected = new int[K];
			numbers = new int[D];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int min = 0;
			for(int i = 0; i<=K; i++) {
				permu(0, i);
				if (check) {
					min = i;
					break;
				}
			}
			
			System.out.println("#"+t+" "+min);
		}
	}

	private static void permu(int cnt, int end) {		// A B 자리 순열
		if (cnt == end) {
			permu2(0,0,end);
			return;
		}
		
		for(int i = 0; i<2; i++) {
			isSelected[cnt] = i;
			permu(cnt+1, end);
		}
	}

	private static void permu2(int cnt, int start, int end) {
		if (cnt == end) {
			for (int i = 0; i < D; i++) {
		        mapCopy[i] = Arrays.copyOf(map[i], W);
		    }
			
			for(int i = 0; i<end; i++) {	// 채우기
				for(int j =0; j<W; j++) {
					mapCopy[numbers[i]][j] = isSelected[i];
				}
			}
			
			int totalCount = 0;
			for(int j =0; j<W; j++) {
				int A =0;
				int B =0;
				for(int i =0; i<D; i++) {
					if (mapCopy[i][j] == 0) {
						A++; B =0;
					}
					if (A == K) {
						totalCount++; break;
					}
					
					if (mapCopy[i][j] == 1) {
						B++; A =0;
					}
					if (B == K) {
						totalCount++; break;
					}
					if (i+1 == D) return;
				}
			}
			
			if (totalCount==W) check= true;
			
			return;
		}
		
		for(int i = start; i<D; i++) {
			numbers[cnt] = i;
			permu2(cnt+1, i+1, end);
		}
	}
}
