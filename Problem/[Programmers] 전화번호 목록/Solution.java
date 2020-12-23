import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        List<String> list = new ArrayList<>();
        
        Arrays.sort(phone_book, new phoneLengthCompare());
        
        for(int i=0; i<phone_book.length; i++){
            
            for(int j=0; j<list.size(); j++){
                String a = list.get(j);
                String b = phone_book[i].substring(0,a.length());
                if(a.equals(b)) return false;
            }
                
            list.add(phone_book[i]);
        }
        
        return answer;
    }
	
	 static class phoneLengthCompare implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return o1.length() > o2.length() ? 1 : o1.length() < o2.length() ? -1:0;
		}
	 }
}