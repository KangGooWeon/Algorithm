package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class ingredient {
		int tart, bitter;
		
		public ingredient(int tart, int bitter) {
			this.tart = tart;
			this.bitter = bitter;
		}
	}
	
	static int N;
	static long result = 10000000;
	static ingredient[] ingredientList;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		ingredientList = new ingredient[N];
		isSelected = new boolean [N];
		
		for(int i=0; i<N; i++) {				// 저장
			StringTokenizer st = new StringTokenizer(in.readLine());
			int tart = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			
			ingredientList[i] = new ingredient(tart,bitter);
		}
		generateSubset(0);
		System.out.println(result);
	}

	private static void generateSubset(int cnt) {
		if(cnt == N) {
			int check = 0;
			for (int i =0; i< N; i++) {
				if(!isSelected[i])check++;
			}
			
			if(check == 4) return;
			
			int tart = 1;
			int bitter = 0;
			for (int i =0; i< N; i++) {
				if(isSelected[i]) {
					tart *= ingredientList[i].tart;
					bitter += ingredientList[i].bitter;
				}
			}
			
			long differ = Math.abs(tart-bitter);
			if(differ < result) {
				result = differ;
			}
			return;
		}
		
		// 부분 집합 구성에 포함
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		// 부분 집합 구성에 비포함
		isSelected[cnt] = false;
		generateSubset(cnt+1);		
	}
}

