import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Pos> qu = new LinkedList<>();
        
        int cnt =0, cur_weight=0, complete=0;
        while(true){
            answer++;
            if(complete == truck_weights.length) break;
            
            if(cnt < truck_weights.length && cur_weight+truck_weights[cnt] <= weight){
                qu.offer(new Pos(truck_weights[cnt], 0));
                cur_weight += truck_weights[cnt];
                cnt++;
            }
            
            int size = qu.size();
            
            for(int i=0; i<size; i++){
                Pos pos = qu.poll();
                if(pos.time+1 == bridge_length) {
                    complete++;
                    cur_weight -= pos.num;
                }
                else qu.offer(new Pos(pos.num, pos.time+1));
            }
            
        }
        
        return answer;
    }
    
    static class Pos{
        int num;
        int time;
        public Pos (int num, int time){
            this.num = num;
            this.time = time;
        }
    }
}