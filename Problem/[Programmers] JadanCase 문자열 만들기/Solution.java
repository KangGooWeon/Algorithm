class Solution {
    public String solution(String s) {
        String answer = "";
        
        // 공백 검사
        boolean flag = true;
        
        for(int i =0; i<s.length(); i++){
            if(s.charAt(i) == ' ') {
            	flag = true;
            	answer += s.charAt(i);
            }
            else{
                if(flag) {	// 공백 다음 첫 단어
                	answer += s.substring(i, i+1).toUpperCase();
                	flag = false;
                }else {
                	answer += s.substring(i, i+1).toLowerCase();
                }
            }
        }
        
        return answer;
    }
}