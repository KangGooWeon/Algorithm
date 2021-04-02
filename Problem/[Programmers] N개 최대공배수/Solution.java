import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int min = arr[0];
        
        for(int i=1; i<arr.length; i++) {
        	min = (min * arr[i]) / gcd(Math.max(min, arr[i]), Math.min(min, arr[i]));
        }
        return min;
    }
    
	static int gcd(int max, int min) {	
		while(min!=0) {
			int remainder = max%min;
			max =min;
			min = remainder;
		}
		return max;
	}
}