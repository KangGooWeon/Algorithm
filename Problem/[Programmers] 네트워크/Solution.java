class Solution {
  int[] parents;
	
	public int solution(int n, int[][] computers) {
		make(n);
        
		for(int i = 0; i<n; i++) {
			for(int j =0; j<n; j++) {
				if(i==j) continue;
				if(computers[i][j] == 1) union(i,j);
			}
		}
		
		int[] check = new int[n];
		
		for(int i =0; i<n; i++) {
			check[find(i)] ++;
		}
		
		int answer= 0;
		for(int i =0; i<n; i++) {
			if(check[i] > 0) answer++; 
		}
        
        return answer;
    }
	
	private int find(int a) {
		if (parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	private void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if ( aRoot == bRoot) return;
		
		parents[bRoot] = aRoot;
		return;
	}

	private void make(int n) {
		parents = new int[n];
		
		for(int i =0; i<n; i++) {
			parents[i] = i;
		}
	}
}