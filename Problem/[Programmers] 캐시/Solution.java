
import java.util.ArrayList;
import java.util.List;

class Solution {
   public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new ArrayList<>();
        
        if( cacheSize == 0 ) return 5*cities.length;
        
        for(int i =0; i<cities.length; i++) {
        	String temp = cities[i].toUpperCase();
        	boolean flag = false;
        	for(int j=0; j<list.size(); j++) {
        		if( list.get(j).equals(temp) ) {
        			answer++;
        			list.add(list.get(j));
        			list.remove(j);
        			flag = true;
        			break;
        		}
        	}
        	
        	if(!flag) {
        		if(list.size() < cacheSize) {
        			list.add(temp);
            		answer +=5;
        		}else {
        			list.remove(0);
            		list.add(temp);
            		answer +=5;
        		}
        	}
        }
        
        return answer;
    }
}