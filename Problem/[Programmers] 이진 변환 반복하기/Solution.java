class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int index =0;
        int removeCnt =0;
        
        while(true){
            index++;
            int length = s.length();
            
            s = s.replace("0", "");
            removeCnt += length - s.length();
            if (s.equals("1")) break;
            
            s = Integer.toBinaryString(s.length());
        }
        answer[0] = index;
        answer[1] = removeCnt;
        
        return answer;
    }
}