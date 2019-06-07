package Q295;

import java.util.ArrayList;

class Heap{
    ArrayList<Integer> minHeap = new ArrayList<>();
    ArrayList<Integer> maxHeap = new ArrayList<>();

    public int getParentIndex(int current){
        if(current%2==1){
            // current element is the left child
            return (current-1)/2;
        }
        else {
            //current element is the right child
            return (current-2)/2;
        }
    }

    public int peek(boolean min){
        if(min){
            return minHeap.get(0);
        }
        else {
            return maxHeap.get(0);
        }
    }

    public int getChildIndex(int current, int offset){
        return 2*current+offset;
    }

    public void swap(boolean min, int firstIndex, int secondIndex){
        if(min){
            // do swapping in min heap
            int temp = minHeap.get(firstIndex);
            minHeap.set(firstIndex,minHeap.get(secondIndex));
            minHeap.set(secondIndex,temp);
        }
        else{
            int temp = maxHeap.get(firstIndex);
            maxHeap.set(firstIndex,maxHeap.get(secondIndex));
            maxHeap.set(secondIndex,temp);
        }
    }

    public void upHeap(boolean min){
        if(min){
            // do upheap for min heap
            int current = minHeap.size()-1;
//            System.out.println(parentIndex);
            while (current>0){
                int parentIndex = getParentIndex(current);
                if(minHeap.get(parentIndex)>minHeap.get(current)){
                    swap(true,current,parentIndex);
                    current=parentIndex;
                }
                else break;
            }
        }
        else{
            // do upheap for max heap
            int current = maxHeap.size()-1;
            while (current>0){
                int parentIndex = getParentIndex(current);
                if(maxHeap.get(parentIndex)<maxHeap.get(current)){
                    swap(false,current,parentIndex);
                    current = parentIndex;
                }
                else break;
            }
        }
    }

    boolean validateChild(boolean min, int childIndex){
        if(min){
            return childIndex<minHeap.size();
        }
        else{
            return childIndex<maxHeap.size();
        }
    }

    public void downHeap(boolean min){
        int current = 0;
        int leftChildIndex = getChildIndex(current,1);
        int rightChildIndex = getChildIndex(current,2);
        if(min){
            // downheap for min heap
            int minimum = Integer.MAX_VALUE;
            while (true){
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                if(!validateChild(min,leftChildIndex) && !validateChild(min,rightChildIndex)) break;
                if(current==minimum) break;

                if(validateChild(min,leftChildIndex)){
                    // left child is present
                    minimum = minHeap.indexOf(Math.min(minHeap.get(current),minHeap.get(leftChildIndex)));
                }
                if(validateChild(min,rightChildIndex)){
                    // right child is present
                    minimum = minHeap.indexOf(Math.min(minHeap.get(minimum),minHeap.get(rightChildIndex)));
                }

                swap(min,minimum,current);
                current=minimum;
            }
        }
        else{
            while (true){
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                int maximum = Integer.MIN_VALUE;
                if(!validateChild(min,leftChildIndex) && !validateChild(min,rightChildIndex)) break;
                if (current==maximum) break;
                if(validateChild(min,leftChildIndex)){
                    // left child is present
                    maximum = maxHeap.indexOf(Math.max(maxHeap.get(current),maxHeap.get(leftChildIndex)));
                }
                if(validateChild(min,rightChildIndex)){
                    // right child is present
                    maximum = maxHeap.indexOf(Math.max(maxHeap.get(maximum),maxHeap.get(rightChildIndex)));
                }

                swap(min,maximum,current);
                current=maximum;
            }
        }
    }

    public int remove(boolean min){
        int removedValue;
        if(min){
            // removal for min heap
            removedValue = minHeap.get(0);
            minHeap.set(0,minHeap.get(minHeap.size()-1));
            minHeap.remove(minHeap.size()-1);
            downHeap(true);
        }
        else{
            removedValue = maxHeap.get(0);
            maxHeap.set(0,maxHeap.get(maxHeap.size()-1));
            maxHeap.remove(maxHeap.size()-1);
            downHeap(false);
        }
        return removedValue;
    }

    public void add(int element, boolean min){
        if(min){
            minHeap.add(element);
            upHeap(true);
        }
        else {
            maxHeap.add(element);
            upHeap(false);
        }
    }
}

public class DataStreamMedian {
    Heap heap = new Heap();

    public double getMedian(){
        if(heap.maxHeap.size()>heap.minHeap.size()){
            return heap.peek(false);
        }
        else {
            return (heap.peek(true)+heap.peek(false))/2.0;
        }
    }

    public void addNumber(int num){
        System.out.println("Adding "+num);
        if(heap.maxHeap.size()==0){
            // max heap empty
            heap.add(num,false);
            return;
        }

        if(heap.minHeap.size()==0){
            // min heap empty so check if element is less than the max heap top
            if(num<heap.peek(false)){
                // remove top of max heap
                int maxHeapTop = heap.remove(false);
                // add new element to max heap
                heap.add(num,false);
                // add max top to min heap
                heap.add(maxHeapTop,true);
                return;
            }
            else{
                // add element to min heap
                heap.add(num,true);
                return;
            }
        }

        if(num>heap.peek(true)){ // check top of min heap
            if(heap.maxHeap.size()==heap.minHeap.size()){
                // remove top of min heap
                int minHeapTop = heap.remove(true);
                // add min heap top to max heap
                heap.add(minHeapTop,false);
            }
            //add new element to min heap
            heap.add(num,true);
        }
        else {
            if(heap.maxHeap.size()>heap.minHeap.size()){
                // remove top of max heap
                int maxHeapTop = heap.remove(false);
                // add to min heap
                heap.add(maxHeapTop,true);
            }
            // add new element to max heap
            heap.add(num,false);
        }
    }
    public static void main(String[] args) {
        DataStreamMedian dsm = new DataStreamMedian();
        boolean min = false;
        dsm.addNumber(6);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());
        dsm.addNumber(10);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());
        dsm.addNumber(2);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());
        dsm.addNumber(6);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());
        dsm.addNumber(5);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());
        dsm.addNumber(0);
        System.out.println("Max heap "+dsm.heap.maxHeap);
        System.out.println("Min heap "+dsm.heap.minHeap);
        System.out.println(dsm.getMedian());

    }
}
