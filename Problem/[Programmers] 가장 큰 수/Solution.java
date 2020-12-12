import java.util.PriorityQueue;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        PriorityQueue<Data> pq = new PriorityQueue<Data>();
        for(int i =0; i<numbers.length; i++) {
        	pq.add(new Data(String.valueOf(numbers[i])));
        }
        
        int size = pq.size();
        for(int i =0; i<size; i++) {
        	answer += pq.poll().number;
            if(answer.equals("0")) return "0";
        }
        
        return answer;
    }
	
	static class Data implements Comparable<Data>{
		String number;

		public Data(String number) {
			this.number = number;
		}

		@Override
		public int compareTo(Data o) {
			return ((o.number+this.number).compareTo(this.number+o.number));
		}
	}
}