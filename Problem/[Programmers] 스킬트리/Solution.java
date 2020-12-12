class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i=0; i<skill_trees.length; i++){
            String temp = skill_trees[i];
            
            boolean flag = true;
            int location = -1;
            
            for(int j=0; j<skill.length(); j++){
                int check = temp.indexOf(skill.charAt(j));
                if(check == -1) {
                	location = 27;
                	continue;
                }
                
                if(location < check){
                    location = check;
                    flag = true;
                }else{
                	flag = false;
                    break;
                }
            }
            
            if(flag) answer++;
        }
        
        return answer;
    }
}