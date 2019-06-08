package Q295;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DataStreamMedian {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public double getMedian(){
        if(maxHeap.isEmpty()) return 0;

        if(maxHeap.size()>minHeap.size()){
            return maxHeap.peek();
        }
        else {
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }
    }

    public void addNumber(int num){
//        System.out.println("Adding "+num);
        if(maxHeap.size()==minHeap.size()){
            if((minHeap.peek()!=null) && (num>minHeap.peek())){
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
            else{
                maxHeap.offer(num);
            }
        }
        else{
            if(num<maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }
            else{
                minHeap.offer(num);
            }
        }
    }

    public String [] parseOperations(String line){
        return line.substring(1,line.length()-1).replaceAll("\"","").split(",");
    }

    public String [] parseInput(String line){
        return line.substring(1,line.length()-1)
                .replaceAll("\\[","")
                .replaceAll("\\]","")
                .split(",");
    }
    public static void main(String[] args) throws FileNotFoundException {
        DataStreamMedian dsm = new DataStreamMedian();
        Scanner sc = new Scanner(new File("src/Q295/input.txt"));
        String [] operations = dsm.parseOperations(sc.nextLine());
        String [] inputValues = dsm.parseInput(sc.nextLine());
//        int count =0;
        for(int i=0;i<operations.length;i++){
            if(operations[i].equals("MedianFinder")) continue;
            if(operations[i].equals("findMedian")){
                System.out.println("Median is "+dsm.getMedian());
            }
            if(operations[i].equals("addNum")){
//                count++;
                int input = Integer.parseInt(inputValues[i]);
                dsm.addNumber(input);
                System.out.println("Max heap "+dsm.maxHeap);
                System.out.println("Min heap "+dsm.minHeap);
                System.out.println("-----------------------------");
            }
        }
//        System.out.println(count);
        
    }
}
