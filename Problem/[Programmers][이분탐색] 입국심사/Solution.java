import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long min = Long.MAX_VALUE;
        
        Arrays.sort(times);
        long left = 0;
        long right = 0;
        long mid;
        
        for (int time : times) {
			if (time > right) {
				right = time;
			}
		}
        
        right *=n;
        
        while(left<=right) {
            mid = (left + right) / 2;
            
        	long num = checkTime(mid, times);
        	
        	if(num < n) left = mid+1;
        	else {
                min = Math.min(min, mid);
                right = mid-1;
            }
        	
        }
        
        return min;
    }

	private long checkTime(long mid, int[] times) {
		long sum =0;
		
		for(int i=0; i<times.length; i++) {
			sum += mid/times[i];
		}
		
		return sum;
	}
}