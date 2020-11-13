import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] map;
	static int dong;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[(N*2)+(M*2)];
		
		int K = Integer.parseInt(br.readLine());
		for(int i = 1; i<=K+1; i++) {
			st = new StringTokenizer(br.readLine());
			int dis =  Integer.parseInt(st.nextToken());
			int num =  Integer.parseInt(st.nextToken());
			
			int cur = 0;
			
			if (dis == 1) cur = num;
			else if (dis == 2) cur = N+M+(N-num);
			else if (dis == 3) cur = (N*2)+M+(M-num);
			else if (dis == 4) cur = N + num;
			
			if (i == K+1) {
				dong = cur;
			}else {
				map[cur] = i;
			}
		}
		
		int sum = 0;
		for(int i = 1; i<=K; i++) {
			sum += check(i);
		}
		
		System.out.println(sum);
	}

	private static int check(int store) {
		int max = (N*2)+(M*2)-1;
		// ¿ÞÂÊ
		int index = dong;
		int leCnt = 0;
		while(true) {
			index--;
			leCnt++;
			if(index < 0) index = (N*2)+(M*2)-1;
			if(map[index] == store) break;
		}
		
		// ¿À¸¥ÂÊ
		index = dong;
		int riCnt = 0;
		while(true) {
			index++;
			riCnt++;
			if(map[index%(max+1)] == store) break;
		}
		
		return Math.min(leCnt, riCnt);
	}
}
