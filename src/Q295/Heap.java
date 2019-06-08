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
        int leftChildIndex;
        int rightChildIndex;
        int current = 0;
        if(type==Type.MIN_HEAP){
            while (true){
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                boolean validateLeftChild = validateChild(type,leftChildIndex);
                boolean validateRightChild = validateChild(type,rightChildIndex);
                if(!validateLeftChild && !validateRightChild) break;
                int minimum = Integer.MAX_VALUE;
                if(validateLeftChild){
                    minimum = Math.min(minHeap.get(current),minHeap.get(leftChildIndex))==minHeap.get(current)
                            ?current:leftChildIndex;
                }

                if(validateRightChild){
                    minimum = Math.min(minHeap.get(minimum),minHeap.get(rightChildIndex))==minHeap.get(minimum)
                            ?minimum:rightChildIndex;
                }

                if(minHeap.get(current)>minHeap.get(minimum)){
                    swap(type,current,minimum);
                    current = minimum;
                }
                else {
                    break;
                }
            }
        }

        else{
            while (true){
                leftChildIndex = getChildIndex(current,1);
                rightChildIndex = getChildIndex(current,2);
                boolean validateLeftChild = validateChild(type,leftChildIndex);
                boolean validateRightChild = validateChild(type,rightChildIndex);
                if(!validateLeftChild && !validateRightChild) break;
                int maximum = Integer.MIN_VALUE;
                if(validateLeftChild){
                    maximum = Math.max(maxHeap.get(current),maxHeap.get(leftChildIndex))==maxHeap.get(current)?
                            current:leftChildIndex;
                }

                if(validateRightChild){
                    maximum = Math.max(maxHeap.get(maximum),maxHeap.get(rightChildIndex))==maxHeap.get(maximum)
                            ?maximum:rightChildIndex;
                }

                if(maxHeap.get(current)<maxHeap.get(maximum)){
                    swap(type,current,maximum);
                    current = maximum;
                }
                else {
                    break;
                }
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
