package Trie;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Trie {
    Node root = new Node('$');
    public void add(String word){
        Node start = root;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i)-'a';
            if(start.connections[index]!=null && start.connections[index].letter == word.charAt(i)){
                start = start.connections[index];
//                System.out.println("already exists "+word.charAt(i));
            }
            else{
//                System.out.println("Adding "+word.charAt(i));
                start.connections[index] = new Node(word.charAt(i));
                start = start.connections[index];
            }
        }
        start.isEndOfWord = true;

    }

    public boolean isPresent(String word){
        Node start = root;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i)-'a';
            if(start.connections[index] == null){
//                System.out.println("got null for "+ word.charAt(i));
                return false;
            }
            start = start.connections[index];
        }
        if(start.isEndOfWord) return true;
        else return false;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.add("there");
        t.add("their");
        t.add("answer");
        t.add("any");
        t.add("bye");
        System.out.println(t.isPresent("there"));
        System.out.println(t.isPresent("hello"));
        System.out.println("h1e l l o".split(" ",2)[1]);
        
    }
}
