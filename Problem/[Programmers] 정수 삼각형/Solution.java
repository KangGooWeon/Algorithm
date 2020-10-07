class Solution {
   private int[][] nodesSum;

	public int solution(int[][] triangle) {
		nodesSum = new int[triangle.length][triangle.length];
		return dfs(0, 0, triangle);
	}

	public int dfs(int left, int right, int[][] triangle) {
		if (left == triangle.length)return 0;
		if (nodesSum[left][right] > 0) return nodesSum[left][right];
		return nodesSum[left][right] = triangle[left][right] + Math.max(dfs(left + 1, right, triangle), dfs(left + 1, right + 1, triangle));

	}
}