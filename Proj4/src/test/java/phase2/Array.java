package phase2;

import org.testng.annotations.Test;

import java.util.*;

public class Array {

    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+", ");
        }
    }
    static void reverse(int[] arr){
        int end = arr.length-1;
        int start=0;

        while (start < end) {
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    static void reverse(int[] arr, int start, int end) {
        if(start>=end){
            return;
        }
        int temp = arr[end];
        arr[end] = arr[start];
        arr[start] = temp;
        reverse(arr, ++start, --end);
    }


    static class Pair{
        int min;
        int max;
    }

    static Pair minMaxInArray(int[] arr) {
        Pair minMax = new Pair();
        if (arr.length == 1) {
            minMax.min = arr[0];
            minMax.max = arr[0];
        }

        if (arr[0] > arr[1]) {
            minMax.min = arr[1];
            minMax.max = arr[0];
        } else {
            minMax.min = arr[0];
            minMax.max = arr[1];
        }

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] < minMax.min) {
                minMax.min = arr[i];
            }
            if (arr[i] > minMax.max) {
                minMax.max = arr[i];
            }
        }
        return minMax;
    }

    static Pair minMaxInArray(int[] arr, int low, int high) {
        Pair minMax = new Pair();
        if (low == high) {
            minMax.min = arr[low];
            minMax.max = arr[low];
            return minMax;
        }

        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                minMax.min = arr[low];
                minMax.max = arr[high];
            } else {
                minMax.max = arr[low];
                minMax.min = arr[high];
            }
            return minMax;
        }

        Pair leftMinMax = new Pair();
        Pair rightMinMax = new Pair();
        int mid = low + high / 2;
        leftMinMax = minMaxInArray(arr, low, mid);
        rightMinMax = minMaxInArray(arr, mid + 1, high);

        if (leftMinMax.min < rightMinMax.min) {
            minMax.min = leftMinMax.min;
        } else {
            minMax.min = rightMinMax.min;
        }

        if (leftMinMax.max > rightMinMax.max) {
            minMax.max = leftMinMax.max;
        } else {
            minMax.max = rightMinMax.max;
        }
        return minMax;
    }

    static int kthSmallestElement(int[] arr, int k){
        Set<Integer> set = new TreeSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        k--;

        Iterator<Integer> itr = set.iterator();
        while(k>0){
            itr.next();
            k--;
        }
        return itr.next();
    }

    static int kthSmallestElementUsingMap(int[] arr, int k){
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);
        }

        int freq=0;
        for(Map.Entry it :  map.entrySet()){
            freq= freq + (int)it.getValue();

            if(freq>=k){
                return (int)it.getKey();
            }
        }
        return -1;
    }

    static void sortAnArrayOf012(int[] arr) {
        int i;
        int c0 = 0, c1 = 0, c2 = 0;

        for (i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 0:
                    c0++;
                    break;
                case 1:
                    c1++;
                    break;
                case 2:
                    c2++;
                    break;
            }
        }
        i = 0;
        while (c0 > 0) {
            arr[i++] = 0;
            c0--;
        }
        while (c1 > 0) {
            arr[i++] = 1;
            c1--;
        }
        while (c2 > 0) {
            arr[i++] = 2;
            c2--;
        }
    }

    public static void swap(int[] ar, int i, int j) {
        int t = ar[i];
        ar[i] = ar[j];
        ar[j] = t;
    }

    @Test
    public void moveNegativeToOneSide(){
        int[] ar = { -1, 2, -4, 5, -6, 7};
        rearrange1(ar);
        printArray(ar);
    }

    static void rearrange1(int[] arr){
        int i=0;
        int j=arr.length-1;
        while (i<=j){
            if(arr[i]<0){
                i++;
            } else{
                swap(arr,i,j);
                j--;
            }
        }
    }


    static void rearrange(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            if (arr[low] < 0) {
                low++;
            } else {
                swap(arr, low, high--);
            }
        }
    }

    static void rightrotate(int arr[], int n, int outofplace,
                            int cur)
    {
        int tmp = arr[cur];
        for (int i = cur; i > outofplace; i--)
            arr[i] = arr[i - 1];
        arr[outofplace] = tmp;
    }
    static void alternate(int[] arr) {
        int outofplace = -1;
        int n =  arr.length;
        for (int index = 0; index < n; index++) {
            if (outofplace >= 0) {
                if (((arr[index] >= 0)
                        && (arr[outofplace] < 0))
                        || ((arr[index] < 0)
                        && (arr[outofplace] >= 0))) {
                    rightrotate(arr, n, outofplace, index);

                    // the new out-of-place entry is now 2
                    // steps ahead
                    if (index - outofplace >= 2)
                        outofplace = outofplace + 2;
                    else
                        outofplace = -1;
                }
            }

            // if no entry has been flagged out-of-place
            if (outofplace == -1) {
                // check if current entry is out-of-place
                if (((arr[index] >= 0) && ((index & 0x01) == 0)) || ((arr[index] < 0) && (index & 0x01) == 1))
                    outofplace = index;
            }
        }
    }



    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6};
        reverse(arr); //using two pointer iterative approach
        printArray(arr);
        System.out.println();
        int[] arr1 = {1,2,3,4,5,6};
        reverse(arr1, 0, arr1.length-1); //using two recursive approach
        printArray(arr1);

        //------------------------
        System.out.println();
        int minMaxArray[] = {3,5,4,1,9};
        Pair minMax = minMaxInArray(minMaxArray);
        System.out.println("Min is :: "+minMax.min);
        System.out.println("Max is :: "+minMax.max);

        int minMaxArray1[] = {3,5,4,1,9};
        Pair minMax1 = minMaxInArray(minMaxArray, 0, minMaxArray1.length-1);
        System.out.println("Min is :: "+minMax1.min);
        System.out.println("Max is :: "+minMax1.max);


        //-------------------------
        int[] kthSmallestElement = { 12, 3, 5, 7, 19 };
        int[] kthSmallestElement1 = { 7, 0, 25, 6, 16, 17, 0 };
        int K = 3;
        System.out.println(kthSmallestElement(kthSmallestElement, K)); // when all are distinct element
        System.out.println(kthSmallestElementUsingMap(kthSmallestElement, K));
        System.out.println(kthSmallestElementUsingMap(kthSmallestElement1, K)); // when distinct/undisticnt element

        //-------------------------
        System.out.println();
        int sorted012[] = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        sortAnArrayOf012(sorted012);
        printArray(sorted012);


        //------------------------
        System.out.println();
        //int[] ar = { -1, 2,  -4, -5, 2, -7, 3, 2, -6, -8, -9, 3, 2,  4 };
        int[] ar = { -1, 2, -4, 5, 6, 7};
        rearrange(ar);
        printArray(ar);

        //------------------------
        System.out.println();
        //int[] minOfMaxDiff = { 7, 4, 8, 8, 8, 9 };
        //int[] minOfMaxDiff = { 1,15,10 };
        int[] minOfMaxDiff = { 1,5,15,10 };
        int k = 3;
        System.out.println("Min of maximum height is :: "+getMinDiff(minOfMaxDiff, k));

        //------------------------
        System.out.println();
        int[] num = {1, 2, 3, 6, 3, 6, 1};
        System.out.println("repeating elements are ::");
        findDuplicate(num);

        //------------------------
        System.out.println("missing element is :: ");
        int mis[] = {1, 2, 4, 6, 3, 7, 8};
        missing(mis, mis.length);

        //------------------------
        System.out.println();
