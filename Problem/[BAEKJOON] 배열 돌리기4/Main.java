import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N,M,K,min;
	static boolean[] isChecked;
	static int[] isSelected;
	static int[][] map, mapCopy;
	static List<Data> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int [N+1][M+1];
		mapCopy = new int [N+1][M+1];
		list = new ArrayList<>();
		min = 100000;
		isChecked = new boolean[K];
		isSelected = new int[K];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x =	Integer.parseInt(st.nextToken());
			int y =	Integer.parseInt(st.nextToken());
			int k =	Integer.parseInt(st.nextToken());
			list.add(new Data(x,y,k));
		}
		
		perm(0);
		System.out.println(min);
	}
		
	private static void perm(int cnt) {
		if (cnt == K){
			for(int i = 1; i<=N; i++){
				mapCopy[i] = Arrays.copyOf(map[i], M+1);
			}
			
			for(int i = 0; i<K; i++) {
				Data data = list.get(isSelected[i]);
				solution (data.x, data.y, data.k);
			}
			check();
			return;
		}
		
		for(int i = 0; i<K; i++) {
			if(isChecked[i]) continue;
			
			isSelected[cnt] = i;
			isChecked[i] = true;
			perm(cnt+1);
			isChecked[i] = false;
		}
	}

	private static void check() {
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += mapCopy[i][j];
			}
			if (sum < min) min =sum;
		}
	}

	private static void solution(int r, int c, int s) {
		for(int k =1; k<=s; k++) {
			int x = r-k;
			int y = c-k;
			int temp = mapCopy[x][y];
			
			// >>> 오른쪽
			for(int i = y+1; i<=c+k; i++) {
				int num = mapCopy[x][i];
				mapCopy[x][i] = temp;
				temp = num;
			}
			
			// vvv 아래로
			x = r-k;
			y = c+k;
			for (int i = x+1; i<=r+k; i++) {
				int num = mapCopy[i][y];
				mapCopy[i][y] = temp;
				temp = num;
			}
			
			// <<< 왼쪽
			x = r+k;
			y = c+k;
			for (int i = y-1; i>=c-k; i--) {
				int num = mapCopy[x][i];
				mapCopy[x][i] = temp;
				temp = num;
			}
			
			//^^^ 위로
			x = r+k;
			y = c-k;
			for(int i = x-1; i>=r-k; i--) {
				int num = mapCopy[i][y];
				mapCopy[i][y]= temp;
				temp = num;
			}
		}
	}

	static class Data{
		int x;
		int y;
		int k;
		
		public Data(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
}
