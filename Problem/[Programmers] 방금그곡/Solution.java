import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class Solution {
    public String solution(String m, String[] musicinfos) throws ParseException {
        String answer = "(None)";
        List<Data> list = new ArrayList<>();
        
        for(int i=0; i<musicinfos.length; i++) {
        	String[] info = musicinfos[i].split(",");
        	Date startDate = new SimpleDateFormat("HH:mm").parse(info[0]);
            Date startDate2 = new SimpleDateFormat("HH:mm").parse(info[1]);
            int time = (int) ((startDate2.getTime() - startDate.getTime()) / 60000);
            
            String ch = change(info[3]);
            
            String newMelody = "";
            
            for(int j=0; j<time; j++) {
            	newMelody += ch.charAt(j%ch.length());
            }
            
            System.out.println(newMelody);
            list.add(new Data(i,time, info[2], newMelody));
        }
        
        Collections.sort(list);
        //찾기
        m = change(m);
        for(int i=0; i<list.size(); i++) {
        	Data data = list.get(i);
        	if(data.melody.contains(m)) return data.title;
        }

        return answer;
    }
	
	private String change(String str) {
		str = str.replace("C#", "1");
		str = str.replace("D#", "2");
		str = str.replace("F#", "3");
		str = str.replace("G#", "4");
		str = str.replace("A#", "5");
		
		return str;
	}

	static class Data implements Comparable<Data>{
		int index;
		int time;
		String title;
		String melody;
		public Data(int index, int time, String title, String melody) {
			this.index = index;
			this.time = time;
			this.title = title;
			this.melody = melody;
		}
		
		@Override
		public int compareTo(Data o) {
			if(this.time == o.time) return this.index - o.index;
			return (this.time - o.time)*-1;
		}
	}
}