//        int pair[] = { 1, 5, 7, -1, 5 };
//        int sum = 6;
        int [] pair = {10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1};
        int sum = 11;
        int n = pair.length;
        System.out.println("count of pairs present are :: "+findPair(pair, sum, n));

        //--------------------------
        int repeat[] = { 10, 5, 3, 4, 3, 5, 6 };
        printFirstRepeating(repeat);

        //--------------------------
        int alternate[] =  {1, 2, 3, -4, -1, 4};
        alternate(alternate);
        printArray(alternate);
//        alternate1(alternate);
//        printArray(alternate);

    }

    static void alternate1(int[] arr) {

        boolean alternate = true;
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            if (arr[i] < 0 && alternate) {
                j = i + 1;
                while (j<arr.length-1 && arr[j] < 0) {
                    j++;
                }
                swap(arr, i, j);
                alternate = false;

            } else if (arr[i] > 0 && !alternate) {
                j = i + 1;
                while (j<arr.length && arr[j] > 0) {
                    j++;
                }
                if (j<arr.length){
                    swap(arr, i, j);
                }

                alternate = true;
            } else {
                alternate=!alternate;
            }
        }
    }


    static void printFirstRepeating(int arr[]) {
        // Initialize index of first repeating element
        int min = -1;
        // Creates an empty hashset
        HashSet<Integer> set = new HashSet<>();
        // Traverse the input array from right to left
        for (int i = arr.length - 1; i >= 0; i--) {
            // If element is already in hash set, update min
            if (set.contains(arr[i])) min = i;
            else set.add(arr[i]); // Else add element to hash set
        }
        // Print the result
        if (min != -1) System.out.println("The first repeating element is " + arr[min]);
        else System.out.println("There are no repeating elements");
    }

    static int findPair(int[] arr, int sum, int n){
        HashMap<Integer, Integer> m = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (m.containsKey(sum - arr[i])) {
                count += m.get(sum - arr[i]);
            }
            if (m.containsKey(arr[i])) {
                m.put(arr[i], m.get(arr[i]) + 1);
            }
            else {
                m.put(arr[i], 1);
            }
        }
        return count;
    }

    static void missing(int[] arr, int N) {
        int i;
        int temp[] = new int[N + 1];
        for (i = 0; i <= N; i++) {
            temp[i] = 0;
        }
        for (i = 0; i < N; i++) {
            temp[arr[i] - 1] = 1;
        }

        int ans = 0;
        for (i = 0; i <= N; i++) {
            if (temp[i] == 0)
                ans = i + 1;
        }
        System.out.println(ans);
//        int sum = ((N + 1) * (N + 2)) / 2;
//        for (int i = 0; i < N; i++)
//            sum -= arr[i];
//        System.out.println(sum);
    }

    static void findDuplicate(int[] numRay){
        for (int i = 0; i < numRay.length; i++) {
            numRay[numRay[i] % numRay.length] = numRay[numRay[i] % numRay.length] + numRay.length;
        }
        System.out.println("The repeating elements are : ");
        for (int i = 0; i < numRay.length; i++) {
            if (numRay[i] >= numRay.length * 2) {
                System.out.println(i + " ");
            }
        }
    }

    static int getMinDiff(int[] arr, int k){
        int n= arr.length;
        Arrays.sort(arr);
        // Maximum possible height difference
        int ans = arr[n - 1] - arr[0];

        int min, max;
        min = arr[0];
        max = arr[n - 1];

        for (int i = 1; i < n; i++) {
            // if on subtracting k we got negative then
            // continue
            if (arr[i] - k < 0)
                continue;
            // Minimum element when we add k to whole array
            min = Math.min(arr[0] + k, arr[i] - k);
            // Maximum element when we subtract k from whole array
            max = Math.max(arr[n - 1] - k, arr[i - 1] + k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }




}
