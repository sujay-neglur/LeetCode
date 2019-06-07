package Q763;

import java.util.*;

class Index{
    int start;
    int end;
    boolean isDescending = false;

    public Index(int start, int end){
        this.start = start;
        this.end = end;
    }
}
public class LabelPartition {

    public void print(LinkedHashMap<Character,Index> map){
        for(Map.Entry<Character,Index> entry:map.entrySet()){
            System.out.println(entry.getKey()+"---"+entry.getValue().start+" , "+entry.getValue().end);
        }
    }

    public List<Integer> partitionLabels(String s){
        LinkedHashMap<Character,Index> charIndex = new LinkedHashMap<>();
        for(int i=0;i<s.length();i++){
            if(!charIndex.containsKey(s.charAt(i))){
                charIndex.put(s.charAt(i),new Index(i,i));
            }
            else{
                Index index = charIndex.get(s.charAt(i));
                charIndex.put(s.charAt(i),new Index(index.start,i));
            }
        }
        print(charIndex);
        List<Integer> result = new ArrayList<>();
        Stack<Index> stack = new Stack<>();
        Index [] endIndices = charIndex.values().toArray(new Index[charIndex.size()]);
        int start = endIndices[0].start;
        int end = endIndices[0].end;
//        result.add(end-start+1);
        boolean isDescending=false;
        for(int i=1;i<endIndices.length;i++){
            Index index = endIndices[i];
            if(index.start>=start && index.end>=end){
                if(index.start>=end){
                    result.add(end-start+1);
                    start = index.start;
                    end = index.end;
                }
                else{
                    end = index.end;
                }
            }
            else{
                System.out.println("Got here for start "+index.start+" and end "+index.end);
            }
        }
        result.add(end-start+1);
        return result;
    }

    public static void main(String[] args) {
        LabelPartition lp = new LabelPartition();
        System.out.println(lp.partitionLabels("abcammde"));
    }
}
