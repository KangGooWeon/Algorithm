import java.util.*;

class Solution {
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int check = w*2+1;
        
        for(int i =1; i<stations.length; i++){
        	 int left = stations[i] - stations[i-1] - (w*2) -1;
             if (left <= 0) continue;
             
             if(left%check != 0) {
            	 answer += left/check +1;
             }else {
            	 answer += left/check;
             }
        }
        
        int num = stations[0];
        int left = num - w-1;
        if(left > 0) {
        	if(left%check != 0) {
           	 answer += left/check +1;
            }else {
           	 answer += left/check;
            }
        }
        
        int num2 = stations[stations.length-1];
        int right = n - num2 -w;
        if(right > 0) {
        	if(right%check != 0) {
           	 answer += right/check +1;
            }else {
           	 answer += right/check;
            }
        }
        
        return answer;
    }
}