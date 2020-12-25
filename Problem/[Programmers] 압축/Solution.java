import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList();
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        
        //초기화
        for(int i=1; i<=26; i++){
            hm.put(Character.toString((char)64+i),i);
        }
        
        int index =0;   
        int temp =0;    // 출력 색인번호 저장
        String str ="";
        
        while(true){
            if(msg.length()==index) {
                list.add(temp);
                break;
            }
            str += msg.charAt(index);
            
            if(hm.containsKey(str)){
                temp = hm.get(str);
                index++;
            }else{
                hm.put(str, hm.size()+1);                
                str ="";
                list.add(temp);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}