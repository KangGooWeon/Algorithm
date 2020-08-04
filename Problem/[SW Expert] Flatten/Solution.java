import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

public class Solution {
	private static int [] box = new int [100]; 
	
	public static void main(String arg[]){
		Scanner sc = new Scanner(System.in);
		
		for(int test_case =1; test_case<= 10; test_case++) {
			int dump = sc.nextInt();
			for(int i = 0; i< 100; i++) {
				box[i] = sc.nextInt();
			}
			
			for(int i = 0; i< dump; i++) {
				int box_high = Arrays.stream(box).max().getAsInt();
				int box_low = Arrays.stream(box).min().getAsInt();
				int high = find(box_high);
				int low = find(box_low);
				
				box[high]--;
				box[low]++;
			}
			
			System.out.println("#"+test_case+" "+(Arrays.stream(box).max().getAsInt()-Arrays.stream(box).min().getAsInt()));
		}
	}

	private static int find(int value) {
		int num = 0;
		
		for(int i = 0; i< 100; i++) {
			if(box[i] == value) {
				num = i;
				break;
			}
		}
		return num;
	}
}