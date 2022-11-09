import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

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

    static int deleteRoot(int arr[], int n) {
        arr[0] = arr[n - 1];
        n = n - 1;
        int i = 0;
        int left = 0;
        int right = 0;
        while (i < n && left < n && right < n) {
            left = 2 * i + 1; // left = 2*i + 1
            right = 2 * i + 2; // right = 2*i + 2

            int largest = arr[left] > arr[right] ? left : right;
            if (arr[i] < arr[largest]) {
                swap(arr, i, largest);
                i = largest;
            }
        }
        return n;
    }

    @Test(description = "delete a node")
    public void test1() {
        //int arr[] = {10, 5, 3, 2, 4};
        int arr[] = {40,30,10,20,15};
        int n = arr.length;
        n = deleteRoot(arr, n);
        printArray(arr, n);
    }

    int[] insertNode(int[] arr, int arrayLength, int key){
        arrayLength=arrayLength+1;
        arr= Arrays.copyOf(arr, arrayLength);
        arr[arrayLength-1]=key;
        int i=arrayLength-1;
        while(i>0){
            int parent = i/2;
            if(arr[parent]<arr[i]){
                swap(arr, parent, i);
                i=parent;
            }
        }
        return arr;
    }
    @Test(description = "insert a node")
    public void test2(){
        int arr[] = {50,30,40,10,5,20,30};
        int n = arr.length;
        int key=60;
        arr = insertNode(arr, n, key);
        printArray(arr, n+1);
    }

    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[largest] < arr[left]) {
            largest = left;
        }
        if (right < n && arr[largest] < arr[right]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    void buildHeap(int[] arr, int n){
        for(int i=(n-1)/2;i>=0;i--){
            heapify(arr,n,i);
        }
    }
    @Test(description = "Heapify")
    public void test3(){
        int arr[] = {20,10,30,5,50,40};
        int n = arr.length;
        buildHeap(arr, n);
        printArray(arr, n);
    }

    void heapifySort(int[] arr, int n){
        buildHeap(arr, n);
        for(int i=n-1;i>0;i--){
            swap(arr, 0, i);
            buildHeap(arr,i);
        }
    }
    @Test(description = "Heapify sort algo")
    public void test4(){
        int arr[] = {40,10,30,50,60,15};
        //int arr[] = { 12, 11, 13, 5, 6, 7 };
        int n = arr.length;
        heapifySort(arr, n);
        printArray(arr, n);
    }

    @Test(description = "K'th largest element in an array")
    public void test5() {
        int[] a = {20, 10, 60, 30, 50, 40};
        int k = 3;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(a[i]);
        }

        for (int i = k; i < a.length; i++) {
            if (pq.peek() < a[i]) {
                pq.poll();
                pq.add(a[i]);
            }
        }
        System.out.println("Largest k'th element is ::: "+pq.peek());
    }

    @Test(description = "K'th Minimum element in an array")
    public void test6() {
        int[] a = {20, 10, 60, 30, 50, 40};
        int k = 3;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.add(a[i]);
        }

        for (int i = k; i < a.length; i++) {
            if (a[i]<pq.peek()) {
                pq.poll();
                pq.add(a[i]);
            }
        }
        System.out.println("Smallest k'th element is ::: "+pq.peek());
    }


    @Test(description = "connect N ropes with minimum cost")
    public void test7() {
        int[] a = {2, 5, 4, 8, 6, 9};
        int n = a.length;
        int cost = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(a[i]);
        }

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            cost += sum;
            pq.add(sum);
        }
        System.out.println("Minimum cost is ::: " + cost);
    }


    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    void insertNum(int num){
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if(maxHeap.size() > minHeap.size()+1){
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size()< minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    double findMedian(){
        if(maxHeap.size() == minHeap.size()){
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
    }


}
