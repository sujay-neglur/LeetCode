package Q4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

class Solution {

    public int getPartition(int[] array) {
        if (array.length % 2 == 1) {
            // odd length
            return array.length / 2;
        } else return array.length / 2 - 1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int totalElements ;
        double median;
        if (totalLength % 2 == 1) {
            totalElements = totalLength / 2 + 1;
        } else {
            totalElements = totalLength / 2;
        }
        int[] small, big;
        // find smaller array
        if (nums1.length < nums2.length) {
            small = nums1;
            big = nums2;
        } else {
            small = nums2;
            big = nums1;
        }
        //get partition point
        int smallPartition = getPartition(small);
        System.out.println(smallPartition);
        int remainingElements = totalElements - smallPartition - 1;
        // find remaining elements
        int bigPartition = big.length / 2 - 1;
        System.out.println(bigPartition);
        if(totalLength%2==1){
            median = Math.max(small[smallPartition],big[bigPartition]);
        }
        else{
            int first = Math.max(small[smallPartition],big[bigPartition]);
            System.out.println(first);
            int second = Math.min(small[smallPartition+1], big[bigPartition-1]);
            median = ((double)first+second)/2;
        }
        // get partition point in larger array
        // check total length and apply logic
        return median;
    }
}

public class MedianSortedArrays {
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

    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
        int[] nums1 = stringToIntegerArray("[1, 3,4,5,7,9]");
//        line = in.readLine();
        int[] nums2 = stringToIntegerArray("[1,2,3]");

        double ret = new Solution().findMedianSortedArrays(nums1, nums2);

        String out = doubleToString(ret);

        System.out.print(out);
    }
}
