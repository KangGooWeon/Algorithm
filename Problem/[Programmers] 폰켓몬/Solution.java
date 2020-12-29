import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> hs = new HashSet<Integer>();
        
        for(int i=0; i<nums.length; i++){
            hs.add(nums[i]);
        }
        
        int answer = hs.size();
        if(nums.length/2 < answer) answer = nums.length/2;
        return answer;
    }
}