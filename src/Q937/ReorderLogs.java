package Q937;

import java.util.Arrays;
import java.util.Random;

class Solution {

    public int compare(String first, String second) {
        String actualFirst = first.split(" ", 2)[1];
        String actualSecond = second.split(" ", 2)[1];
        boolean equal = false;
        int small = Math.min(actualFirst.length(), actualSecond.length());
        int i=0;
        for (i = 0; i < small; i++) {
//            System.out.println("first<second "+ (actualFirst.charAt(i)<actualSecond.charAt(i)));
//            System.out.println("first>second "+ (actualFirst.charAt(i)>actualSecond.charAt(i)));
            if (actualFirst.charAt(i) > actualSecond.charAt(i)) {
                return 1;
            }

            if (actualFirst.charAt(i) < actualSecond.charAt(i)) {
                return -1;
            }
        }
        // both are equal
        String firstID = first.split(" ", 2)[0];
        String secondID = second.split(" ", 2)[0];
        for (i = 0; i < firstID.length(); i++) {
            if (firstID.charAt(i) > secondID.charAt(i)) {
                return 1;
            }

            if (first.charAt(i) < second.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }

    public int partition(String[] logs, int start, int end) {
//        Random r = new Random(123);
//        int random = r.nextInt((end-start)+1)+start;
//        swap(logs, start, random);
        String pivot = logs[start];

        int i = start;
        int j = end;
        // for(int k =0;k<=end;k++) System.out.println(logs[k]);
        // System.out.println(pivot);
        while (i < j) {
            while (compare(logs[i], pivot) != 1 && i <= j) i++;
            while (compare(logs[j], pivot) != -1 && i<= j) j--;
            if (i<j) {
                swap(logs, i, j);
            }
        }
        swap(logs, start, j);
        return j;
    }

    public void swap(String[] logs, int firstIndex, int secondIndex) {
        String temp = logs[firstIndex];
        logs[firstIndex] = logs[secondIndex];
        logs[secondIndex] = temp;
    }

    public void sort(String[] logs, int start, int end) {
        if (end <= start) return;
        int splittingPoint = partition(logs, start, end);
        sort(logs, start, splittingPoint - 1);
        System.out.println("Start:"+start+"End:"+end);
        sort(logs, splittingPoint+1, end);
    }

    public String[] reorderLogFiles(String[] logs) {
        int last = logs.length - 1;
        for (int i = last; i >= 0; i--) {
            String current = logs[i];
            if (current.charAt(current.length() - 1) - 97 < 0) {
                swap(logs, i, last);
                last--;
            }
        }
        sort(logs,0,last);
        Arrays.stream(logs).forEach(System.out::println);
//        System.out.println(compare(logs[0],logs[1]));
//        System.out.println('c'<'d');
        return logs;
    }
}

public class ReorderLogs {

    public static void main(String[] args) {
        String [] logs = new String[]{"j je","b fjt","7 zbr","m le","o 33"};
        Solution s = new Solution();
        s.reorderLogFiles(logs);
//        Arrays.stream(logs).forEach(System.out::println);

    }

}
