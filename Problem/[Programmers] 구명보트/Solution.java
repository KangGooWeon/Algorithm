import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<>();
        Arrays.sort(people);
        for(int i=0; i<people.length; i++){
            list.add(people[i]);
        }
        
        int index = people.length-1;
        while(true){
            if(list.size() == 0) break;
            
            int weight = list.get(index);
            list.remove(index--);
            answer++;
            for(int i= 0; i<list.size(); i++){
                if(list.get(i)+weight <= limit){
                    index--;
                    weight += list.get(i);
                    list.remove(i--);
                }
                else {
                    break;
                }
            }
        }
        return answer;
    }
}