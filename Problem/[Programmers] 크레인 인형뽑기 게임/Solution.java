import java.util.Stack;
class Solution {
    public int solution(int[][] board, int[] moves) {
       int answer = 0;
	    Stack<Integer> s = new Stack<Integer>();
	    
	    for(int i =0; i<moves.length; i++) {
	    	for(int j=0; j<board[moves[i]-1].length; j++) {
	    		if(board[j][moves[i]-1] > 0) {
	    			int temp = board[j][moves[i]-1];
	    			board[j][moves[i]-1] =0;
	    			if (!s.isEmpty()) {
	    				if(s.peek() == temp) {
	    					s.pop();
	    					answer+=2;
	    					break;
	    				}
	    				else {
	    					s.push(temp);
	    					break;
	    				}
	    			}
	    			else {
	    				s.push(temp);
	    				break;
	    			}
	    		}
	    	}
	    }
	    
	    return answer;
    }
}