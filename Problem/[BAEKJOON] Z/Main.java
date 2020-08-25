
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, R, C, num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = 2;
		for (int i =1; i<N; i++) {
			M = M*2;
		}
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		divide(0, 0, M);
		
	}
	
	public static void divide(int i, int j, int N) {
		if (N == 2) {
			for (int k =i; k<i+2; k++) {
				for(int h = j; h<j+2; h++) {
					if (k == R && h == C) System.out.println(num);
					num++;
				}
			}
			
			return;
		}
		
		 int size = N/2;
		 
		 divide(i, j, size);
		 divide(i, j+size, size);
		 divide(i+size, j, size);
		 divide(i+size, j+size, size);
	}
}
