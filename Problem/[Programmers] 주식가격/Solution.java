import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Pos> qu = new LinkedList<>();
        qu.offer(new Pos(prices[0], 0, 1));
        
        for(int i=1; i<prices.length-1; i++){
            int size = qu.size();
            
            for(int j=0; j<size; j++){
                Pos pos = qu.poll();
                
                if(pos.num <= prices[i])  qu.offer(new Pos(pos.num , pos.index, pos.time+1));
                else answer[pos.index] = pos.time;
            }
            
            qu.offer(new Pos(prices[i], i,1));
        }
        
        int size = qu.size();
        for(int j=0; j<size; j++){
            Pos pos = qu.poll();
            answer[pos.index] = pos.time;
        }
        
        return answer;
    }
    
    static class Pos{
        int num;
        int index;
        int time;
        public Pos (int num, int index, int time){
            this.num = num;
            this.index = index;
            this.time = time;
        }
    }
}