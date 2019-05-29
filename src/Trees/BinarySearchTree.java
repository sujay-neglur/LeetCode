package Trees;

import java.util.ArrayList;
import java.util.Random;

public class BinarySearchTree {
    Node root;
    public boolean checkLeftValid(int element, Node current){
        return element<= current.data && current.left==null;
    }
    public boolean checkRightValid(int element, Node current){
        return element>current.data && current.right==null;
    }
    public void add(int element){
        Node start = root;
        if(start!=null){
            // normal case
            // two cases exist if element is greater than current node or not
            while (true){
                if(element<=start.data){
                    if(start.left==null){
                        start.left = new Node(element);
                        break;
                    }
                    else{
                        start = start.left;
                    }
                }
                else{
                    if(start.right==null){
                        start.right = new Node(element);
                        break;
                    }
                    else{
                        start = start.right;
                    }
                }
            }
        }
        else{
            root = new Node(element);
        }
    }

    public static void print(Node start){
        if(start==null) return;
        print(start.left);
        System.out.println(start.data);
        print(start.right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> elements = new ArrayList<>();
        Random r= new Random(34218);
        for(int i=0;i<7;i++){
            elements.add(r.nextInt((int)Math.pow(i+1,2)));
        }
        System.out.println(elements);
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0;i<elements.size();i++){
            binarySearchTree.add(elements.get(i));
        }
        print(binarySearchTree.root);
    }
}
