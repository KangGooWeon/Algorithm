
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution{
	static class Ham{
		int taste;
		int kal;
		
		public Ham (int taste, int kal) {
			this.taste = taste;
			this.kal = kal;
		}
	}
	
	static int N, K, max;
	static Ham[] HamList;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			HamList = new Ham[N];
			isSelected = new boolean[N];
			max=0;
			
			for(int i =0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int taste = Integer.parseInt(st.nextToken());
				int kal = Integer.parseInt(st.nextToken());
				
				HamList[i] = new Ham(taste, kal);
			}
		
			sub(0);
			System.out.println("#"+test+" "+max);
		}
	}

	private static void sub(int cnt) {
		if (cnt == N) {
			int taste = 0;
			int kal = 0;
			int count =0;
			for(int k =0; k<N; k++) {
				if(!isSelected[k]) count++;
			}
			
			if (count == N) return; 
			
			for(int k =0; k<N; k++) {
				if(isSelected[k]) {
					taste += HamList[k].taste;
					kal += HamList[k].kal;
				}
			}
			if(kal<=K) 
				if (taste > max) max=taste;
			
			return;
		}
	
		isSelected[cnt] = true;
		sub(cnt+1);
		isSelected[cnt] = false;
		sub(cnt+1);
	}
}

