import java.io.*;
import java.util.*;

public class Main{
	static int N;
    static long max, min;
	static int[] num, oper, isSelected;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		max =-1000000000;
		min =1000000000;
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		oper = new int[N-1];
		//연산자 입력받기
		st = new StringTokenizer(br.readLine());
		int index =0;
		for(int i=0; i<4; i++) {
			int leng = Integer.parseInt(st.nextToken());
			for(int j=0; j<leng; j++) {
				oper[index++] = i;
			}
		}
		
		isSelected = new int[N-1];
		visited = new boolean[N-1];
		//순열
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	private static void permutation(int cnt) {
		if(cnt == N-1) {
			int result = num[0];
			for(int i=0; i<N-1; i++) {
				int oper = isSelected[i];
				
				switch (oper) {
				case 0:
					result += num[i+1];
					break;
				case 1:
					result -= num[i+1];
					break;
				case 2:
					result *= num[i+1];
					break;
				case 3:
					result /= num[i+1];
					break;
				}
			}
			
			max = Math.max(result, max);
			min = Math.min(result, min);
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			if(visited[i] == true)continue;
			
			isSelected[cnt] = oper[i];
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}
}
