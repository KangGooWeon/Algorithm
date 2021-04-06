import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int result =0;
		
		while(true) {
			if(pq.size() == 1) break;
			
			int num1 = pq.poll();
			int num2 = pq.poll();
			
			result += num1+num2;
			
			pq.offer(num1+num2);
		}
		
		System.out.println(result);
	}
}