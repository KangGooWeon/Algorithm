
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
	
	static int N,K,val,index;
	static char[] box;
	static Integer[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 1; t<=T; t++) {
			N = sc.nextInt();
			box = new char[N];
			result = new Integer[N];
			
			K = sc.nextInt();
			val = N/4;
			index=0;
			
			//저장
			String str = sc.next();
			for (int i = 0; i < N; i++) {
				box[i] = str.charAt(i);
			}
			
			for (int i = 0; i < val; i++) {
				save();
				turn();
			}
			
			result = Arrays.copyOf(result, index);
			Arrays.sort(result, Collections.reverseOrder());
			System.out.println("#"+t+" "+result[K-1]);
		}
	}
	
	

	private static void save() {
		for(int j =0; j<4; j++) {
			String a ="";
			for(int k = val*j; k< val*(j+1); k++) {
				a+= box[k];
			}
			
			int ten = Integer.parseInt(a,16);
			if(check(ten)) {
				result[index++] = ten;
			}
		}		
	}

	private static boolean check(int ten) {
		for (int i =0; i< index; i++) {
			if (result[i] == ten) return false; 
		}
		return true;
	}

	private static void turn() {
		char temp = box[0];
		for (int i = 1; i < N; i++) {
			char a = box[i];
			box[i] = temp;
			temp = a;
			if (i == N-1) {
				box[0] = temp;
			}
		}
	}
}