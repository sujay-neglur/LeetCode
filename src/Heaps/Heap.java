package Heaps;

import java.util.Random;

public class Heap {
    public int [] resize(int [] heap,int size){
        return new int[heap.length+size];
    }
    public int calculateParentIndex(int current){
        if(current%2==1){
            return  (current-1)/2;
        }
        else{
            return (current-2)/2;
        }
    }

    public int [] extractMin(int [] heap){
        int minimum = heap[0];
        int [] resizedHeap = resize(heap,-1);
        heap[0] = heap[heap.length-1];
        for(int i=0;i<resizedHeap.length;i++){
            resizedHeap[i] = heap[i];
        }
        System.out.println(resizedHeap.length);
        heap = resizedHeap;
//        print(heap);
        return heapify(heap,0);
    }
    public int [] upHeap(int [] heap, int current){
        int parent = calculateParentIndex(current);
        while(heap[parent]>heap[current]){
            swap(heap,current,parent);
            current = parent;
            parent = calculateParentIndex(current);
        }
        return heap;
    }
    public int [] add(int [] heap, int element){
        int [] resizedHeap = resize(heap,1);
        int i;
        for(i=0;i<heap.length;i++){
            resizedHeap[i] = heap[i];
        }
        resizedHeap[i] = element;
        heap = resizedHeap;
//        print(heap);
        return upHeap(heap,heap.length-1);
    }

    public int getMinimum(int indexFirst, int indexSecond, int[] heap) {
        if (heap[indexFirst] < heap[indexSecond]) return indexFirst;
        else return indexSecond;
    }

    public boolean validateChild(int[] heap, int current, int offset) {
        if (2 * current + offset < heap.length) return true;
        return false;
    }

    public void swap(int[] heap, int indexFirst, int indexSecond) {
        int temp = heap[indexFirst];
        heap[indexFirst] = heap[indexSecond];
        heap[indexSecond] = temp;
    }

    public int[] heapify(int[] heap, int current) {
        if(current>heap.length){
            return heap;
        }
        int left = 2 * current + 1;
        int right = 2 * current + 2;
        int minimum = Integer.MAX_VALUE;
        boolean validateLeftChild = validateChild(heap, current, 1);
        boolean validateRightChild = validateChild(heap, current, 2);
        if (validateLeftChild) {
            minimum = getMinimum(current, left, heap);
        }
        if(validateRightChild){
            minimum = getMinimum(minimum,right,heap);
        }
        if(!validateLeftChild && !validateRightChild) return heap;
        if(heap[current]>heap[minimum]){
            swap(heap,current,minimum);
            heapify(heap,minimum);
        }

        return heap;
    }

    public int[] createHeap(int[] elements) {
        int middle = elements.length / 2;
        for (int i = middle; i >= 0; i--) {
            elements = heapify(elements,i);
        }
        return elements;
    }

    static void print(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) System.out.print(",");
        }
        System.out.print("]");
        System.out.println();
    }

    static boolean isPresent(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
//        System.out.println("Hello World");
        int[] heap = new int[10];
        Random r = new Random(345);
        for (int i = 0; i < heap.length; ) {
            int randomNum = r.nextInt(50);
            if (!isPresent(heap, randomNum)) {              
                heap[i] = randomNum;
                i++;
            }
        }
//        print(heap);
        Heap h = new Heap();
        heap = h.createHeap(heap);
//        print(heap);
        heap = h.add(heap,22);
        print(heap);
        heap = h.extractMin(heap);
        print(heap);
    }
}
