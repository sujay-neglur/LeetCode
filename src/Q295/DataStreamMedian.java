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
        if(heap.maxHeap.size()==0){
            // max heap empty
            heap.add(num, Heap.Type.MAX_HEAP);
            return;
        }

        if(heap.minHeap.size()==0){
            // min heap empty so check if element is less than the max heap top
            if(num<heap.peek(Heap.Type.MAX_HEAP)){
                // remove top of max heap
                int maxHeapTop = heap.remove(Heap.Type.MAX_HEAP);
                // add new element to max heap
                heap.add(num, Heap.Type.MAX_HEAP);
                // add max top to min heap
                heap.add(maxHeapTop, Heap.Type.MIN_HEAP);
                return;
            }
            else{
                // add element to min heap
                heap.add(num, Heap.Type.MIN_HEAP);
                return;
            }
        }

        if(num>heap.peek(Heap.Type.MIN_HEAP)){ // check top of min heap
            if(heap.maxHeap.size()==heap.minHeap.size()){
                // remove top of min heap
                int minHeapTop = heap.remove(Heap.Type.MIN_HEAP);
                // add min heap top to max heap
                heap.add(minHeapTop, Heap.Type.MAX_HEAP);
            }
            //add new element to min heap
            heap.add(num, Heap.Type.MIN_HEAP);
        }
        else {
            if(heap.maxHeap.size()>heap.minHeap.size()){
                // remove top of max heap
                int maxHeapTop = heap.remove(Heap.Type.MAX_HEAP);
                // add to min heap
                heap.add(maxHeapTop, Heap.Type.MIN_HEAP);
            }
            // add new element to max heap
            heap.add(num, Heap.Type.MAX_HEAP);
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
