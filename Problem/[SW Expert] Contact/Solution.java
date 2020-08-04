
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	private static int N,S;
	private static Queue<Integer> qu = new LinkedList<Integer>();
	private static int[] visited;
	private static int[][]map;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		for(int t =1; t<=10; t++) {
			N = sc.nextInt();
			S = sc.nextInt();
			visited = new int[101];
			map = new int[101][101];
			
			for(int i=0; i<N/2; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[x][y] = 1;
			}
			
			BFS();
			
			int max =0;
			int num =0;
			
			for(int i =1; i<=100; i++) {
				if (visited[i] > max) {
					max = visited[i];
					num = i;
				}
				else if(visited[i] == max) {
					if( num < i ) {
						num = i;
					}
				}
			}
			
			System.out.println(num);
		}
	}
	
	private static void BFS() {
		qu.offer(S);
		visited[S] = 1;
		
		int count =2;
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int j =0; j<size; j++) {
			int curr = qu.poll();
				for(int i=1; i<=100; i++) {
					if (map[curr][i] == 1 
							&& visited[i] == 0) {
						qu.offer(i);
						visited[i] = count;
					}
				}
			}
			count ++;	
		}
	}
}
