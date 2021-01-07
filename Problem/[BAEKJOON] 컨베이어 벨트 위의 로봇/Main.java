import java.io.*;
import java.util.*;

import jdk.internal.util.xml.impl.Input;

public class Main {
	static int N, K, zeroCnt;
	static List<Integer> list;
	static int[] conveNum;
	static boolean[] conveVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		conveNum = new int[N*2];
		conveVisited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N*2; i++) {
			conveNum[i] = Integer.parseInt(st.nextToken());
			if(conveNum[i]==0) zeroCnt++;
		}
		
		int index =0;
		while(true) {
			index ++;
			//벨트 칸 이동
			conveMove();
			robotMove();
			onRobotCheck();
			
			if(zeroCnt <= K) break;
		}
		
		System.out.println(index);
	}
	private static void onRobotCheck() {
		//올라가는 위치에 로봇 올리기
		if(conveNum[0] !=0 && conveVisited[0] == false) {
			conveNum[0]--;
			if(conveNum[0] == 0) zeroCnt++;
			conveVisited[0] = true;
			list.add(0);
		}		
	}
	private static void robotMove() {
		for(int i=0; i<list.size(); i++) {
			int index = list.get(i);
			
			// 로봇이 이동 가능한 경우
			if(conveNum[index+1] !=0 && conveVisited[index+1] == false) {
				conveNum[index+1]--;
				if(conveNum[index+1] == 0) zeroCnt++;
				conveVisited[index] = false;
				
				//내려오는 자리 오면 삭제
				if(index+1 == N-1) {
					list.remove(i--);
					conveVisited[N-1] = false;
				}
				else {
					conveVisited[index+1] = true;
					list.set(i, index+1);
				}
			}
		}
	}
	private static void conveMove() {
		int temp = conveNum[(N*2)-1];
		
		// 컨베이어 벨트 이동
		for(int i=(N*2)-1; i>0; i--) conveNum[i] = conveNum[i-1];
		// 컨베이어 위에 있는 로봇 이동
		for(int i=N-1; i>0; i--) conveVisited[i] = conveVisited[i-1];
		
		conveNum[0] = temp;
		conveVisited[0] = false;
		conveVisited[N-1] = false;
		
		// 실질적으로 로봇 위치를 저장한 list도 1씩 증가
		for(int i=0; i<list.size(); i++) {
			int index = list.get(i);
			if(index+1 == N-1) list.remove(i--);
			else list.set(i, index+1);
		}
	}
}
