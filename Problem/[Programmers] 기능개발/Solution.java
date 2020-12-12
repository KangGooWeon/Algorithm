import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int[] complete = new int[progresses.length];
        
        // 완료날짜 파악
        for(int i=0; i<progresses.length; i++){
            int temp = 100 - progresses[i];
            int index = 0;
            int day =0;
            while(true){
                index += speeds[i];
                day++;
                if(temp <= index) break;
            }
            complete[i] = day;
        }
        
        //완료 날짜 통일
        for(int i=0; i<complete.length-1; i++){
            if(complete[i] < complete[i+1]) continue;
            
            complete[i+1] = complete[i];
        }
       
        int day = 0;
        for(int i=0; i<complete.length; i++){
            day++;
            if(i == complete.length-1){
                list.add(day);
                break;
            }
            if(complete[i] != complete[i+1]) {
                list.add(day);
                day=0;
            } 
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
