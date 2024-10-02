package phase2;

import org.testng.annotations.Test;

import java.util.*;

public class Heap {
    static void printArray(int arr[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static void swap(int[] a, int i, int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    @Test(description = "insert a node")
    public void test2(){
        int arr[] = {50,30,40,10,5,20,30};
        int n = arr.length;
        int key=60;
        arr=insertNode(arr, n, key);
        printArray(arr, n+1);
    }

    @Test(description = "insert a node")
    public void test21(){
        int arr[] = {50,30,40,10,5,20,30};
        int n = arr.length;
        int key=60;
        arr=insertNode1(arr, n, key);
        printArray(arr, n+1);
    }

    @Test(description = "delete a node")
    public void test22(){
        int arr[] = {50,30,40,10,5,20,30};
        int n = arr.length;
        arr=deleteNode(arr, n);
        printArray(arr, n-1);
    }

    void delete(int[] arr, int n, int i){
        int largest=i;
        int left=2*i+1;
        int right=2*i+2;

        if(left<n && arr[left]>arr[largest]){
            largest=left;
        }
        if(right<n&& arr[right]>arr[largest]){
            largest=right;
        }

        if(largest!=i){
            swap(arr,largest,i);
            delete(arr,n,largest);
        }
    }
    int[] deleteNode(int[] arr, int n){
        arr[0]=arr[n-1];
        n=n-1;
        delete(arr,n,0);
        return arr;
    }

    void insert(int[] arr, int n, int i){
        int parent= (i-1)/2;
        if(arr[parent]>0){
            if(arr[i]>arr[parent]){
                int tmp=arr[i];
                arr[i]=arr[parent];
                arr[parent]=tmp;
                insert(arr,n,parent);
            }
        }
    }
    int[] insertNode1(int[] arr, int n, int key){
        n=n+1;
        arr=Arrays.copyOf(arr,n);
        arr[n-1] = key;
        insert(arr,n,n-1);
        return arr;
    }

    int[] insertNode(int[] arr, int n, int key){
        n=n+1;
        arr= Arrays.copyOf(arr,n);
        arr[n-1]=key;
        int i=arr.length-1;
        while(i>0){
            int parent=(i-1)/2;
            if(arr[i]>arr[parent]){
                swap(arr, i, parent);
                i=parent;
            }
        }
        return arr;
    }

    @Test(description = "heapify a node")
    public void heapifyNodes(){
        int arr[] = {20,10,30,5,50,40};
        int n = arr.length;
        arr=heapify(arr, n);
        printArray(arr, n);
    }

    void heapifyIt(int[] arr, int n, int i){
        int largest=i;
        int left=i*2+1;
        int right=i*2+2;
        if(left<n && arr[left]>arr[largest]){
            largest=left;
        }
        if(right<n&& arr[right]>arr[largest]){
            largest=right;
        }

        if(largest!=i){
            swap(arr,largest,i);
            heapifyIt(arr,n,largest);
        }
    }
    int[] heapify(int[] arr, int n){
        for(int i=(n-1)/2;i>=0;i--){
            heapifyIt(arr,n,i);
        }
        return arr;
    }


    @Test(description = "delete a node")
    public void heapifySort(){
        int arr[] = {20,10,30,5,50,40};
        int n = arr.length;
        arr=heapifySorting(arr, n);
        printArray(arr, n);
    }

    int[] heapifySorting(int[]arr, int n){
        heapify(arr,n);
        for(int i=n-1;i>0;i--){
            swap(arr,i,0);
            heapify(arr,i);
        }

        return arr;
    }

    @Test(description = "K'th largest element in an array")
    public void test5() {
        int[] a = {20, 10, 60, 30, 50, 40};
        int k = 3;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(a[i]);
        }
        for(int i=k;i<a.length;i++){
            if(a[i]>pq.peek()){
                pq.poll();
                pq.add(a[i]);
            }
        }
        System.out.println(pq.peek());
    }

    @Test(description = "connect N ropes with minimum cost")
    public void test7() {
        int[] a = {2, 5, 4, 8, 6, 9};
        int n = a.length;
        int cost = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            pq.add(a[i]);
        }

        while(pq.size()>1){
            int first=pq.poll();
            int sec=pq.poll();
            int sum=first+sec;
            cost+=sum;
            pq.add(sum);
        }
        System.out.println(cost);
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    void insertNum(int num){
        if(maxHeap.isEmpty() || num<=maxHeap.peek()){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if(maxHeap.size() > minHeap.size()+1){
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size()<minHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }

    double findMedian(){
        if(maxHeap.size()==minHeap.size()){
            return maxHeap.peek()/2.0+minHeap.peek()/2.0;
        } else{
            return maxHeap.peek();
        }
    }

    @Test(description = "Find the median of a number stream")
    public void test8(){
        //https://www.youtube.com/watch?v=rzA7UJ-hQn4
        insertNum(3);
        insertNum(1);
        System.out.println("median is ::: "+findMedian());
        insertNum(5);
        System.out.println("median is ::: "+findMedian());
        insertNum(4);
        System.out.println("median is ::: "+findMedian());
        insertNum(7);
    }

    @Test(description = "sort a nearly sorted array")
    public void test9(){
        int k = 3;
        int arr[] = { 2, 6, 3, 12, 56, 8 };
        int n = arr.length;

        // function call
        kSort(arr, n, k);
        printArray(arr, n);
    }

    void kSort(int[]arr, int n, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size=Math.min(n,k+1);
        for(int i=0;i<size;i++){
            pq.add(arr[i]);
        }

        int index=0;
        for(int i=k+1;i<n;i++){
            arr[index++]=pq.peek();
            pq.poll();
            pq.add(arr[i]);
        }

        Iterator<Integer> it = pq.iterator();
        while (it.hasNext()){
            arr[index++]=pq.peek();
            pq.poll();
        }
    }

    @Test
    public void  smaller(){
        int x = 80;
        int arr[] = { 2,3,15,5,4,45,80,6,150,77,120 };
        printSmallerThan(arr, x, 0);
    }
    void printSmallerThan(int[] arr, int x, int pos) {
        if(pos>=arr.length) return;
        if(arr[pos]>=x) return;

        System.out.print(arr[pos]+",");
        printSmallerThan(arr,x,(2*pos+1));
        printSmallerThan(arr,x,(2*pos+2));
    }

    @Test
    public void distinct(){
//        int arr[] = { 5, 7, 5, 5, 1, 2, 2 };
//        int k=3;
//        int arr[] = {1, 2, 3, 4, 5, 6, 7};
//        int k=5;
//        int arr[]= {1, 2, 2, 2};
//        int k=1;
//        int arr[]={32,9,55,9,22,35,9,35};
//        int k=3;
        int arr[]={35,36,32,55,50,9};
        int k=4;
        int n = arr.length;
        System.out.println("Maximum distinct elements = " + maxDistinctNum(arr, n, k));
    }

    int maxDistinctNum(int[] arr, int n, int k) {
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            if (it.getValue() == 1) {
                result++;
            } else {
                pq.add(it.getValue());
            }
        }

        while (k > 0 && !pq.isEmpty()) {
            int t = pq.poll();
            if (t == 1) result++;
            else {
                t--;
                k--;
                pq.add(t);
            }
        }
        if (!pq.isEmpty() && pq.peek() == 1) result++;
        return (k <= 0) ? result : result - k;
    }


    @Test
    public void testMaxSum(){
        int A[] = { 4, 2, 5, 1 };
        int B[] = { 8, 0, 5, 3 };
        int N = A.length;
        int K = 3;
        KMaxCombinations(A, B, N, K);
    }

    void KMaxCombinations(int[] A, int[] B, int N, int K){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                pq.add(A[i]+B[j]);
            }
        }

        while(K>0){
            System.out.println(pq.peek());
            pq.poll();
            K--;
        }
    }

    @Test
    public void testKthLargest() {
//        int arr[] = {1, 2, 3, 4, 5, 6};
//        int k = 4;

        int arr[] = {10, 20, 11, 70, 50, 40, 100, 5};
        int k = 3;
        int n=arr.length;
        kthLargest(k, arr, n);
    }

    void kthLargest(int k, int[] arr, int n){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }

        for(int i=0;i<k;i++){
            System.out.print((i<k-1)?-1+",":pq.peek()+",");
        }
        for(int i=k;i<n;i++){
            if(arr[i]>pq.peek()){
                pq.poll();
                pq.add(arr[i]);
            }
            System.out.print(pq.peek()+",");
        }
    }

