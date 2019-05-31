package Q146;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Node{
    int val;
    Node next;
    Node previous;

    public Node(int val) {
        this.val = val;
    }
}


class LinkedList{
    Node head;
    Node tail;
    HashMap<Integer,Node> nodeRef = new HashMap<>();

    public void print(Node node){
        System.out.println("Linked list");
        while(node!=null){
            System.out.print(node.val);
            if(node.next!=null){
                System.out.print("-->");
            }
            node = node.next;
        }
        System.out.println();
    }

    public void add(int key){
        Node n = new Node(key);
        if(tail!=null){
            tail.next = n;
            n.previous = tail;
            tail = n;
        }
        else{
            tail = n;
            head = n;
        }
        nodeRef.put(n.val,n);
    }

    public int remove(){
        Node n = head;
        nodeRef.remove(n.val);
        head = head.next;
//        head.previous=null;
        return n.val;
    }

    public void update(int key){
        Node n = nodeRef.get(key);
        if(n==head){
            head=head.next;
            n.previous=null;
            if(head==null) tail=null;
            add(n.val);
        }
        else{
            if(n==tail){
                tail = tail.previous;
            }
           if(n.previous!=null){
               n.previous.next = n.next;
           }
           if(n.next!=null){
               n.next.previous = n.previous;
           }
            add(n.val);
        }
    }
}

public class LRUCache {

    int capacity;
    HashMap<Integer,Integer> lruMap = new HashMap<>();
    LinkedList ll = new LinkedList();

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public void put(int key,int value){
        if(lruMap.containsKey(key)){
            ll.update(key);
            lruMap.put(key,value);
        }
        else{
            if(lruMap.size()<capacity){
                lruMap.put(key,value);
                ll.add(key);
            }
            else{
                int lruValue = ll.remove();
                lruMap.remove(lruValue);
                ll.add(key);
                lruMap.put(key,value);
            }
        }
    }

    public int get(int key){
        if(lruMap.containsKey(key)){
            ll.update(key);
            return lruMap.get(key);
        }
        else{
            return -1;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("src/Q146/input.txt"));
        String [] operations=null;
        ArrayList<String[]> values =null;
        while (sc.hasNext()){
            String line = sc.nextLine();
            String [] input = line.substring(1,line.length()-1).split(",");
            List<Object> tempObjects = Arrays.stream(input).map(x -> x.substring(1,x.length()-1)).collect(Collectors.toList());
            operations = tempObjects.toArray(new String[tempObjects.size()]);
            line = sc.nextLine();
            String newInput = line.substring(1,line.length()-1);
            newInput = newInput.replaceAll("\\["," ").replaceAll("]"," ");
            String [] temp = newInput.split(" , ");
            values = new ArrayList<>();
            values.add(new String[]{temp[0].strip()});
            for(int i=1;i<temp.length;i++){
                values.add(temp[i].split(","));
            }
        }
        LRUCache lru = new LRUCache(Integer.parseInt(values.get(0)[0].strip()));
//        System.out.println(operations.length);
//        System.out.println(values.size());
        for(int i=1;i<operations.length;i++){
            if(operations[i].equals("put")){
                int key = Integer.parseInt(values.get(i)[0].strip());
                int value = Integer.parseInt(values.get(i)[1].strip());
                System.out.println("Putting "+key);
                lru.put(key,value);
            }

            if(operations[i].equals("get")){
                int key = Integer.parseInt(values.get(i)[0].strip());
                System.out.println("Getting "+key);
                lru.get(key);
            }
            lru.ll.print(lru.ll.head);

        }
    }
}
