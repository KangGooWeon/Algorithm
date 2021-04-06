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
		
		//�� ó�� �� �־��ֱ�
		leftMax.offer(Integer.parseInt(br.readLine()));
		sb.append(leftMax.peek()+"\n");
		
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			//leftMax��  root�� num�� ��
			if(leftMax.peek() >= num) leftMax.offer(num);
			else rightMin.offer(num);
			
			//leftMax�� rightMax �¿� ���� �����ֱ�
			if(leftMax.size() == rightMin.size()+2) {
				rightMin.offer(leftMax.poll());
			}
			else if(leftMax.size()+2 == rightMin.size()) {
				leftMax.offer(rightMin.poll());
			}
			
			// leftMax�� ������� rightMin ����� Ʋ���ٸ� ū ���� Ȧ���� ���
			// ���ٸ� ¦���� �ش� �ϱ⋚���� leftMax�� peek�� ���ǿ� ���� ��� ���̴�.
			if(leftMax.size() >= rightMin.size()) sb.append(leftMax.peek());
			else sb.append(rightMin.peek());
			
			if(i != N-1) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
