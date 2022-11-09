import org.testng.annotations.Test;

import java.util.*;

public class ArrayConcept {

    int majElementUsingBruteForce(int[] arr) {
        int count = 0;
        int max = 0;
        int num = 0;
        //Using brute force algorithm -
        // Time complexity : O((n^2)
        // Space complexity : O(1)
        for (int i = 0; i < arr.length; i++) {
            count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                num = arr[i];
                if (max > arr.length / 2) {
                    return num;
                }
            }
        }
        return 0;
    }

    int usingSortTech(int[] arr) {
        int count = 0;
        int max = 0;
        int num = 0;
        int ele = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (ele == arr[i]) {
                count++;
                if (count > max) {
                    max = count;
                    num = ele;
                }
            } else {
                ele = arr[i];
                count=0;
            }
        }
        if (max > arr.length / 2) {
            return num;
        }
        return 0;
    }

    int usingSpace(int[] arr) {
        int max = 0;
        int ele = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
                if (map.get(arr[i]) > max) {
                    max = map.get(arr[i]);
                    ele = arr[i];
                }
            } else {
                map.put(arr[i], 1);
            }
        }
        if (max > arr.length / 2) {
            return ele;
        }
        return 0;
    }

    int mooreApproach(int[] arr){
        int ansIndex=0;
        int count=1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]==arr[ansIndex]){
                count++;
            } else{
                count--;
            }
            if(count==0){
                ansIndex=i;
                count=1;
            }
        }
        for(int i=0;i<arr.length;i++){
            int ele=arr[ansIndex];
            int eleCount=0;
            if(ele==arr[i]){
                eleCount++;
                if(eleCount>arr.length/2){
                    return ele;
                }
            }
        }
        return 0;
    }
    @Test(description = "Majority element in an array of size N")
    public void test(){
        int[] arr = {5,1,4,1,1};
        //Using brute force algorithm - Time complexity : O((n^2) --- Space complexity : O(1)
        int res=majElementUsingBruteForce(arr);
        System.out.println(res==0?"No majority element present":res);

        //Method 2: First sort the array (complexity: O(NLogN)) and use only 1 for loop, get the count and find max
        Arrays.sort(arr);
        int res1=usingSortTech(arr);
        System.out.println(res1==0?"No majority element present":res1);

        int[] arr1 = {1,1,4,5,4};
        //Method 3: Using space : using space means either of the one mentioned below
        //1. Using dynamic programming 2. Using Hashmap
        // Time complexity: O(N) and space complexity : O(N)
        int res2= usingSpace(arr1);
        System.out.println(res2==0?"No majority element present":res2);

        //Method 4: Moore's voting algorithm - When same num is found increase by 1 else decrease by 1
        int res3= mooreApproach(arr1);
        System.out.println(res2==0?"No majority element present":res2);
    }

    int bruteSum(int[] arr) {
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum = sum + arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    int kadanesAlgo(int[] arr){
        int curSum=0;
        int maxSum=0;
        int index=0;
        for(int i=0;i<arr.length;i++){
            curSum=curSum+arr[i];
            if(curSum>maxSum){
                maxSum=curSum;
            }
            if(curSum<0){
                curSum=0;
                index=0;
            }
        }
        return maxSum;
    }
    @Test(description = "Largest Sum Contiguous Subarray")
    public void test2(){
        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
        //Using brute force technique // O(N^2) complexity
        System.out.println(bruteSum(arr));
        //Method 2 : sorting is not possible because it will disturb the array element positions which will violate the ques
        //Method 3: Kadane's Algorithm
        System.out.println(kadanesAlgo(arr));
    }

    void buyAndSellOnlyOneStock(int[] stock) {
        int minSoFar = stock[0];
        int max = 0;
        int profit = 0;
        int buyDay = 0;
        int sellDay = 0;
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] < minSoFar) {
                minSoFar = stock[i];
                buyDay = i;
            }
            max = stock[i];
            if (max - minSoFar > profit) {
                profit = max - minSoFar;
                sellDay = i;
            }
        }
        System.out.println("profit is ::: " + profit);
        System.out.println("Buy and sell day is ::: " + (buyDay) + "," + (sellDay));
    }
    void buyAndSellMultipleStocks(int[] stock){
        int profit = 0;
        for(int i=1;i<stock.length;i++){
            if(stock[i]>stock[i-1]){
                profit=profit+(stock[i]-stock[i-1]);
            }
        }
        System.out.println(profit);
    }
    @Test(description = "Stocks buy and sell")
    public void test3() {
        int[] stock = {3, 1, 4, 8, 7, 2, 5};
        buyAndSellOnlyOneStock(stock);
        int [] stock1= {100, 180, 260, 310, 40, 535, 695 };
        buyAndSellMultipleStocks(stock1);
    }

    int maxTrappedWater(int[] blocks){
        int[] left = new int[blocks.length];
        int[] right = new int[blocks.length];
        int totalWater=0;

        //Creating auxiliary array first
        left[0]=blocks[0];
        for(int i=1;i<blocks.length;i++){
            left[i]=Math.max(blocks[i], left[i-1]);
        }
        right[blocks.length-1]=blocks[blocks.length-1];
        for(int i= blocks.length-2;i>=0;i--){
            right[i]=Math.max(blocks[i], right[i+1]);
        }
        for(int i=0;i< blocks.length;i++){
           totalWater = totalWater+(Math.min(left[i], right[i])-blocks[i]);
        }

        return totalWater;
    }
    @Test(description = "Trapping rain water")
    public void test4(){
        int[] blocks = {3,1,2,4,0,1,3,2};
        System.out.println(maxTrappedWater(blocks));
    }

    void swap(int[] a, int i, int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    @Test(description = "Bubble sort")
    public void test5() {
        //boolean swap is for optimizing more in order to stop the execution further if elements are already swapped
        int[] a = {4, 3, 7, 1, 5};
        int n = a.length;
        boolean swap = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j + 1] < a[j]) {
                    swap = true;
                    swap(a, j + 1, j);
                }
            }
            if (!swap) {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + ",");
        }
    }

    @Test(description = "Insertion Sort")
    public void test6(){
        int[] a = {8,4,1,5,9,2};
        int n =  a.length;
        for(int i=1;i<n;i++){
            int temp = a[i];
            int j=i-1;
            while(j>=0 && a[j]>temp){
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=temp;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + ",");
        }
    }

    @Test(description = "Selection sort")
    public void test7(){
        int[] a = {8,4,1,5,9,2};
        int n=a.length;

        for(int i=0;i<n-1;i++){
            int min = i;
            for(int j=i+1;j<n;j++){
                if(a[j]<a[min]){
                    min=j;
                }
            }
            if(min!=i){
                swap(a, min, i);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + ",");
        }
    }

    int partition(int [] a, int l, int h){
        int pivot =  a[l];
        int i=l;
        int j=h;
        while(i<j){
            while(a[i]<=pivot) i++;
            while(a[j]>pivot) j--;
            if(i<j){
                swap(a, i, j);
            }
        }
        swap(a, j, l);
        return j;
    }

    void quickSort(int [] a, int l , int h){
        if(l<h){
            int pivot = partition(a, l, h);
            quickSort(a, l, pivot-1);
            quickSort(a, pivot+1, h);
        }
    }


    @Test(description = "Quick sort")
    public void test8() {
        int[] a = {4,6,2,5,7,9,1,3};
        int n = a.length;
        quickSort(a, 0, n-1);
        for (int j = 0; j < n; j++) {
            System.out.print(a[j] + ",");
        }
    }

    void merge(int[] a, int l, int mid, int r) {
        int[] out = new int[a.length];
        int i = l;
        int j = mid + 1;
        int k = l;

        while (i <= mid && j <= r) {
            if (a[i] < a[j]) {
                out[k] = a[i];
                i++;
            } else {
                out[k] = a[j];
                j++;
            }
            k++;
        }
        //to check if sub-arrays are exhausted
        if (i > mid) {
            while (j <= r) {
                out[k] = a[j];
                k++;
                j++;
            }
        } else {
            while (i <= mid) {
                out[k] = a[i];
                k++;
                i++;
            }
        }
        //copy out into a array
        for (k = l; k <= r; k++) {
            a[k] = out[k];
        }

    }

    void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(a, l, mid);
            mergeSort(a, mid + 1, r);
            merge(a, l, mid, r);
        }
    }

    @Test(description = "Merge sort")
    public void test9() {
        int[] a = {9, 4, 7, 6, 3, 1, 5};
        int n = a.length;
        mergeSort(a, 0, n - 1);
        for (int j = 0; j < n; j++) {
            System.out.print(a[j] + ",");
        }
    }

    /**
     * Binary search concept
     */
    int binarySearchRecursively(int[] a, int key, int l, int h)
    {
        if(l>h){
            return -1;
        }
        int mid = (l+h)/2;
        if(a[mid]==key){
            return mid;
        }
        if(key>a[mid]){
            return binarySearchRecursively(a, key, mid+1, h);
        } else {
            return binarySearchRecursively(a, key, l, mid-1);
        }
    }

    int binarySearchIteratively(int[] a, int key){
        int l=0;
        int h=a.length-1;
        while(l<=h){
            int mid= (l+h)/2;
            if(a[mid]==key){
                return mid;
            } else if(key>a[mid]){
                l=mid+1;
            } else{
                h=mid-1;
            }
        }
        return -1;
    }

    int binarySearchInInfiniteArray(int[] a, int key){
        int low =0;
        int high =1;
        while(a[high]<key){
            low=high;
            high=2*high;
        }
        return binarySearchRecursively(a, key, low, high);
    }

    @Test(description = "Binary Search")
    public void test10(){
        int[] a = {-4,-1,3,7,10,11};
        int n= a.length-1;
        int res = binarySearchIteratively(a,7);
        System.out.println(res==-1?"Element is not present":res);
        System.out.println(binarySearchRecursively(a, 5,0,n));
        System.out.println(binarySearchInInfiniteArray(a, 7));
    }

    int bsInSortRotatedArray(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[low] < a[mid]) { // line 450 to 456 talks about left side if it is sorted
                if (key >= a[low] && key < a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // if right side is sorted
                if (key > a[mid] && key <= a[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    @Test(description = "Search an element in an sorted and rotated array")
    public void test11(){
        int a[] = {20,30,40,50,60,5,10};
        int key=10;
        int res = bsInSortRotatedArray(a, key);
        System.out.println(res==-1?"Element is not present":res);
    }

    int maxOfAll(int[] a){
        int max=0;
        for(int i=0;i<a.length;i++){
            max=Math.max(max,a[i]);
        }
        return max;
    }
    int sumofAll(int[] a){
        int sum=0;
        for(int i=0;i<a.length;i++){
            sum=sum+a[i];
        }
        return sum;
    }
    boolean isFeasible(int[] a, int k, int res){
        int student = 1;
        int sum=0;
        for(int i=0;i<a.length;i++){
            if(sum+a[i]>res){
                student++;
                sum=a[i];
            } else{
                sum=sum+a[i];
            }
        }
        return student<=k;
    }
    int minPages(int[] a, int k){
        int res=0;
        int min= maxOfAll(a);
        int max= sumofAll(a);
        while(min<=max){
            int mid = (min+max)/2;
            if(isFeasible(a,k,mid)){
               res=mid;
               max=mid-1;
            } else{
                min=mid+1;
            }
        }
        return res;
    }
    @Test(description = "Allocate minimum number of maximum pages")
    public void test12(){
        int a [] = {12, 34, 67, 90};
        int k=2;
        System.out.println(minPages(a,k));
    }

    /**
     * Hashing concept
     */
    int distinctElement(int[] a){
        int res = 1;
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int j = 0;
            for (j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    break;
                }
            }
            if (i == j) {
                res++;
            }
        }
        return res;
    }

    int distinctElementHashingWay(int[] a){
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<a.length;i++){
            set.add(a[i]);
        }
        return set.size();
    }
    @Test(description = "count distinct elements")
    public void test13() {
        //using brute force
        int[] arr = {5, 10, 5, 4, 5, 10};
        System.out.println(distinctElement(arr));
        System.out.println(distinctElementHashingWay(arr));
    }

    int unionUsingSet(int[] a, int[] b){
        Set<Integer> set = new HashSet<>();
        for(int x : a){
            set.add(x);
        }
        for(int y : b){
            set.add(y);
        }
        for(int z : set){
            System.out.print(z+",");
        }
        return set.size();
    }

    int unionUsingBruteForce(int a[], int [] b){
        List lst = new ArrayList();
        for(int x:a){
            if(!lst.contains(x)){
               lst.add(x);
            }
        }
        for(int y:b){
            if(!lst.contains(y)){
                lst.add(y);
            }
        }
        for(int i=0;i<lst.size();i++){
            System.out.print(lst.get(i)+",");
        }
        return lst.size();
    }

    @Test(description = "print union of two arrays")
    public void test14(){
        int [] a= {5,2,5,10,6};
        int [] b= {3,5,10,4};
        System.out.println("\n"+unionUsingSet(a,b));
        System.out.println("\n"+unionUsingBruteForce(a,b));
    }

    void printUnion(int[] a, int[] b, int m, int n){
        int i=0;
        int j=0;
        while(i<m && j<n){
            if(a[i]<b[j]){
                System.out.print(a[i++]+",");
            } else if(a[i]>b[j]){
                System.out.print(b[j++]+",");
            } else{
                System.out.print(a[i++]+",");
                j++;
            }
        }
        while(i<m){
            System.out.print(a[i++]+",");
        }
        while(j<n){
            System.out.print(b[j++]+",");
        }
    }
    @Test(description = "print union of two sorted arrays")
    public void test15(){
        int arr1[] = { 1, 2, 4, 5, 6 };
        int arr2[] = { 2, 3, 5, 7 };
        int m = arr1.length;
        int n = arr2.length;
        printUnion(arr1, arr2, m, n);
    }

    void intersectionOfTwoArraysBrute(int[] a, int[] b, int m, int n) {
        int i = 0;
        int j = 0;
        boolean intersection = false;
        while (i < m && j < n) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                System.out.print(a[i] + ",");
                i++;
                j++;
                intersection = true;
            }
        }
        System.out.println(intersection ? "" : "no intersections present");
    }
    @Test(description = "intersection of two sorted arrays")
    public void test16(){
        int arr1[] = { 1, 3, 4, 5, 7 };
        int arr2[] = { 2, 0, 2, 9 };
        int m = arr1.length;
        int n = arr2.length;
        intersectionOfTwoArraysBrute(arr1,arr2,m,n);
    }

    /**
     * HashMap
     */
    void bruteForceSubArrayFind(int[] a, int sum) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int totalSum = 0;
            totalSum = totalSum + a[i];
            for (int j = i + 1; j < n - 1; j++) {
                totalSum = totalSum + a[j];
                if (totalSum <= sum) {
                    if (totalSum == sum) {
                        System.out.println(i + "," + j);
                        return;
                    }
                } else {
                    break;
                }
            }
        }
    }

    void subArrayUsingHashMap(int[] a, int sum){
        int currSum=0;
        int start =0;
        int end =-1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<a.length;i++){
            currSum=currSum+a[i];
            //for handling special case where the sum is present from 0th index
            if(currSum==sum){
                start=0;
                end=i;
                break;
            }
            if(map.containsKey(currSum-sum)){
                start=map.get(currSum-sum)+1;
                end=i;
                break;
            }
            map.put(currSum,i);
        }
        if(end==-1){
            System.out.println("no subarray found");
        } else{
            System.out.println(start+","+end);
        }
    }

    void largestSubArray(int[] a, int sum){
        int currSum=0;
        int start =0;
        int end = -1;
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            currSum = currSum + a[i];
            //for handling special case where the sum is present from 0th index
            if (currSum == sum) {
                start = 0;
                end = i;
                break;
            }
            if (map.containsKey(currSum - sum)) {
                if (maxLen < (i - map.get(currSum - sum))) {
                    maxLen = i - map.get(currSum - sum);
                    start = map.get(currSum - sum) + 1;
                    end = i;
                }
            }
            map.put(currSum, i);
        }
        if (end == -1) {
            System.out.println("no subarray found");
        } else {
            System.out.println(start+","+end);
            System.out.println("maximum length is ::: "+maxLen);
        }
    }

    void smallestSubArray(int[] a, int sum){
        int currSum=0;
        int start =0;
        int end = -1;
        int maxLen = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            currSum = currSum + a[i];
            //for handling special case where the sum is present from 0th index
            if (currSum == sum) {
                start = 0;
                end = i;
                break;
            }
            if (map.containsKey(currSum - sum)) {
                if (maxLen > (i - map.get(currSum - sum))) {
                    maxLen = i - map.get(currSum - sum);
                    start = map.get(currSum - sum) + 1;
                    end = i;
                }
            }
            map.put(currSum, i);
        }
        if (end == -1) {
            System.out.println("no subarray found");
        } else {
            System.out.println(start+","+end);
            System.out.println("minimum length is ::: "+maxLen);
        }
    }
    @Test(description = "find sub array equal to sum")
    public void test17(){
        int[] a = {1, 4, 20, 3, 10, 5};
        int sum=33;
        bruteForceSubArrayFind(a, sum);
        subArrayUsingHashMap(a,sum);

        //largest sub array equal to sum
        int[] b = {1,4,4,2,1,4,6};
        sum=10;
        largestSubArray(b,sum);

        //smalled sub array equal to sum
        smallestSubArray(b,sum);
    }

    void largestSubArrayHavingOneZero(int[] a){
        for(int i=0;i<a.length;i++){
            if(a[i]==0){
                a[i]=-1;
            }
        }
        int sum=0;
        int currSum=0;
        int start =0;
        int end = -1;
        int maxLen = 0;
        boolean fromZeroIndex=false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            currSum = currSum + a[i];
            //for handling special case where the sum is present from 0th index
            if (currSum == sum) {
                start = 0;
                end = i;
                fromZeroIndex=true;
            }
            if (map.containsKey(currSum - sum) && fromZeroIndex==false) {
                if (maxLen < (i - map.get(currSum - sum))) {
                    maxLen = i - map.get(currSum - sum);
                    start = map.get(currSum - sum) + 1;
                    end = i;
                }
            }
            if(!map.containsKey(currSum)) {
                map.put(currSum, i);
            }
        }
        if (end == -1) {
            System.out.println("no subarray found");
        } else {
            System.out.println(start+","+end);
            System.out.println("maximum length is ::: "+(end-start+1));
        }
    }
    @Test(description = "largest subarray with equal no of 1 and 0")
    public void test18(){
        int[] a = {1,1,0,1,1,0,0};
        largestSubArrayHavingOneZero(a);
        int[] b= {1, 0, 0, 1, 0, 1, 1 };
        largestSubArrayHavingOneZero(b);
        int[] c = {0, 0, 1, 1, 0};
        largestSubArrayHavingOneZero(c);
        int[] d = {1, 1, 1, 1};
        largestSubArrayHavingOneZero(d);
    }

    void distinctElementsInWindowBruteForce(int[] a, int k){
        int n=a.length;
        Set<Integer> set= new HashSet<>();
        for(int i=0;i<=n-k;i++){
            set.clear();
            for(int j=i;j<i+k;j++){
               set.add(a[j]);
            }
            System.out.print(set.size()+",");
        }
    }

    void distinctElementInWindowOfKUsingMap(int[] a, int k){
        int n=a.length;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<k;i++){
            map.put(a[i], map.getOrDefault(a[i],0)+1);
        }
        System.out.print(map.size()+",");

        for(int i=k;i<n;i++){
            if(map.get(a[i-k])==1){
                map.remove(a[i-k]);
            } else{
                map.put(a[i-k], map.get(a[i-k])-1);
            }
            map.put(a[i], map.getOrDefault(a[i], 0)+1);
            System.out.print(map.size()+",");
        }
    }
    @Test(description = "count the distinct elements in a window of k")
    public void test19(){
        int[] a = {1,2,2,1,3,1,1,3};
        int k=4;
        distinctElementsInWindowBruteForce(a, k);
        System.out.println();
        distinctElementInWindowOfKUsingMap(a, k);
    }

    void missingElements(int[] a, int low, int high){
        int ele = low;
        for(int i=0;i<=high-low &&  ele<=high;i++){
            boolean found=false;
            for(int j=0;j<a.length;j++){
                if(ele==a[j]){
                    found=true;
                    break;
                }
            }
            if(!found){
                System.out.print(ele+",");
            }
            ele++;
        }
    }

    void usingHashSetForFindingMissingEle(int[] a, int low, int high){
        HashSet<Integer> hash = new HashSet<>();
        for(int x:a){
            hash.add(x);
        }
        for(int i=low; i<=high;i++){
            if(!hash.contains(i)){
                System.out.print(i +",");
            }
        }
    }


    @Test(description = "find missing element in the range")
    public void test20(){
        int a [] = {1, 14, 11, 51, 15};
        int low=50;
        int high=55;
//        int a[] = {10, 12, 11, 15};
//        int low=10;
//        int high=15;

        //using brute force
        missingElements(a, low, high);
        //using hashing
        System.out.println();
        usingHashSetForFindingMissingEle(a,low,high);
    }

    void longestConsSubsequence(int[] a){
        Arrays.sort(a);
        int count=1;
        int ans=0;
        for(int i=0;i<a.length;i++){
            int e1=a[i];
            count=1;
            for(int j=i+1;j<a.length-1;j++){
                e1=e1+1;
                if(a[j]==e1){
                    count++;
                } else{
                    i=j-1;
                    break;
                }
            }
            ans = Math.max(ans,count);
        }
        System.out.println("longest consecutive subsequence is ::: "+ans);
    }

    void hashsetSubSequence(int[] a) {
        HashSet<Integer> S = new HashSet<Integer>();
        int ans = 0;

        // Hash all the array elements
        for (int i = 0; i < a.length; ++i)
            S.add(a[i]);

        for (int i = 0; i < a.length; ++i) {
            if (!S.contains(a[i] - 1)) {
                int j = a[i];
                while (S.contains(j))
                    j++;
                if (ans < j - a[i])
                    ans = j - a[i];
            }
        }
        System.out.println(ans);
    }
    @Test(description = "Longest Consecutive Subsequence")
    public void test21(){
        int a [] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        //Brute force : O(N^2)
        longestConsSubsequence(a);
        // Using hashset
        int a1 [] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        hashsetSubSequence(a1);
    }

    boolean checkPairs(int[] a, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : a) {
            // put remainder as key and their occurrence as value
            int remainder = x % k;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        if ((a.length) % 2 != 0) {
            return false;
        }

        for (int x : a) {
            int remainder = x % k;
            if (remainder == 0) {
                if (map.get(remainder) % 2 != 0) {
                    return false;
                }
            } else if (2 * remainder == k) {
                if (map.get(remainder) % 2 != 0) {
                    return false;
                }
            } else {
                int otherRemainder = k - remainder;
                if (map.get(remainder) != map.getOrDefault(otherRemainder, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test(description = "Check if an array can be divided into pairs whose sum is divisible by k")
    public void test22() {
        int[] a = {92, 75, 65, 48, 45, 35};
        int k = 10;
        int[] a1 = {91, 74, 66, 48};
        System.out.println(checkPairs(a, k));
        System.out.println(checkPairs(a1,k));
    }

    boolean isColorful(int no) {
        String s = String.valueOf(no);
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<s.length();i++){
            int prod = 1;
            for(int j=i;j<s.length();j++){
                prod=prod*(s.charAt(j)-'0');
                if(set.contains(prod)){
                    return false;
                } else{
                    set.add(prod);
                }
            }
        }
        return true;
    }
    @Test(description = "Colorful Nos: ")
    public void test23(){
        /**
         * A number can be broken into different contiguous sub-subsequence parts.
         * And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different
         */
        int i=3245;
        System.out.println(isColorful(i));
    }

    void zeroSum(int[] a) {
        int start = 0;
        int end = -1;
        int sum = 0;
        int currSum = 0;
        int maxLen = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            currSum = currSum + a[i];
            if (currSum == sum) {
                start = 0;
                end = i;
                maxLen = i + 1;
            }

            if (map.containsKey(currSum - sum)) {
                if (maxLen < (i - map.get(currSum - sum))) {
                    maxLen = i - map.get(currSum - sum);
                    start = map.get(currSum - sum) + 1;
                    end = i;
                }
            }
            if (!map.containsKey(currSum)) {
                map.put(currSum, i);
            }
        }

        int[] out = new int[end - start + 1];
        int k = 0;
        for (int i = start; i <= end; i++) {
            out[k] = a[i];
            System.out.print(a[i] + ",");
            k++;
        }
    }
    @Test(description = "Largest Continuous Sequence Zero Sum")
    public void test24(){
        //int[] a= {1 ,2 ,-2 ,4 ,-4};
        //int [] a = { -1, 1, 1, -1, -1, 1, 1, -1 };
        int[] a = {15, -25, 10, -8, -15, 14, -11, 12, 25, 26, -15, -23, -7, -7, 20, 21, -7, -15};
        zeroSum(a);
    }


    public int solve(int[] a) {
        int start=0;
        int end =-1;
        int maxLen=0;
        int sum=1;
        int currSum=0;
        int zeroCount=0;
        int oneCount=0;
        HashMap<Integer,Integer> map = new HashMap();
        boolean flag=false;

        for(int i=0;i<a.length;i++){
            if(a[i]==0){
                a[i]=-1;
                zeroCount++;
            } else{
                oneCount++;
            }
        }

        if(zeroCount-oneCount==1){
            return 1;
        }
        for(int i=0;i<a.length;i++){
            if(i==2089){
                System.out.println();
            }
            currSum=currSum+a[i];
            if(currSum==sum){
                start=Math.max(start,0);
                end=i;
                maxLen=i+1;
            }

            if(map.containsKey(map.get(currSum-sum))){
                if(maxLen<(i-map.get(currSum-sum))){
                    maxLen=i-map.get(currSum-sum);
                    start=map.get(currSum-sum)+1;
                    end=i;
                }
            }
            if(!map.containsKey(currSum)){
                map.put(currSum,i);
            }
        }
        System.out.println(start+","+end);
        return maxLen;
    }
    @Test(description = "Longest Subarray Length having one extra one")
    public void test25(){
        //int [] a = {0, 1, 1, 0, 0, 1};
        //int [] a = {0, 1, 0 };
        //int [] a = {1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        int [] a = {0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1 };
        System.out.println(solve(a));
    }

    /**
     * https://www.interviewbit.com/courses/programming/topics/hashing/#problems
     */
    @Test()
    public void test26() {
        int[] A = {10, 5, 3, 4, 3, 5, 6};

        HashMap<Integer, Integer> map = new HashMap();
        HashMap<Integer, Integer> index = new HashMap();
        boolean dup = false;
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
                index.put(A[i], Math.min(index.get(A[i]), i));
                dup = true;

            } else {
                map.put(A[i], 1);
                index.put(A[i], i);
            }
        }

        if (!dup) {
            System.out.println(-1);

        }

        int ind = 100;
        for (int i = 0; i < A.length; i++) {
            if (map.get(A[i]) > 1) {
                ind = Math.min(ind, i);
            }
        }
        System.out.println(A[ind]);
    }

}
