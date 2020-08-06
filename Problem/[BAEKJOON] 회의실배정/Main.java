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
	static class Meeting {
		int from, to;
		
		public Meeting(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	
	static class MeetingComparator implements Comparator<Meeting> {

		@Override
		public int compare(Meeting o1, Meeting o2) {
			if(o1.to == o2.to) return (o1.from - o2.from);    // 종료시간이 같다면 시작시간
					
			return o1.to - o2.to;
		}

	}
	
	static int N;
	static Meeting[] meetingList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		meetingList = new Meeting[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			meetingList[i] = new Meeting(from,to);
		}
		
		Arrays.sort(meetingList, new MeetingComparator());
		
		int to = meetingList[0].to;		// 첫 번째 회의는 무조건 진행 가능
		int count =1;					// 이미 첫번째 회의 들어감
		
		for(int i=1; i<N; i++) {
			if(to <= meetingList[i].from) {
				to = meetingList[i].to;
				count ++;
			}
		}
		
		System.out.println(count);
	}
}

