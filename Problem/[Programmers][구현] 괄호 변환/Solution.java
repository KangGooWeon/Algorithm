import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        int bal = balanceBracket(p);
        answer = dfs(p.substring(0,bal), p.substring(bal));
        
        return answer;
    }
    
    public String dfs(String u, String v) {
        if(v.length() ==0 && u.length() ==0) return "";
    	String s = u;
    	int bal = balanceBracket(v);
    	
    	if(correctBracket(u)) {
    		s += dfs(v.substring(0,bal), v.substring(bal));
    	}
    	else {
    		s = "(";
    		s+= dfs(v.substring(0,bal), v.substring(bal));
    		s+= ")";
    		StringBuilder sb = new StringBuilder(u);
    		sb.deleteCharAt(0);
    		sb.deleteCharAt(sb.length()-1);
    		s += changeBracket(sb.toString());
    	}
    	
    	return s;
    }
    
    private String changeBracket(String substring) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<substring.length(); i++) {
			if(substring.charAt(i) == '(') sb.append(")");
			else sb.append("(");
		}
		return sb.toString();
	}
    
    public boolean correctBracket(String str) {
    	Stack<Character> stack = new Stack<>();
    	
    	for(int i=0; i<str.length(); i++) {
    		if(str.charAt(i) == '(') stack.push('(');
    		else {
    			if(stack.size() == 0) return false;
    			else stack.pop();
    		}
    	}
    	
    	if(stack.size() == 0 ) return true;
    	else return false;
    }
    
    public int balanceBracket(String str) {
    	int left =0;
    	int right =0;
    	int index =0;
    	
    	for(int i=0; i<str.length(); i++) {
    		index++;
    		if(str.charAt(i) == '(') left++;
    		else right++;
    		
    		if(left == right) return index;
    	}
    	
    	return index;
    }
}