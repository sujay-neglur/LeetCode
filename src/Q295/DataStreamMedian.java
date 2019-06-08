package Q295;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataStreamMedian {
    Heap heap = new Heap();

    public double getMedian(){
        if(heap.maxHeap.size()>heap.minHeap.size()){
            return heap.peek(Heap.Type.MAX_HEAP);
        }
        else {
            return (heap.peek(Heap.Type.MIN_HEAP)+heap.peek(Heap.Type.MAX_HEAP))/2.0;
        }
    }

    public void addNumber(int num){
//        System.out.println("Adding "+num);
        if(heap.minHeap.size()==heap.maxHeap.size()){
            if(heap.minHeap.size()!=0 && num>heap.peek(Heap.Type.MIN_HEAP)){
                heap.add(heap.peek(Heap.Type.MAX_HEAP), Heap.Type.MIN_HEAP);
                heap.remove(Heap.Type.MAX_HEAP);
                heap.add(num, Heap.Type.MIN_HEAP);
            }
            else{
                heap.add(num, Heap.Type.MAX_HEAP);
            }
        }
        else{
            if(num<heap.peek(Heap.Type.MAX_HEAP)){
                heap.add(heap.peek(Heap.Type.MAX_HEAP), Heap.Type.MIN_HEAP);
                heap.remove(Heap.Type.MAX_HEAP);
                heap.add(num, Heap.Type.MAX_HEAP);
            }
            else{
                heap.add(num, Heap.Type.MIN_HEAP);
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
                System.out.println("Max heap "+dsm.heap.maxHeap);
                System.out.println("Min heap "+dsm.heap.minHeap);
                System.out.println("-----------------------------");
            }
        }
//        System.out.println(count);
    }
}
