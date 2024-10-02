package phase2;

import org.testng.annotations.Test;
import java.util.*;

public class QueuePractice {


    Stack<Integer> s1=new Stack<>();
    Stack<Integer> s2=new Stack<>();

    void enqueue(int data){
        s1.push(data);
    }

    int dequeue(){
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        int res=s2.pop();
        while (!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return res;
    }

    Queue<Integer> q1= new ArrayDeque<>();
    Queue<Integer> q2= new ArrayDeque<>();

    void push(int data){
        while (!q1.isEmpty()){
            q2.offer(q1.poll());
        }
        q1.offer(data);
        while (!q2.isEmpty()){
            q1.offer(q2.poll());
        }
    }

    int pop(){
        return q1.poll();
    }

    @Test(description = "Elements fetching technique should match stack property : LIFO")
    public void queueToStack() {
        push(1);
        push(2);
        push(3);
        push(4);

        for(int i=0;i<4;i++) {
            System.out.print(pop() + ",");
        }
    }

    static class Node {
        int data;
        Node next, down;

        Node(int data) {
            this.data = data;
        }
    }
    @Test(description = "Elements fetching technique should match queue property : FIFO")
    public void stackToQueue(){
        enqueue(1);
        enqueue(2);
        enqueue(3);
        enqueue(4);

        for(int i=0;i<4;i++) {
            System.out.print(dequeue() + ",");
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

    void convertList(Node head) {
        Queue<Node> q = new ArrayDeque<>();
        Node cur = head;

        while (cur != null) {
            if (cur.next == null) {
                cur.next = q.poll();
            }

            if (cur.down != null) {
                q.offer(cur.down);
            }
            System.out.print(cur.data + ",");
            cur = cur.next;
        }
        System.out.print("null");
    }

    @Test
    public void flattenALinkedList(){
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
        //convertList1(head);
        convertListUsingLL(head);
    }

    void convertListUsingLL(Node head) {
        Node last = head;

        while (last.next != null) {
            last = last.next;
        }
        Node cur = head;
        System.out.print(cur.data + ",");

        while (cur != last) {
            if (cur.down != null) {
                last.next = cur.down;

                Node tmp = cur.down;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                last = tmp;
            }
            cur = cur.next;
            System.out.print(cur.data + ",");
        }

    }

    void convertList1(Node head){
        Queue<Node> q = new ArrayDeque<>();
        Node cur = head;
        while(cur!=null){
            if(cur.next==null){
                cur.next=q.poll();
            }
            if(cur.down!=null){
                q.offer(cur.down);
            }

            System.out.print(cur.data);
            cur=cur.next;
        }
    }
}

