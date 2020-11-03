import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
 public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        List<Integer> time = new ArrayList<>();
        for(int i = 0; i<timetable.length; i++) {
        	String[] a = timetable[i].split(":");
        	time.add(Integer.parseInt(a[0]+a[1]));
        }
        
        Collections.sort(time);
        
        int start = 900;
        int result = 900;
        
        for(int i =0; i<n; i++) {
        	if(i >0) {
        		if((start%100) +t >=60) {
        			int a = t-60;
        			start += 100 +a;
        		}
        		else {
        			start = start +t;	// 시간 조정
        		}
        	}
        	
        	result = start;
        	int ti = start;
        	int index =0;
        	for(int j =0; j<m; j++) {
        		if(time.get(index) <= ti) {
        			if(j == m-1) {
        				if((time.get(index)-1)%100 > 59 && (time.get(index)-1)%100  < 100) {
        					result = time.get(index)-41;
        				}
        				else{
        					result = time.get(index)-1;
        				}
        			}
        			time.remove(0);
        			index--;
        		}
        		index++;
        		if(time.size() ==0) break;
        		if(index == time.size()) break;
        	}
        }
        
        answer =  Integer.toString(result);
        for(int i =answer.length(); i<4; i++) {
        	answer = "0"+answer;
        }
        StringBuffer origin = new StringBuffer(answer);
        origin.insert(2,":");
        
        return origin.toString();
    }
}