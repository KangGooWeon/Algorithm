import java.util.*;
import java.io.*;

public class Main {
	static int[][] gear = new int[4][8];
	static int gearAmount = 4; // 톱니바퀴 갯수
	static int gearNum = 8; // 톱니 갯수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			String a = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = a.charAt(j) - '0';
			}
		}

		int T = Integer.parseInt(br.readLine());
		int pow = 1;
		int result =0;

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int dis = Integer.parseInt(st.nextToken());
			
			int left = start;
			int right = start;
			boolean leftCheck = false;
			boolean rightCheck = false;
			int[] rotate = new int[gearAmount];
			rotate[start] = dis;
			
			while(true) {
				left--;
				right++;
				
				dis *=-1;
				
				if(left<0 && right > gearAmount-1) break;
				
				if(left >= 0 && !leftCheck) {
					if(gear[left][2] != gear[left+1][6]) rotate[left] = dis;
					else leftCheck = true;
				}
				
				if(right<gearAmount && !rightCheck) {
					if(gear[right][6] != gear[right-1][2]) rotate[right] = dis;
					else rightCheck = true;
				}
			}
			
			for(int j=0; j<gearAmount; j++) {
				if(rotate[j] == 0) continue;
				rotateGear(j, rotate[j]);
			}
		}
		
		for(int j=0; j<gearAmount; j++) {
			result += gear[j][0] * pow;
			pow *=2;
		}
		
		System.out.println(result);
	}

	private static void rotateGear(int curr, int dis) {
		int temp = 0;
		
		if (dis == 1) {
			temp = gear[curr][gearNum-1];
			for (int i = gearNum-1; i > 0; i--) {
				gear[curr][i] = gear[curr][i-1];
			}
			gear[curr][0] = temp;
		}else {
			temp = gear[curr][0];
			for (int i = 0; i < gearNum-1; i++) {
				gear[curr][i] = gear[curr][i+1];
			}
			gear[curr][gearNum-1] = temp;
		}
	}
}