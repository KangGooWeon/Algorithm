import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int X,K;
	private static Queue<Integer> qu = new LinkedList<Integer>();
	private static int[] visited = new int[100001];
	private static int[] move = {-1,1,2};

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(BFS(X, K)-1);
	}
	
	private static int BFS(int start, int end) {
		qu.offer(start);
		visited[start] = 1;
		
		while(!qu.isEmpty()) {
			int x = qu.poll();
			if(x == end) break;
			
			for(int i =0; i<3; i++) {
				int a =0;
				if( i ==2) a =x*move[i];
				else a = x + move[i];
				
				if(a<0 || a>100000 || visited[a] > 0) continue;
				
				qu.offer(a);
				visited[a] = visited[x]+1;
			}
		}
		return visited[end];
	}
}
