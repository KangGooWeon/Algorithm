import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Solution {
	public static int solution(String str1, String str2) {
		int answer = 0;
        
        str1 = change(str1);
        str2 = change(str2);
        
        List<String> s1 = zip(str1);
        List<String> s2 = zip(str2);
        
        if (s1.size() == 0 && s2.size() == 0) return 65536;
        
        if (s1.size() < s2.size()) answer = (int) (check(s2,s1) * 65536);
        else answer = (int) (check(s1,s2) * 65536);
        
		return answer;
	}

	private static double check(List<String> s1, List<String> s2) {
    	int sumCnt = s1.size();
    	int gyoCnt = 0;
    	
    	List<String> list = new ArrayList<>();
    	
    	for(int j =0; j<s1.size(); j++) {
    		list.add(s1.get(j));
		}
    	
    	for(int i=0; i<s2.size(); i++) {
    		String a = s2.get(i);
    		boolean flag = false;
    		for(int j =0; j<list.size(); j++) {
    			if(a.equals(list.get(j))) {
    				list.remove(j);
    				flag = true;
    				break;
    			}
    		}
    		if(!flag) sumCnt++;
    	}
    	
    	for(int i=0; i<s2.size(); i++) {
    		String a = s2.get(i);
    		for(int j =0; j<s1.size(); j++) {
    			if(a.equals(s1.get(j))) {
    				s1.remove(j);
    				gyoCnt++;
    				break;
    			}
    		}
    	}
    	
		return (double)gyoCnt/  (double) sumCnt;
	}

	private static List<String> zip(String s) {
    	List<String> list = new ArrayList<>();
    	char[] c = s.toCharArray();
    	
    	for(int i=0; i<s.length()-1; i++){
            if('A'<= c[i] && c[i] <= 'Z'){
            	if('A'<= c[i+1] && c[i+1] <= 'Z'){
            		list.add(Character.toString(c[i]) + Character.toString(c[i+1]));
            	}
            }
        }
    	
		return list;
	}

	public static String change(String s){
        String temp = "";
        char[] c = s.toCharArray();
        
        for(int i=0; i<s.length(); i++){
            if('a'<= c[i] && c[i] <= 'z'){
                temp += (char)(c[i] - Math.abs('A'-'a'));
                continue;
            }
            temp += c[i];
        }
        
        return temp;
    }
}