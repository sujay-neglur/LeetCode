package LinkedList;


import java.util.Random;

class Node{
    int data;
    Node next;
    Node random;
    public Node(int data){
        this.data = data;
    }

    public Node(int data, Node next, Node random){
        this.data = data;
        this.next = next;
        this.random = random;
    }
}

public class LinkedList{
    Node first;
    Node last;


    public Node reverse(Node head){
       Node current = head;
       Node runner = null;
       Node previous =null;
       while(current!=null){
            runner = current.next;
            current.next=previous;
            previous=current;
            current=runner;
       }
       return previous;
    }

    public void add(int data){
        Node n = new Node(data);
        if(last!=null){
            last.next = n;
            last = n;
        }
        else{
            first = n;
            last = n;
        }
    }

    public void add(int data, int position){
        Node n = new Node(data);
        Node start = first;
        for(int i =0; i<position-1;i++){
            start = start.next;
        }
        n.next = start.next;
        start.next = n;
    }

    public void remove(int data){
        Node start = first;
        Node previous = null;
        while(start!=null){
            if(start.data == data){
                if(previous!=null){
                    // node to delete is not firs node
                    previous.next = start.next;
                }
                else{
                    // node to delete is first
                    first = start.next;
                }
                break;
            }
            else{
                previous = start;
                start = start.next;
            }
        }
    }
    public void print(Node head){
        Node start = head;
        while(start !=null){
            System.out.print(start.data);
            if(start.next!=null) System.out.print("-->");
            start = start.next;
        }
        System.out.println();
    }
    public LinkedList partition(LinkedList list, int x){
        System.out.println("Partitioning around "+x);
        Node firstStart =null;
        Node firstEnd =null;
        Node secondStart = null;
        Node secondEnd = null;
        Node originalList = list.first;
        while (originalList!=null){
            Node n = new Node(originalList.data);
            if(originalList.data<x){
                if(firstEnd!=null){
                    firstEnd.next = n;
                    firstEnd = n;
                }
                else{
                    firstStart = n;
                    firstEnd = n;
                }
            }
            else{
                if(secondEnd!=null){
                    secondEnd.next = n;
                    secondEnd = n;
                }
                else{
                    secondStart = n;
                    secondEnd = n;
                }
            }
            originalList = originalList.next;
        }
        if(firstEnd!=null) firstEnd.next = secondStart;
        LinkedList newList = new LinkedList();
        newList.first = firstStart;
        return newList;
    }
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        for(int i=51;i<61;i++){
            linkedList.add(new Random().nextInt(i));
        }
        linkedList.print(linkedList.first);
        Node head = linkedList.reverse(linkedList.first);
        linkedList.print(head);
    }

}