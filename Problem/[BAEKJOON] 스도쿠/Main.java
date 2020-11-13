import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[9][9];
	static boolean[] result = new boolean[9];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i =0; i<9; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		
		dfs(0,0,0);
		
		for(int i =0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void dfs(int i, int j, int cnt) {
		if (i == 9) {
			for (int a = 0; a < 9; a++) {
				for (int b = 0; b < 9; b++) {
					System.out.print(map[a][b]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		if(cnt == 9) {
			dfs(i+1,0,0);
			return;
		}
		
		if(map[i][j] != 0) {
			dfs(i, j+1, cnt+1);
			return;
		}
		
		for(int k=1; k<=9; k++) {
			map[i][j] = k;
			if(!rowColCheck(i,j) || !quadCheck(i,j)) {
				map[i][j] = 0;
				continue;
			}
			
			dfs(i, j+1, cnt+1);
			map[i][j] = 0;
		}
	}

	private static boolean quadCheck(int i, int j) {
		for(int a = (i/3) *3; a< ((i/3) *3) +3; a++) {
			for(int b = (j/3) *3; b<((j/3) *3) +3; b++) {
				if(i == a && j == b) continue;
				if(map[a][b] == map[i][j]) return false;
			}
		}
		return true;
	}

	private static boolean rowColCheck(int i, int j) {
		// 왼쪽
		for (int k = j - 1; k >= 0; k--) {
			if (map[i][k] == map[i][j]) return false;
		}

		// 오른쪽
		for (int k = j + 1; k < 9; k++) {
			if (map[i][k] == map[i][j]) return false;
		}
		
		// 위
		for (int k = i - 1; k >= 0; k--) {
			if (map[k][j] == map[i][j]) return false;
		}
		
		// 아래
		for (int k = i + 1; k<9; k++) {
			if (map[k][j] == map[i][j]) return false;
		}
		
		return true;
	}
}
