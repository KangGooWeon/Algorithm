import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
   public int solution(String[] lines) {
		int answer = 0;
		
		List<Time> list = new ArrayList<>();
		
		for(int i=0; i<lines.length; i++) {
			String[] data = lines[i].split(" ");
			
			data[1] = data[1].replace(".", "");
			String[] time = data[1].split(":");
			
			int hour = Integer.parseInt(time[0])*10000000;
			int min = Integer.parseInt(time[1])*100000;
			int sec = Integer.parseInt(time[2]);
			
			//마지막 시간 저장
			int end = hour + min + sec;

			data[2] = data[2].replace("s", "");
			int e = (int) (Double.parseDouble(data[2])*1000);
			
			sec -= e;
			if(sec <0) {
				sec += 60000;
				min -= 100000;
				if(min < 0) {
					min += 6000000;
					hour -= 10000000;
				}
			}
			
			int start = hour + min + sec;
			
			list.add(new Time(start, end));
		}
		
		for(int i=0; i<list.size(); i++) {
			Time t = list.get(i);
			int check = t.end + 1000 -1;
			
			int count = 1;
			for(int j=i+1; j<list.size(); j++) {
				Time temp = list.get(j);
				if(temp.start<check) {
					count++;  
				}
			}
			
			answer = Math.max(answer, count);
		}
		
		return answer;
	}
	
	static class Time {
		int start;
		int end;
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}