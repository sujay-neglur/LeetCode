package Q380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    ArrayList<Integer> values = new ArrayList<>();
    HashMap<Integer,Integer> valueIndexMap = new HashMap<>();

    public boolean add(int val){
        if(valueIndexMap.containsKey(val)){
            return false;
        }
        values.add(val);
        valueIndexMap.put(val,values.size()-1);
        return true;
    }

    public boolean remove(int val){
        if(!valueIndexMap.containsKey(val)){
            return false;
        }

        int indexOfVal = valueIndexMap.get(val);
        int lastElement = values.get(values.size()-1);
        values.set(indexOfVal,lastElement);
        values.remove(values.size()-1);
        valueIndexMap.remove(val);
        if(lastElement!=val){
            valueIndexMap.put(lastElement,indexOfVal);
        }
        return true;
    }

    public static void main(String[] args) {
        RandomizedSet rs = new RandomizedSet();

        Random r = new Random(123);
        ArrayList<Integer> inputs = new ArrayList<>();
        for(int i=0;inputs.size()<15;i++){
            int num = r.nextInt(i+1);
            inputs.add(num);
//            if(!inputs.contains(num)){
//            }
        }
        for(int i=0;i<inputs.size();i++){
            rs.add(inputs.get(i));
        }

        for(int i=0;i<inputs.size();i++){
//            System.out.println(i);
            rs.remove(inputs.get(i));
        }
    }

}
