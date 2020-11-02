import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {
   int[] parents;

	public int solution(int n, int[][] costs) {
		make(n);
		List<Pos> list = new ArrayList<>();
		
		for(int i = 0; i<costs.length; i++) {
			list.add(new Pos(costs[i][0], costs[i][1],costs[i][2]));
		}
		
		Collections.sort(list);
		
        int index = 0;
		int answer = 0;
		for(int i =0; i<list.size(); i++){
			Pos pos = list.get(i);
			if(union(pos.first, pos.second)) {
				index++;
				answer += pos.cost;
			}
            if (index == n-1) break;
		}
		
		return answer;
	}

	private int find(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = find(parents[a]);
	}

	private boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	private void make(int n) {
		parents = new int[n];

		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int first;
		int second;
		int cost;
		public Pos(int first, int second, int cost) {
			super();
			this.first = first;
			this.second = second;
			this.cost = cost;
		}
		@Override
		public int compareTo(Pos o) {
			return this.cost - o.cost;
		}
		
	}
}