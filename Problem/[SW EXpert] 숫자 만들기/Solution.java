package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int[] number;
	private static int N, min, max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			N = Integer.parseInt(br.readLine());
			number = new int[N];
			int[] operator = new int[4];
			min = 10000000;
			max = -10000000;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			permutation(operator, 0, number[0]);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max - min);
			System.out.println(sb.toString());
		}
	}

	private static void permutation(int[] operator, int cnt, int numb) {
		if(cnt == N-1) {
			if (min > numb) min = numb;
			if (max < numb) max = numb;
			return;
		}
		
		for(int i = 0; i< 4; i++) {
			int num = operator[i];
			switch(i) {
			case 0:
				if(num > 0) {
					--operator[i];
					permutation(operator, cnt+1, numb+number[cnt+1]);
					++operator[i];
				}
				break;
			case 1:
				if(num > 0) {
					--operator[i];
					permutation(operator, cnt+1, numb-number[cnt+1]);
					++operator[i];
				}
				break;
			case 2:
				if(num > 0) {
					--operator[i];
					permutation(operator, cnt+1, numb*number[cnt+1]);
					++operator[i];
				}
				break;
			case 3:
				if(num > 0) {
					--operator[i];
					permutation(operator, cnt+1, numb/number[cnt+1]);
					++operator[i];
				}
				break;
			}
		}
	}

	private static int check(int i, int j, Character poll) {
		int num = 0;
		switch(poll) {
		case'+': num = i+j; break;
		case'-': num = i-j; break;
		case'*': num = i*j; break;
		case'/': num = i/j; break;
		}
		return num;
	}
}
