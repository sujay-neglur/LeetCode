package Q763;

import java.util.HashMap;
import java.util.List;
class Index{
    int start;
    int end;

    public Index(int start, int end){
        this.start = start;
        this.end = end;
    }
}
public class LabelPartition {
    public List<Integer> partitionLabels(String s){
        HashMap<Character,Index> charIndex = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!charIndex.containsKey(s.charAt(i))){
                charIndex.put(s.charAt(i),new Index(i,i));
            }
            else{
                Index index = charIndex.get(s.charAt(i));
                charIndex.put(s.charAt(i),new Index(index.start,i));
            }
        }
        System.out.println(charIndex.values());
        return null;
    }

    public static void main(String[] args) {
        LabelPartition lp = new LabelPartition();
        lp.partitionLabels("abcdabcd");
    }
}
