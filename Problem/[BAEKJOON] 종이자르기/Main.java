import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w =  Integer.parseInt(st.nextToken());
		int h =  Integer.parseInt(st.nextToken());
		
		List<Integer> width = new ArrayList<>();
		List<Integer> height = new ArrayList<>();
		width.add(h);
		height.add(w);
		
		int N =  Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int check =  Integer.parseInt(st.nextToken());
			int num =  Integer.parseInt(st.nextToken());
			
			if (check == 0) {
				width.add(num);
			}
			else {
				height.add(num);
			}
		}
		
		Collections.sort(width);
		Collections.sort(height);
		
		List<Integer> widthLeng = new ArrayList<>();
		List<Integer> heightLeng = new ArrayList<>();
		
		for(int i =0; i<width.size(); i++) {
			if (i == 0) {
				widthLeng.add(width.get(i));
			}
			else {
				widthLeng.add(width.get(i) - width.get(i-1));
			}
		}
		
		for(int i =0; i<height.size(); i++) {
			if (i == 0) {
				heightLeng.add(height.get(i));
			}
			else {
				heightLeng.add(height.get(i) - height.get(i-1));
			}
		}
		
		Collections.sort(widthLeng);
		Collections.sort(heightLeng);
		
		
		System.out.println(widthLeng.get(width.size()-1) * heightLeng.get(height.size()-1));
	}
}
