
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int check = 0;
			
			int[] snack = new int [N];
			
			for(int i =0; i<N; i++) {
				snack[i] = sc.nextInt();
				
			}
			
			int max =0;
			for(int i =0; i<N; i++) {
				for(int j =i+1; j<N; j++){
					int val = snack[i] + snack[j];
					if(val <=M) {
						if(max < val) {
							max = val;
							check = 1;
						}
					}
				}
			}
			
			int result = (check == 0)?-1:max;
			System.out.println("#"+t+" "+ result);
		}
	}
}
