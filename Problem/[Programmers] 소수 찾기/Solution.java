import java.util.HashSet;
import java.util.Set;

class Solution {
    static int answer;
    static String number;
    static Set<Integer> set;
    
    public int solution(String numbers) {
        number = numbers;
        set = new HashSet<>();
        
        for(int i =1; i<=numbers.length(); i++) {
        	permutaion(0,numbers,new char[number.length()], new boolean[number.length()], i);
        }
        
        return answer;
    }
    
    public void permutaion(int cnt, String check, char[] result, boolean[] isChecked, int end){
        if(cnt == end){
        	String temp ="";
        	int index =0;
        	for(int i=0; i<check.length(); i++) {
                if(isChecked[i]==true)temp += result[index++];
			}
        	
        	int num = Integer.parseInt(temp);
        	if(set.contains(num)) return;
        	
            if(findPrime(num)) {
            	set.add(num);
            	answer++;
            }
            return;
        }
        
        for(int i=0; i<check.length(); i++){
            if(isChecked[i]==true) continue;
            
            result[cnt] = check.charAt(i);
            isChecked[i] = true;
            permutaion(cnt+1, check, result, isChecked, end);
            isChecked[i] = false;
        }
    }
    
    public boolean findPrime(int num){
    			if(num < 2) return false;
    			
    			if(num == 2) return true;
    	        
    			for(int i = 2; i < num; i++) {
    				if(num % i == 0) {
    					return false;
    				}
    			}
    			return true;
    }
}