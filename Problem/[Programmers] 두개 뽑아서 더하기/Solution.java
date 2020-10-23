import java.util.*;
class Solution {
    public static int[] solution(int[] numbers) {
		HashSet<Integer> s = new HashSet<>();
		
		for(int i =0; i<numbers.length; i++) {
			for(int j=0; j<numbers.length; j++) {
				if(i ==j) continue;
				s.add(numbers[i]+numbers[j]);
			}
		}
		int[] answer = new int[s.size()];
		
		int index =0;
		for(int n : s) {
			answer[index++] = n;
		}
		
		Arrays.sort(answer);
		
		return answer;
	}
}