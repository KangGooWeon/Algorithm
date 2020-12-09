class Solution {
    static int[] answer;
	static int[][] map;
	public int[] solution(int[][] arr) {
        answer = new int[2];
        map = arr;
        
        divide(0,0,arr.length);
        return answer;
    }

	private void divide(int x, int y, int n) {
		
		if(check(x,y,n)) return;
		
		int size = n / 2;
		
		divide(x, y, size);
		divide(x, y + size, size);
		divide(x + size, y, size);
		divide(x + size, y + size, size);
	}

	private boolean check(int x, int y, int n) {
		int a = map[x][y];
		
		for(int i = x; i<x+n; i++) {
			for(int j = y; j<y+n; j++) {
				if(a != map[i][j]) return false;
			}
		}
		
		answer[a]++;
		return true;
	}
}