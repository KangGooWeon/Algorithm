import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<Data> list = new ArrayList<>();
        
        //전체 반복
        for(int i=0; i<files.length; i++) {
        	
        	String copy = files[i].toUpperCase();
            int index =0;
            
            //HEAD 찾기
            String head ="";
            while(true){
                if(index == copy.length()) break;
            	if('A'<= copy.charAt(index) && copy.charAt(index) <='Z' || copy.charAt(index) == ' ' || copy.charAt(index) == '-') head+= copy.charAt(index++);
            	else break;
            }
            
            //Number 찾기
            String number ="";
            while(true){
                if(index == copy.length()) break;
            	if('0'<= copy.charAt(index) && copy.charAt(index) <='9') number+= copy.charAt(index++);
            	else break;
            }
            
            list.add(new Data(head, Integer.parseInt(number), files[i]));
        }
        
        Collections.sort(list);
        
        for(int i=0; i<files.length; i++) {
        	answer[i] = list.get(i).fileName;
        	System.out.println(answer[i]);
        }
        
        return answer;
    }
	
	static class Data implements Comparable<Data>{
		String head;
		int number;
		String fileName;
		public Data(String head, int number, String fileName) {
			super();
			this.head = head;
			this.number = number;
			this.fileName = fileName;
		}
		@Override
		public int compareTo(Data o) {
			if(this.head.equals(o.head)) return this.number - o.number;
			return this.head.compareTo(o.head);
		}
	}
}