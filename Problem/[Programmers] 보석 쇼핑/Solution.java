import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        Set<String> gemCheck = new HashSet<>();
        
        //보석 종류 확인
        for(int i=0; i<gems.length; i++){
            gemCheck.add(gems[i]);
        }
        
        //보석 종류 갯수
        int gemNum = gemCheck.size();
        
        gemCheck.clear();
        HashMap<String, Integer> gemDisplay = new HashMap<>();
        int gemLength = Integer.MAX_VALUE;
        
        for(int i=0; i<gems.length; i++){
            
            gemDisplay.put(gems[i], i+1);
            
            // 모든 종류가 확인 될 때
            if(gemDisplay.size() == gemNum){
                int min = Integer.MAX_VALUE;
                int max = 0;
                String minKey = "";
                
                // 진열된 보석 전시 길이
                for(String key : gemDisplay.keySet()) {
                    if (gemDisplay.get(key) < min){
                        min = gemDisplay.get(key);
                        minKey = key;
                    }
                    max = Math.max(max, gemDisplay.get(key));
                }
                gemDisplay.remove(minKey);
                
                if(max-min+1 < gemLength){
                    gemLength = max-min+1;
                    answer[0] = min;
                    answer[1] = max;
                }
            }
        }
        
        return answer;
    }
}