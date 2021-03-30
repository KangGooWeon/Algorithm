import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		int result = 0;
		int size = T.length();
		
		for(int i=0; i<size; i++) {
			char c = T.charAt(T.length()-1);
			
			T = T.substring(0,T.length()-1);
			
            // B인 경우 뒤집어 준다.
			if(c == 'B') {
				StringBuilder sb = new StringBuilder(T);
				T = sb.reverse().toString();
			}
			
            // S와 길이가 같다면 S와 T 비교
			if(S.length() == T.length()) {
				if(S.equals(T)) result =1;
				break;
			}
		}
        
		System.out.println(result);
	}
}