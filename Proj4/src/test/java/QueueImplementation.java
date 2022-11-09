import org.testng.IDataProviderListener;
import org.testng.annotations.Test;

import java.util.*;

public class QueueImplementation {


    static class Node{
        int data;
        Node next,down;

        Node(int data){
            this.data= data;
        }
    }

    void convertList(Node head) {
        Node cur = head;
        Queue<Node> q = new ArrayDeque<>();

        while (cur != null) {
            if (cur.down != null) { // if there is child reference present then add child reference to queue.
                q.add(cur.down);
            }

            if (cur.next == null) { // if there is no element present after current element then poll from queue to get child ref.
                cur.next = q.poll();
            }

            System.out.print(cur.data+","); // print if next of current is not null and make next element as cur.
            cur = cur.next;
        }
    }

    public static void printOriginalList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(" " + head.data + " ");

        if (head.down != null) {
            System.out.print("[");
            printOriginalList(head.down);
            System.out.print("]");
        }

        printOriginalList(head.next);
    }

    void convertListUsingLL(Node head) {
        Node tmp = null;
        if (head == null) {
            return;
        }
        Node last = head;
        while (last.next != null) {
            //System.out.print(last.data + ",");
            last = last.next;
        }
        Node cur = head;
        System.out.print(cur.data + ",");
        while (cur != last) {
            if (cur.down != null) {
                last.next = cur.down;

                tmp = cur.down;
                while (tmp.next != null) {
                    //System.out.print(tmp.data+",");
                    tmp = tmp.next;
                }
                last = tmp;
            }

            cur = cur.next;
            System.out.print(cur.data + ",");
        }
    }

    @Test(description = "Flatten a linked list : Using Queue O(N) complexity")
    public void test(){
        // create individual nodes and link them together later
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);
        Node twelve = new Node(12);
        Node thirteen = new Node(13);
        Node fourteen = new Node(14);
        Node fifteen = new Node(15);
        Node sixteen = new Node(16);
        Node seventeen = new Node(17);

        // set head node
        Node head = one;

        // set next pointers
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        six.next = seven;
        seven.next = eight;
        nine.next = ten;
        thirteen.next=fourteen;
        sixteen.next=seventeen;

        // set down pointers
        one.down = six;
        four.down = nine;
        seven.down = eleven;
        eight.down = twelve;
        nine.down = thirteen;
        twelve.down = fifteen;
        thirteen.down=sixteen;

        System.out.println("The original list is :");
        printOriginalList(head);

        System.out.println("*****************************");
        System.out.println("\n\nThe flattened list is : Using queue is ");
        //convertList(head); // Using queue

        System.out.println("\n\nThe flattened list is : Using linked list is ");
        convertListUsingLL(head);

    }

    void slidingWindowBruteForce(int[] a, int k) {
        for (int i = 0; i < a.length - k + 1; i++) {
            int max = a[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, a[j]);
            }
            System.out.print(max+",");
        }
    }

    void slidingWindowPriorityQueue(int [] a, int k){
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> res = new ArrayList<>();

        int i=0;
        for(;i<k;i++){
            q.add(a[i]);
        }
        //res.add(q.peek());
        System.out.print(q.peek()+",");
        q.remove(a[0]);

        for(;i<a.length;i++){
            q.add(a[i]);
            //res.add(q.peek());
            System.out.print(q.peek()+",");
            q.remove(a[i-k+1]);
        }
    }

    void slidingWindowDeque(int[] a,int k){
        Deque<Integer> q = new ArrayDeque<>();

        int i;
        for(i=0;i<k;++i){
            while(!q.isEmpty() && a[i]>=a[q.peekLast()]){
                q.removeLast();
            }
            q.addLast(i);
        }

        for(;i<a.length;++i){
            System.out.println(a[q.peek()]+",");

        }
    }

    @Test(description = "Sliding window")
    public void test1() {

        int[] a ={4,1,3,5,1,2,3,2,1,1,5};
        int k = 4;
        slidingWindowBruteForce(a,k); // Time complexity - O(n*k) and space complexity - O(1)
        System.out.println();
        slidingWindowPriorityQueue(a,k); // Time complexity - O(n*LogK) and space complexity - O(k)
        System.out.println();
        slidingWindowDeque(a,k); // Time complexity - O(n) and space complexity - O(k)
    }


    @Test(description = "description")
    public void test2(){

    }

}
