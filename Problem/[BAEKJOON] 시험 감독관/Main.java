import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] map = new int[N];
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		//총 감독관 배치
		for(int i=0; i<N; i++) {
			map[i] -= B;
		}
		
		long index = N;
		
		for(int i=0; i<N; i++) {
			if(map[i] <= 0) continue;
			
			index += map[i]/C;
			if(map[i] % C !=0) index++;
		}
		
		System.out.println(index);
	}
}
