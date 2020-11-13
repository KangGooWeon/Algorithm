class Solution {
    public String solution(int n) {
        String answer = "";
        
        int[] arr = new int[19];
        int i;
        int sum=0;
        int index= 3;
        
        for(i=0; i<19; i++) {
        	sum += index;
        	if(n <= sum) break;
        	arr[i] = sum;
        	index *=3;
        }
        
        index = index/3;
        answer = recu(arr, n, --i, index);
        
        return answer;
    }
    
    public String recu(int[] arr, int n, int i, int index) { 
    	int a = n %3;
    	String temp ="";
    	if ( a == 0) temp = "4";
    	else temp = Integer.toString(a);
    	
    	if (index == 1) return temp;
    	
    	int q = 1;
    	if (i>0) q = arr[i-1] +1;
    	int w = (n-arr[i]-1)/3;
    	
    	return recu(arr, q+w , i-1, index/3) + temp;
    }
}