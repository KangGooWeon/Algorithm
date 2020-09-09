import java.util.Arrays;
class Solution {
    public int[] solution(String s) {
	        int[] answer = new int[100000];
	        int[] count = new int[100000];
	        int max = 0;

	        String sum = "";
	        for(int i =0; i<s.length(); i++) {
	        	char a = s.charAt(i);
	        	if(a=='{' || a=='}' || a==',' ) {
	        		if (sum.equals("")) continue;
	        		
	        		int num = Integer.parseInt(sum);
	        		count[num]++;
	        		if (max < num) max =num;
	        		sum = "";
	        	}else {
	        		sum += a;
	        	}
	        }
	        
	        int index =0;
	        for(int i =1; i<=max; i++) {
	        	if(count[i] > 0) {
	        		answer[count[i]-1] = i;
	        		index++;
	        	}
	        }
	        answer = Arrays.copyOf(answer, index);
	        
	        for(int i =0; i<answer.length/2; i++) {
	        	int temp;
				temp = answer[i]; 
				answer[i] = answer[answer.length -1 -i];
				answer[answer.length -1 -i] = temp;
	        }

	        
	        return answer;
	    }
}