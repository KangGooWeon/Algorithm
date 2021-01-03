import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> wordCheck = new HashSet<>();
        wordCheck.add(words[0]);
        
        for(int i=1; i<words.length; i++){
            wordCheck.add(words[i]);
            if( words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0) ||   // 단어가 이어지는지
                wordCheck.size() != i+1 ||                                        // 단어의 중복
                words[i].length() == 1){                                            // 한글자인 단어
                answer[0] = (i%n) +1;
                answer[1] = (i/n) +1;
                break;
            }
        }

        return answer;
    }
}