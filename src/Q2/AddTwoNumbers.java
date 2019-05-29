package Q2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class Digits {
    ArrayList<Integer> first;
    ArrayList<Integer> second;

    public Digits(ArrayList<Integer> first, ArrayList<Integer> second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public Digits adjustLength(ArrayList<Integer> first, ArrayList<Integer> second) {
        int difference = 0;
        if (first.size() > second.size()) {
            difference = first.size() - second.size();
            for (int i = 0; i < difference; i++) {
                second.add(i, 0);
            }
        } else {
            if (first.size() < second.size()) {
                difference = second.size() - first.size();
                for (int i = 0; i < difference; i++) {
                    first.add(i, 0);
                }
            }
        }
        return new Digits(first, second);
    }

    public ArrayList<Integer> getDigits(ListNode listNode) {
        ArrayList<Integer> digits = new ArrayList<>();
        ListNode iterator = listNode;
        while (iterator != null) {
            digits.add(iterator.val);
            iterator = iterator.next;
        }
        Collections.reverse(digits);
        return digits;
    }

    public ArrayList<Integer> generateSum(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> result = new ArrayList<>();
        int carryOver = 0;

        for (int i = first.size() - 1; i >= 0; i--) {
            int sum = first.get(i) + second.get(i) + carryOver;
            //check for last digit
            if (sum >= 10) {
                carryOver = 1;
                result.add(first.size()-1-i, sum - 10);
                if(i==0){
                    result.add(carryOver);
                }
            } else {
                carryOver = 0;
                result.add(first.size()-1-i, sum);
            }
        }
        Collections.reverse(result);
        return result;
    }

    public ListNode createLinkedList(ArrayList<Integer> result) {
        ListNode first = new ListNode(result.get(result.size()-1));
        ListNode start = first;
        for(int i = result.size()-2;i>=0;i--){
            ListNode current = new ListNode(result.get(i));
            start.next = current;
            start = current;
        }
        return first;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> firstNumberDigits = getDigits(l1);
        ArrayList<Integer> secondNumberDigits = getDigits(l2);
//        System.out.println(firstNumberDigits);
//        System.out.println(secondNumberDigits);
        Digits d = adjustLength(firstNumberDigits, secondNumberDigits);
        System.out.println(d.first);
        System.out.println(d.second);
        ArrayList<Integer> result = generateSum(d.first, d.second);
        ListNode node = createLinkedList(result);
        return node;
    }
}

public class AddTwoNumbers {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        ListNode l1 = stringToListNode("[8]");
//        line = in.readLine();
        ListNode l2 = stringToListNode("[5]");

        ListNode ret = new Solution().addTwoNumbers(l1, l2);

        String out = listNodeToString(ret);

        System.out.print(out);
    }
}

