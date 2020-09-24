
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] result = new int[N+1];
		int[][] map = new int[101][101];
		
		int cnt = 1;
		for(int k =0; k<N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			
			
			for(int i = 0 ; i<height; i++ ) {
				for(int j =0; j<width; j++) {
					int a = 100-y-i;
					int b = x+j;
					map[a][b]=cnt;
				}
			}
			
			cnt++;
		}
		
		for(int i = 0 ; i<101; i++ ) {
			for(int j =0; j<101; j++) {
				result[map[i][j]]++;

			}
		}
		
		for(int i=1; i<=N; i++) {
			System.out.println(result[i]);
		}
	}
}
