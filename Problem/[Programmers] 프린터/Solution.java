import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        //우선 순위가 가장 높은 값을 꺼내기 위해
        PriorityQueue<Data> pq = new PriorityQueue<Data>();
        //인쇄 대기목록 저장
        Queue<Data> qu = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++){
            qu.offer(new Data(i, priorities[i]));
            pq.offer(new Data(i, priorities[i]));
        }
        
        loop:
        while(true){
            //우선순위 높은 값 저장
            Data target = pq.poll();
            
            //우선순위와 같은 값 찾기
            while(true){
                Data check = qu.poll();
                if(check.priority == target.priority){
                    answer++;
                    if(check.index == location) break loop;
                    break;
                }else{
                    qu.offer(new Data(check.index, check.priority));
                }
            }
        }

        return answer;
    }
    
    static class Data implements Comparable<Data>{
        int index;
        int priority;
        
        public Data(int index, int priority){
            this.index = index;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Data o) {
			return (this.priority - o.priority) *-1;
		}
    }
}