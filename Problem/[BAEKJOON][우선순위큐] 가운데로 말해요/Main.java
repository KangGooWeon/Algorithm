import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> leftMax = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightMin = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		
		//맨 처음 값 넣어주기
		leftMax.offer(Integer.parseInt(br.readLine()));
		sb.append(leftMax.peek()+"\n");
		
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			//leftMax의  root와 num값 비교
			if(leftMax.peek() >= num) leftMax.offer(num);
			else rightMin.offer(num);
			
			//leftMax와 rightMax 좌우 높이 맞춰주기
			if(leftMax.size() == rightMin.size()+2) {
				rightMin.offer(leftMax.poll());
			}
			else if(leftMax.size()+2 == rightMin.size()) {
				leftMax.offer(rightMin.poll());
			}
			
			// leftMax의 사이즈와 rightMin 사이즈가 틀리다면 큰 쪽이 홀수의 가운데
			// 같다면 짝수에 해당 하기떄문에 leftMax의 peek가 조건에 의한 가운데 수이다.
			if(leftMax.size() >= rightMin.size()) sb.append(leftMax.peek());
			else sb.append(rightMin.peek());
			
			if(i != N-1) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
