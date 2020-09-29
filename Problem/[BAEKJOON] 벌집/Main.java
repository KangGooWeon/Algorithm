import java.util.Scanner;

public class Main {
	public static int eee(int N) {
		if (N == 1) return 1;
		if (2<= N && N<=7) return 2;
		int result = 2;
		int cnt = 1;
		int start = 2;
		int end = 7;
		
		while(true) {
			start += 6*cnt;
			end += 6*(cnt+1);
			
			if(start<= N && N<=end) {
				result += cnt;
				break;
			}
			cnt++;
		}
		
		
		return result; // 리턴값을 수정하세요
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
//		int N = 13;
		System.out.println(eee(N)); // 3
	}

}
