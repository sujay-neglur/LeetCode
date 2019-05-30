package Q5;

import java.io.IOException;
import java.util.ArrayList;

class Index {
    int start;
    int end;
    int length;

    public Index(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }
}

class Solution {

    public Index getLongestPalindrome(String s, int start, int end) {
        int currentLength = 0;
        int currentStart = 0;
        int currentEnd = 0;
        int low = start;
        int high = end;
        while (low >= 0 && high < s.length()) {
            if (s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > currentLength) {
                    currentLength = high - low + 1;
                    currentStart = low;
                    currentEnd = high;
                }
                low--;
                high++;
            } else {
                break;
            }
        }
//        System.out.println(currentLength);
        return new Index(currentStart, currentEnd, currentLength);
    }

    public String longestPalindrome(String s) {
        int startIndex = 0;
        int endIndex = 0;
        int maxLength = 0;
        for (int i = 1; i < s.length(); i++) {
//            System.out.println(i);
//            System.out.print("Odd length ");
            Index odd = getLongestPalindrome(s, i - 1, i + 1);
            int currentEndOdd = odd.end;
            int currentStartOdd = odd.start;
            int currentLengthOdd = odd.length;
//            System.out.println("Odd length after return " + currentLengthOdd);
//            System.out.print("Even length ");
            Index even = getLongestPalindrome(s, i - 1, i);
            int currentEndEven = even.end;
            int currentStartEven = even.start;
            int currentLengthEven = even.length;
//            System.out.println("Even length after return " + currentLengthEven);
//            System.out.println(i);
            int maximum = Math.max(currentLengthEven, currentLengthOdd);
            if (maximum == currentLengthEven) {
                if(maximum>maxLength){
                    startIndex = currentStartEven;
                    endIndex = currentEndEven;
                    maxLength = maximum;
                }

            }
            if (maximum == currentLengthOdd) {
                if(maximum>maxLength){
                    startIndex = currentStartOdd;
                    endIndex = currentEndOdd;
                    maxLength = maximum;
                }
            }
//            System.out.println("Start " + startIndex);
//            System.out.println("End " + endIndex);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=startIndex;i<=endIndex;i++){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

public class LongestPalindromicString {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }

    public static void main(String[] args) throws IOException {
//        String line;
        String s = "cabbadc";

        String ret = new Solution().longestPalindrome(s);

        String out = (ret);

        System.out.println(out);

        System.out.println("h1 e l l o".split(" ",2)[1]);
    }
}