import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static StringBuilder sb;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		map = new int[N][N];
		
		for (int i = 0; i<N; i++) {
			String s = br.readLine();
			for (int j =0; j<N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		divide(0,0,N);
		
		System.out.println(sb);
	}
	
	public static void divide(int x, int y, int n) {
		int temp = map[x][y];
		boolean flag = false;
		
		Outer:for (int i = x; i<x+n; i++) {
			for (int j =y; j<y+n; j++) {
				if (map[i][j] != temp) {
					flag = true;
					break Outer;
				}
			}
		}
		
		if(flag == false) {
			sb.append(temp);
		}
		else {
			sb.append("(");
			int size = n / 2;
			divide(x, y, size);
			divide(x, y + size, size);
			divide(x + size, y, size);
			divide(x + size, y + size, size);
			sb.append(")");
		}
	}
}