
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, min;
	static List<Pos> home;
	static List<Pos> customer;
	static boolean[] isChecked;
	static int[] isSelected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			N = Integer.parseInt(br.readLine());
			home = new ArrayList<>();
			customer = new ArrayList<>();
			isChecked = new boolean[N];
			isSelected = new int[N];
			min = 100000;

			StringTokenizer st = new StringTokenizer(br.readLine());
			// 회사 위치 저장
			home.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			// 집 위치 저장
			home.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			// 고객 위치 저장
			for (int i = 0; i < N; i++) {
				customer.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			perm(0);

			System.out.println("#" + t + " " + min);
		}

	}
	
	static public void perm (int cnt) {
		if(cnt == N) {
			int sum = 0;
			
			// 회사와 첫번째 고객집과의 거리
			Pos comPos = home.get(0);
			Pos cusPos = customer.get(isSelected[0]);
			sum += Math.abs(comPos.x - cusPos.x) + Math.abs(comPos.y - cusPos.y);
			
			// 고객과 고객간의 이동거리
			for(int i = 1; i<N; i++) {
				Pos first = customer.get(isSelected[i-1]);
				Pos second = customer.get(isSelected[i]);
				sum += Math.abs(first.x - second.x) + Math.abs(first.y - second.y);
			}
			
			comPos = home.get(1);
			cusPos = customer.get(isSelected[N-1]);
			sum += Math.abs(comPos.x - cusPos.x) + Math.abs(comPos.y - cusPos.y);
			
			if (sum < min) min = sum;
		}
		
		for(int i =0; i<N; i++) {
			if (isChecked[i]) continue;
			
			isSelected[cnt] = i;
			isChecked[i] = true;
			perm(cnt+1);
			isChecked[i] = false;
		}
	}
	
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
