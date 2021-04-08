import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		
		for(int tc = 0; tc<T; tc++) {
			int n = Integer.parseInt(br.readLine());
			HashSet<String> set = new HashSet<>();
			boolean isPhone = false;
			List<String> list = new ArrayList<>();
			
			//저장
			for(int i=0; i<n; i++) {
				String a = br.readLine();
				set.add(a);
				list.add(a);
			}
			
			for(int i=0; i<n; i++) {
				String str = list.get(i);
				StringBuilder sb = new StringBuilder();
				boolean flag = false;
				
                //일관성 검사
				for(int j=0; j<str.length()-1; j++) {
					sb.append(str.charAt(j));
					if(set.contains(sb.toString())) {
						flag = true;
						break;
					}
				}
				
				if(flag) {
					result.append("NO\n");
					isPhone = true;
					break;
				}
			}
            
           	//위에서 No가 아니기 때문에 Yes
			if(!isPhone) {
				result.append("YES\n");
			}
		}
		System.out.println(result.toString());
	}
}