import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(st.nextToken());
			arr[i] = a;
		}
		
		Arrays.sort(arr);
		
		for(int i=1; i<arr.length; i++) {
			arr[i] += arr[i-1];
		}
		
		System.out.println(Arrays.stream(arr).sum());
	}

}