import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        List<Pos> list = new ArrayList<>();
		PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for(int i=0; i<jobs.length; i++){
            list.add(new Pos(jobs[i][0], jobs[i][1]));
        }
        
        int index = 0;
        int cnt =0;
        
        while(true){
            if(cnt == jobs.length) break;           
            
            int min = Integer.MAX_VALUE;
            for(int i=0; i<list.size(); i++){
                Pos pos = list.get(i);
                if(pos.start < min) min = pos.start;
                
                if(pos.start <= index) {
                    pq.offer(new Pos(pos.start, pos.time));
                    list.remove(i--);
                }
            }
            
			if (pq.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Pos pos = list.get(i);
					if (pos.start <= min) {
						pq.offer(new Pos(pos.start, pos.time));
						list.remove(i--);
					}
				}
			}
            
			Pos pos = pq.poll();
			if (index - pos.start > 0) {
				answer += pos.time - pos.start + index;
				index += pos.time;
			}
			else {
				answer += pos.time;
				index += pos.time + pos.start -index;;
			}
			
			cnt++;
        }
        
        return answer/cnt;
    }
    
    
    static class Pos implements Comparable<Pos>{
		int start;
		int time;
		public Pos(int start, int time) {
			super();
			this.start = start;
			this.time = time;
		}
		@Override
		public int compareTo(Pos arg0) {
			return this.time - arg0.time;
		}
	}
}