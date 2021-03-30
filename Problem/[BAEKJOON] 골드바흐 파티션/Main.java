import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());

			boolean[] isPrime = new boolean[num + 1];

			// 에라토스테네스의 체
			// 처음은 모두 소수라는 가정
			for (int j = 2; j <= num; j++) {
				isPrime[j] = true;
			}

			for (int j = 2; j * j <= num; j++) {
				if (!isPrime[j])
					continue;
				for (int k = j * j; k <= num; k += j) {
					isPrime[k] = false;
				}
			}
			
			List<Integer> list = new ArrayList<>();

			//소수만 담기
			for (int j = 2; j <= num; j++) {
				if (isPrime[j]) list.add(j);
			}
			
			int left =0;
			int right = list.size()-1;
			int result = 0;
			
			while(left<=right) {
				int a = list.get(left);
				int b = list.get(right);
				
				if(a+b > num)right--;
				else if(a+b < num) left++;
				else {
					result++;
					left++;
					right--;
				}
			}
			
			System.out.println(result);
		}
	}
}