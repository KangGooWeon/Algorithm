import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int index =0;   // 차례
        int number =0;
        int size =0;
        String list = "";
        while(true){
            if(size ==0) {
                list = makeJinsu(n, number).toUpperCase();
                size = list.length();
                number++;
            }
            
            if(index%m == p-1) {
                answer += list.charAt(list.length() - size);
            }
            
            index++;
            size--;
            if(answer.length() == t) break;
            
        }
        return answer;
    }
    
	public String makeJinsu(int n, int number){
		List<Character> temp = new ArrayList<>();
		String t = "";
		if(number == 0) t ="0";
        while(number != 0){
            int a = number%n;
            if( a > 9){
                temp.add((char) (55 +a));
            }else{
            	temp.add((char)(a +'0'));
            }
            number /= n;
        }
        Collections.reverse(temp);
        
        for(int i =0; i<temp.size(); i++) {
        	t += temp.get(i);
        }
        
        return t;
    }
}