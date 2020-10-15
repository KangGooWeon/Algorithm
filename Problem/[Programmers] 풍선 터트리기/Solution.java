class Solution {
   public static int solution(int[] a) {
        int answer = 0;
        
        int[] leftArr = new int[a.length];
        int leftMin = a[0];
        leftArr[0] = a[0];
        int[] rigthArr = new int[a.length];
        int rightMin = a[a.length-1];
        rigthArr[a.length-1] = a[a.length-1];
        
        for(int i = 0; i<a.length-1; i++) {
        	if(a[i+1] < leftMin) {
        		leftMin = a[i+1];
        	}
        	leftArr[i+1] = leftMin;
        }
        
        for(int i = a.length-1; i>0; i--) {
        	if(a[i-1] < rightMin) {
        		rightMin = a[i-1];
        	}
        	rigthArr[i-1] = rightMin;
        }
        
        for(int i =1; i<a.length-1; i++) {
        	int select = a[i];
        	boolean chance = false;
        	int r = rigthArr[i+1];
        	int l = leftArr[i-1];
        	
        	
        	if(l < select) {
        		chance = true;
        	}
        	
        	if(r < select && !chance) {
        		answer++;
        	}
        	else if(r > select) {
        		answer++;
        	}
        }
        
        return answer+2;
    }
}