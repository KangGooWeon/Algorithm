import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		String[] in = new String[T];
		String[] out = new String[T];
		boolean[] check = new boolean[T];
		int result =0;
		
		//입구
		for(int i=0; i<T; i++) {
			in[i] = br.readLine();
		}
		
		//출구
		for(int i=0; i<T; i++) {
			out[i] = br.readLine();
		}
		
		for(int i=0; i<T; i++) {
			for(int j=0; j<T; j++) {
				if(!in[i].equals(out[j])) {
					if(check[j] == false) {
						check[j] = true;
						result++;
					}
				}else {
					check[j] = true;
					break;
				}
			}
		}
		System.out.println(result);
	}
}