    @Test(description = "largest sum contiguous subbarray")
    public void largestContSubArray() {
        int arr[] = {20, -5, 1, 9};
        int k = 3;

        int curSum = 0;
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            curSum = curSum + arr[i];
            if (curSum > maxSum) {
                maxSum = curSum;
            }
            if (curSum < 0) {
                curSum = 0;
            }
        }

        System.out.println("largest contiguous subarray is :: "+maxSum);
    }

    static int kthLargestSum(int arr[], int N, int K) {
        int sum[] = new int[N + 1];
        sum[0] = 0;
        sum[1] = arr[0];
        for (int i = 2; i <= N; i++)
            sum[i] = sum[i - 1] + arr[i - 1];

        PriorityQueue<Integer> Q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                int x = sum[j] - sum[i - 1];

                if (Q.size() < K)
                    Q.add(x);
                else {
                    if (Q.peek() < x) {
                        Q.poll();
                        Q.add(x);
                    }
                }
            }
        }
        return Q.poll();
    }

    @Test(description = "Kth largest sum contiguous subarray")
    public void kthLargestContSubArray(){
        int a[] = new int[] { 20,-5,-1 };
        int N = a.length;
        int K = 3;

        // Function call
        System.out.println(kthLargestSum(a, N, K));
    }

    @Test
    public void largestTriplet(){
        int arr[] = { 1, 2, 3, 4, 5 };
        int n = arr.length;
        LargestTripletMultiplication(arr, n);
    }

    void LargestTripletMultiplication(int[] arr, int n){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<n;i++) {
            pq.add(arr[i]);
            if(pq.size()<3){
                System.out.print("-1,");
            } else {
                int first = pq.poll();
                int sec = pq.poll();
                int third = pq.poll();
                int prod = first * sec * third;
                System.out.print(prod + ",");
                pq.add(first);
                pq.add(sec);
                pq.add(third);
            }
        }
    }




}
