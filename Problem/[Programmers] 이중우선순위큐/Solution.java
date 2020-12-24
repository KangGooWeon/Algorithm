import java.util.*;

class Solution {
    PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
    PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
    int max, min;
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        for(int i =0; i<operations.length; i++){
            String[] temp = operations[i].split(" ");
            //삽입 일때 둘다 삽입
            if(temp[0].equals("I")){
                int t = Integer.parseInt(temp[1]);
                maxPQ.add(t);
                minPQ.add(t);
            }
            //삭제
            else{
                if(maxPQ.size() == 0 || minPQ.size() == 0) continue;
                    
                if(temp[1].equals("1")){
                    max = maxPQ.poll();
                    minPQ.remove(max);
                }
                else{
                    min = minPQ.poll();
                    maxPQ.remove(min);
                }
            }
            
        }
        
        if(maxPQ.size() >1) {
            answer[0] = maxPQ.poll();
            answer[1] = minPQ.poll();
        }
        
        return answer;
    }
}