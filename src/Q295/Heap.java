package Q295;

import java.util.ArrayList;

class Heap{
    enum Type{
        MIN_HEAP, MAX_HEAP
    }
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

    public int peek(Type type){
        if(type == Type.MIN_HEAP){
            return minHeap.get(0);
        }
        else {
            return maxHeap.get(0);
        }
    }

    public int getChildIndex(int current, int offset){
        return 2*current+offset;
    }

    public void swap(Type type, int firstIndex, int secondIndex){
        if(type==Type.MIN_HEAP){
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

    public void upHeap(Type type){
//        System.out.println("Up heaping "+type);
        if(type == Type.MIN_HEAP){
            // do upheap for min heap
            int current = minHeap.size()-1;
//            System.out.println(parentIndex);
            while (current>0){
                int parentIndex = getParentIndex(current);
                if(minHeap.get(parentIndex)>minHeap.get(current)){
                    swap(Type.MIN_HEAP,current,parentIndex);
//                    System.out.println("Swapping done for "+type);
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
                    swap(Type.MAX_HEAP,current,parentIndex);
//                    System.out.println("Swapping done for "+type);
                    current = parentIndex;
                }
                else break;
            }
        }
    }

    boolean validateChild(Type type, int childIndex){
        if(type == Type.MIN_HEAP){
            return childIndex<minHeap.size();
        }
        else{
            return childIndex<maxHeap.size();
        }
    }

    public void downHeap(Type type){
//        System.out.println("Down heaping "+type);
        int current = 0;
        int leftChildIndex;
        int rightChildIndex;
        if(type==Type.MIN_HEAP){
            // downheap for min heap
            int minimum = Integer.MAX_VALUE;
            while (true){
//                int minimum = Integer.MAX_VALUE;
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                if(!validateChild(type,leftChildIndex) && !validateChild(type,rightChildIndex)) break;
                if(current==minimum) break;
                minimum = Integer.MAX_VALUE;
                if(validateChild(type,leftChildIndex)){
                    // left child is present
                    minimum = minHeap.indexOf(Math.min(minHeap.get(current),minHeap.get(leftChildIndex)));
                }
                if(validateChild(type,rightChildIndex)){
                    // right child is present
                    minimum = minHeap.indexOf(Math.min(minHeap.get(minimum),minHeap.get(rightChildIndex)));
                }

                swap(type,minimum,current);
//                System.out.println("Swapping done for "+type);
                current=minimum;
            }
        }
        else{
            int maximum = Integer.MIN_VALUE;
            while (true){
//                int maximum = Integer.MIN_VALUE;
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                if(!validateChild(type,leftChildIndex) && !validateChild(type,rightChildIndex)) break;
                if (current==maximum) break;
                maximum = Integer.MIN_VALUE;
                if(validateChild(type,leftChildIndex)){
                    // left child is present
                    maximum = maxHeap.indexOf(Math.max(maxHeap.get(current),maxHeap.get(leftChildIndex)));
                }
                if(validateChild(type,rightChildIndex)){
                    // right child is present
                    maximum = maxHeap.indexOf(Math.max(maxHeap.get(maximum),maxHeap.get(rightChildIndex)));
                }

                swap(type,maximum,current);
                current=maximum;
            }
        }
    }

    public int remove(Type type){
        int removedValue;
        if(type == Type.MIN_HEAP){
            // removal for min heap
            removedValue = minHeap.get(0);
            minHeap.set(0,minHeap.get(minHeap.size()-1));
            minHeap.remove(minHeap.size()-1);
        }
        else{
            removedValue = maxHeap.get(0);
            maxHeap.set(0,maxHeap.get(maxHeap.size()-1));
            maxHeap.remove(maxHeap.size()-1);
        }
        downHeap(type);
//        System.out.println("Removed "+removedValue+" from "+type);
        return removedValue;
    }

    public void add(int element, Type type){
        System.out.println("Adding "+element+" to "+type);
        if(type==Type.MIN_HEAP){
            minHeap.add(element);

        }
        else {
            maxHeap.add(element);
        }
        upHeap(type);
    }
}
