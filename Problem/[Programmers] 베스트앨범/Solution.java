import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
   public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Pos>> map = new HashMap<>();
        Map<String, Integer> num = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        for(int i =0; i<genres.length; i++) {
        	if(map.containsKey(genres[i])) {
        		num.put(genres[i], num.get(genres[i]) + plays[i]);
        		List<Pos> a = map.get(genres[i]);
        		a.add(new Pos(plays[i], i));
        		map.put(genres[i], a);
        	}else {
        		num.put(genres[i], plays[i]);
        		map.put(genres[i], new ArrayList<>());
        		List<Pos> a = map.get(genres[i]);
        		a.add(new Pos(plays[i], i));
        		map.put(genres[i], a);
        	}
        }
        
        List<String> keySetList = new ArrayList<>(num.keySet());
        Collections.sort(keySetList, (o1, o2) -> (num.get(o2).compareTo(num.get(o1))));
        
        for(String key : keySetList) {
        	 List<Pos> pos = map.get(key);
        	 if (pos.size() == 1) {
        		 result.add(pos.get(0).index);
        	 }else {
        		 Collections.sort(pos);
        		 int a = pos.get(0).num;
        		 int b = pos.get(1).num;
        		 if ( a == b) {
        			 if (pos.get(0).index < pos.get(1).index) {
        				 result.add(pos.get(0).index);
        				 result.add(pos.get(1).index);
        			 }else {
        				 result.add(pos.get(1).index);
        				 result.add(pos.get(0).index);
        			 }
        		 }else {
        			 result.add(pos.get(0).index);
    				 result.add(pos.get(1).index);
        		 }
        	 }
		}
        
        int[] answer = new int[result.size()];
        for(int i = 0; i<result.size(); i++ ) {
        	answer[i] = result.get(i);
        }
        
        return answer;
    }
	
	static class Pos implements Comparable<Pos>{
		int num;
		int index;
		public Pos(int num, int index) {
			super();
			this.num = num;
			this.index = index;
		}
		@Override
		public int compareTo(Pos o) {
			return (this.num - o.num) * -1;
		}
	}
}