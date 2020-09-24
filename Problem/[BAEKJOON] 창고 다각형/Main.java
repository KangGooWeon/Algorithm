import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Data> list = new ArrayList();
		
		int result = 0;
		int heightMax = 0;
		int position = 0;
		for(int k =0; k<N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int leng =  Integer.parseInt(st.nextToken());
			int height =  Integer.parseInt(st.nextToken());
			
			if ( heightMax<= height) {
				heightMax = height;
				position = leng;
			}
			
			list.add(new Data(leng,height));
		}
		
		Collections.sort(list);
		
		Stack<Data> leftStack = new Stack<>();
		int leftMax =0;
		
		for(int i=0; i<list.size(); i++) {
			Data data = list.get(i);
			int leng = data.leng;
			int height = data.height;
			if(leng != position) {
				if(leftMax <= height) {
					leftMax = height;
					leftStack.push(data);
				}
			}
			else {
				leftStack.push(data);
				break;
			}
		}
		
		Stack<Data> rightStack = new Stack<>();
		int rightMax =0;
		
		for(int i=list.size()-1; i>=0; i--) {
			Data data = list.get(i);
			int leng = data.leng;
			int height = data.height;
			if(leng != position) {
				if(rightMax <= height) {
					rightMax = height;
					rightStack.push(data);
				}
			}
			else {
				rightStack.push(data);
				break;
			}
		}
		
		Data leftNum = leftStack.pop();
		int size = leftStack.size();
		for(int i =0; i<size; i++) {
			Data temp = leftStack.pop();
			result += temp.height * Math.abs(leftNum.leng - temp.leng);
			leftNum = temp;
		}
		
		Data rightNum = rightStack.pop();
		size = rightStack.size();
		for(int i =0; i<size; i++) {
			Data temp = rightStack.pop();
			result += temp.height * Math.abs(rightNum.leng - temp.leng);
			rightNum = temp;
		}
		
		System.out.println(result+heightMax);
	}
	
	static class Data implements Comparable<Data>{
		int leng;
		int height;
		public Data(int leng, int height) {
			super();
			this.leng = leng;
			this.height = height;
		}
		@Override
		public int compareTo(Data o) {
			return this.leng - o.leng;
		}
	}
}
