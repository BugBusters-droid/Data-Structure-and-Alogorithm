import org.testng.annotations.Test;

import java.util.HashSet;

/**
 * https://www.geeksforgeeks.org/data-structures/linked-list/
 */

public class LinkedList {

    static Node head;
    static class Node {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    /*
    ============================== Basic operations for concept understanding =======================
     */

    @Test(description = "Concept understanding")
    public void testMain(){
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node head = n1;
        head.next = n2;
        n2.next=n3;
        n3.next=null;
    }

    void traverseData(Node head){
        Node cur = head;
        while(cur!=null){
            System.out.println(cur.data);
            cur=cur.next;
        }
    }

    void insertData(int data, Node head, int pos){
        Node toAdd = new Node(data);
        if(pos==0){
           toAdd.next =  head;
           head =  toAdd;
           return;
        }
        Node prev = head;
        for(int i=0;i<pos-1;i++){
            prev = prev.next;
        }
        toAdd.next = prev.next;
        prev.next = toAdd;
    }

    void deleteData(Node head, int pos){
        if(pos == 0){
            head = head.next;
            return;
        }

        Node prev = head;
        for(int i=0;i<pos-1;i++){
            prev=prev.next;
        }
        prev.next=prev.next.next;
    }
    @Test(description = "Traversing in linked list")
    public void testLinkedListOperations(){
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node head = n1;
        head.next = n2;
        n2.next=n3;
        n3.next=null;
        traverseData(head);
        insertData(25, head, 2);
        deleteData(head, 2);
        System.out.println();
    }


    void append(int data){
        Node toAdd = new Node(data);
        if(head==null){
            head=toAdd;
            return;
        }

        toAdd.next=null;
        Node last = head;
        while(last.next!=null){
             last = last.next;
        }
        last.next = toAdd;
        return;
    }

    void push(int data){
        Node toAdd = new Node(data);
        toAdd.next=head;
        head=toAdd;
    }

    void insertAfter(int data, Node prev){
        Node toAdd = new Node(data);
        if(prev==null){
            System.out.println("Prev cant be null");
            return;
        }
        toAdd.next=prev.next;
        prev.next=toAdd;
    }

    public void printList()
    {
        Node tnode = head;
        while (tnode != null)
        {
            System.out.print(tnode.data+" ");
            tnode = tnode.next;
        }
    }

    @Test(description = "Various ways of insertion")
    public void testInsertion(){
        LinkedList list = new LinkedList();
        // Insert 6.  So linked list becomes 6->NUllist
        list.append(6);
        // Insert 7 at the beginning. So linked list becomes
        // 7->6->NUllist
        list.push(7);
        // Insert 1 at the beginning. So linked list becomes
        // 1->7->6->NUllist
        list.push(1);
        // Insert 4 at the end. So linked list becomes
        // 1->7->6->4->NUllist
        list.append(4);
        // Insert 8, after 7. So linked list becomes
        // 1->7->8->6->4->NUllist
        list.insertAfter(8,list.head.next);
        System.out.println("\nCreated Linked list is: ");
        list.printList();
    }

    /*
    ============================ Programs =====================
     */

    int getCount() {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    int getCountRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getCountRecursive(node.next);
    }

    @Test(description = "Length of a linked list")
    public void testLength() {
        LinkedList list = new LinkedList();
        list.push(1);
        list.push(3);
        list.push(1);
        list.push(2);
        list.push(1);
        System.out.println("Count of nodes is " + list.getCount());
        System.out.println(list.getCountRecursive(head));
    }

    boolean isPresent(int data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            } else {
                temp = temp.next;
            }
        }
        return false;
    }

    boolean isPresentRecursive(Node head, int data) {
        if (head == null) {
            return false;
        }
        if (head.data == data) {
            return true;
        }
        return isPresentRecursive(head.next, data);
    }

    @Test(description = "Search an element in the linked list")
    public void testSearch(){
        LinkedList list = new LinkedList();
        /*Use push() to construct below list
        14->21->11->30->10  */
        list.push(10);
        list.push(30);
        list.push(11);
        list.push(21);
        list.push(14);

        System.out.println("Node is present : "+isPresent(20));
        System.out.println("Node is present : "+isPresent(14));
        System.out.println("Node is present : "+isPresent(10));

        System.out.println("Node is present : "+isPresentRecursive(head,20));
        System.out.println("Node is present : "+isPresentRecursive(head, 14));
        System.out.println("Node is present : "+isPresentRecursive(head, 10));
    }

    int getNodeData(int index) {
        int data = 0;
        if (head == null) {
            return 0;
        }
        Node cur = head;
        int count = 0;

        while (cur != null) {
            if (count == index) {
                return cur.data;
            }
            count++;
            cur = cur.next;
        }
        assert (false);
        return 0;
    }

    int getNodeDataFromEnd(int index){
       /*
       Calculate the length of the Linked List. Let the length be len.
       Print the (len – n + 1)th node from the beginning of the Linked List.
        */
        int len = 0;
        Node temp = head;
        while(temp!=null){
            temp=temp.next;
            len++;
        }

        if(len<index){return 0;}
        temp=head;
        for(int i=0;i<len-index+1;i++){
            temp=temp.next;
        }
        return temp.data;
    }
    @Test(description = "get Nth node in a Linked List")
    public void testGetNodeData(){
        LinkedList list = new LinkedList();
        list.push(14);
        list.push(30);
        list.push(10);
        list.push(1);

        int index=2;
        System.out.println(getNodeData(index)); // Nth node data from front of the linked list
        System.out.println(getNodeDataFromEnd(index)); // Nth node data from the end
    }

    void printMiddle(){
        Node cur = head;
        int len=0;
        while(cur!=null){
            cur=cur.next;
            len++;
        }
        cur=head;
        for(int i=0;i<len/2;i++){
           cur=cur.next;
        }
        System.out.println("The middle element is :: "+cur.data);
    }

    @Test(description = "Find the middle of a given linked list")
    public void getMiddleData(){
        LinkedList list = new LinkedList();
        for(int i=5;i>=1;i--){
            list.push(i);
            System.out.println();
            printList();
            System.out.println();
            printMiddle();
        }
    }

    int count(int num){
        int occurrence = 0;
        Node cur = head;
        while(cur!=null){
            if(cur.data==num){
                occurrence++;
            }
            cur=cur.next;
        }
        return occurrence;
    }

    int freq=0;
    int countRecursively(Node head, int num){
        if(head==null){
            return freq;
        }
        if(head.data==num){
            freq++;
        }
        return countRecursively(head.next,num);
    }
    @Test(description = "counts the number of times a given int occurs in a Linked List")
    public void countTheOccurrence(){
        LinkedList list = new LinkedList();
        list.push(1);
        list.push(2);
        list.push(1);
        list.push(3);
        list.push(1);

        /*Checking count function*/
        System.out.println("Count of 1 is " + list.count(1));
        System.out.println("Count of 1 is " + list.countRecursively(head,1));

    }


    void removeDuplicatesInSortedArrayRecursiveWay(Node head) {
        if (head == null) {
            return;
        }
        if (head.next != null) {
            if (head.data == head.next.data) {
                head.next = head.next.next;
                removeDuplicatesInSortedArrayRecursiveWay(head);
            } else {
                removeDuplicatesInSortedArrayRecursiveWay(head.next);
            }
        }
    }

    @Test(description = "Remove duplicates from a sorted linked list")
    public void removeDuplicates(){
        LinkedList llist = new LinkedList();
        llist.push(20);
        llist.push(13);
        llist.push(13);
        llist.push(11);
        llist.push(11);
        llist.push(11);
        System.out.println("List before removal of duplicates");
        llist.printList();

        llist.removeDuplicatesInSortedArrayRecursiveWay(head);

        System.out.println("List after removal of elements");
        llist.printList();
    }

    void removeDuplicatesInUnSortedArrayRecursiveWay(Node head){
        HashSet<Integer> set = new HashSet<>();

        Node cur = head;
        Node prev = null;
        while(cur!=null){
            int curVal = cur.data;
            if (set.contains(curVal)){
                prev.next=cur.next;
                cur=cur.next;
            } else {
                set.add(curVal);
                prev=cur;
                cur=cur.next;
            }
        }
    }
    @Test(description = "Remove duplicates from a unsorted linked list")
    public void removeDuplicatesUnSortedArray(){
        LinkedList llist = new LinkedList();
        llist.push(13);
        llist.push(20);
        llist.push(13);
        llist.push(11);
        llist.push(15);
        llist.push(11);
        System.out.println("List before removal of duplicates");
        llist.printList();

        llist.removeDuplicatesInUnSortedArrayRecursiveWay(head);
        System.out.println("List after removal of elements");
        llist.printList();
    }

    Node swapNodes(Node head, int x , int y){
        if (x == y)
            return null;

        // Search for x (keep track of prevX and CurrX)
        Node prevX = null;
        Node curX = head;
        while(curX!=null && curX.data!=x){
            prevX=curX;
            curX= curX.next;
        }
        Node prevY=null;
        Node curY=head;
        // Search for y (keep track of prevY and currY)
        while(curY!=null && curY.data!=y){
            prevY=curY;
            curY= curY.next;
        }

        // If either x or y is not present, nothing to do
        if(curX==null || curY==null){
            return null;
        }

        // If x is not head of linked list
        if(prevX!=null){
            prevX.next=curY;
        } else{
            head=curY;
        }

        // If y is not head of linked list
        if(prevY!=null){
           prevY.next=curX;
        } else{
            head=curX;
        }

        // Swap next pointers
        Node temp = curX.next;
        curX.next=curY.next;
        curY.next=temp;

        return head;
    }
    @Test(description = "Swap nodes in a linked list without swapping data")
    public void swapNodesFromLL() {
        LinkedList list = new LinkedList();
        list.push(14);
        list.push(20);
        list.push(13);
        list.push(12);
        list.push(15);
        list.push(10);
        System.out.println("List before swapping of nodes");
        list.printList();

        head = list.swapNodes(head, 12, 14);
        System.out.println("\nList After swapping of nodes");
        list.printList(head);
    }

    void swapPairNodes(Node head){
        Node cur = head;
        while(cur!=null && cur.next!=null){
            int k = cur.data;
            cur.data =  cur.next.data;
            cur.next.data = k;
            cur = cur.next.next;
        }
    }

    @Test(description = "Pairwise Swap Nodes of a given Linked List")
    public void pairSwap(){
        LinkedList list = new LinkedList();
        list.push(14);
        list.push(20);
        list.push(13);
        list.push(12);
        list.push(15);
        list.push(10);
        System.out.println("List before pair swapping of nodes");
        list.printList();

        list.swapPairNodes(head);
        System.out.println("\nList After swapping of nodes");
        list.printList(head);

    }

    void moveNodeToFront(){
        Node last = head;
        Node prev = null;

        if(head==null || head.next==null){
            return;
        }
        while(last.next!=null){
            prev=last;
            last=last.next;
        }
        prev.next=null;
        last.next = head;
        head=last;
    }
    @Test(description = "Move last element to front of a given Linked List")
    public void moveLastToFront(){
        LinkedList list = new LinkedList();
        list.push(14);
        list.push(20);
        list.push(13);
        list.push(12);
        list.push(15);
        list.push(10);
        System.out.println("List before pair swapping of nodes");
        list.printList();

        list.moveNodeToFront();
        System.out.println("\nList After swapping of nodes");
        list.printList(head);
    }

    Node reverse(Node node){
        Node cur = node;
        Node prev = null;

        while(cur!=null){
            Node next=cur.next;
            cur.next=prev; //Assigning reference of current element to prev reference (null) instead of next reference
            prev=cur;
            cur=next;
        }
        return prev;
    }

    void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    @Test(description = "reverse a linked list : Iterative approach")
    public void test1()
    {
        LinkedList list = new LinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(head);
        head = list.reverse(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(head);
    }

    Node recursiveReverse(Node node){
        if(node==null || node.next==null){
            return node;
        }

        Node newHead = recursiveReverse(node.next);
        Node headNext = node.next;
        headNext.next=node;
        node.next=null;
        return newHead;
    }

    @Test(description = "reverse a linked list : Recursive approach")
    public void test2()
    {
        LinkedList list = new LinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(head);
        head = list.recursiveReverse(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(head);
    }

    Node middle(Node node){
        Node slow = node;
        Node fast = node;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    boolean Palindrome(Node head){
        if(head==null){
            return true;
        }
        Node mid = middle(head);
        Node last = reverse(mid.next);

        Node cur = head;
        while(last!=null){
            if(last.data != cur.data){
                return false;
            }
            cur=cur.next;
            last=last.next;
        }
        return true;
    }

    public void push(char new_data)
    {
        /* Allocate the Node &
           Put in the data */
        Node new_node = new Node(new_data);

        /* link the old list off the new one */
        new_node.next = head;

        /* Move the head to point to new Node */
        head = new_node;
    }

    @Test(description = "Palindrome in linked list")
    public void test3(){
        LinkedList llist = new LinkedList();

        //char str[] = { 'a', 'b', 'a', 'c', 'a', 'b', 'b' };
        char str[] = { 'a', 'b', 'a', 'c', 'a', 'b', 'a' };

        for (int i = 0; i < 7; i++) {
            llist.push(str[i]);
        }
        System.out.println(llist.Palindrome(head));
    }


    Node detectCycle(Node head){
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                System.out.println("Loop found");
                return slow;
            }
        }
        System.out.println("Loop not found");
        return null;
    }

    Node detectFirstNode(Node head){
        Node meet = detectCycle(head);
        Node start = head;

        while(start!=meet){
            start=start.next;
            meet=meet.next;
        }
        return start;
    }
    public void push1(int new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }
    @Test(description = "detect cycle in linked list")
    public void test4(){
        LinkedList llist = new LinkedList();
        llist.push1(6);
        llist.push1(5);
        llist.push1(4);
        llist.push1(3);
        llist.push1(2);
        llist.push1(1);

        llist.head.next.next.next.next.next.next = llist.head.next.next;

        llist.detectFirstNode(head);
        System.out.println("");
        System.out.println("Starting point of cycle is --- "+llist.detectFirstNode(head).data);


        //Length of a loop
        Node firstNode = llist.detectFirstNode(head);
        Node cur = firstNode;
        int len=1;
        while(cur.next!=firstNode){
            cur=cur.next;
            len++;
        }
        System.out.println(len);

        // Remove a cycle from a linked list
         firstNode = llist.detectFirstNode(head);
         cur = firstNode;
        while(cur.next!=firstNode){
            cur = cur.next;
        }
        cur.next=null;
        printList();

    }


    static class Node2 {
        int data;
        Node2 next, random;
        Node2(int x)
        {
            data = x;
            next = random = null;
        }
    }

    static void print(Node2 start)
    {
        Node2 ptr = start;
        while (ptr != null) {
            System.out.println("Data = " + ptr.data
                    + ", Random = "
                    + ptr.random.data);
            ptr = ptr.next;
        }
    }

//    @Test(description = "Clone a Linked List with Next and Random Pointer")
//    public void test6(){
//        Node2 start = new Node2(1);
//        start.next = new Node2(2);
//        start.next.next = new Node2(3);
//        start.next.next.next = new Node2(4);
//        start.next.next.next.next = new Node2(5);
//
//        // 1's random points to 3
//        start.random = start.next.next;
//
//        // 2's random points to 1
//        start.next.random = start;
//
//        // 3's and 4's random points to 5
//        start.next.next.random = start.next.next.next.next;
//        start.next.next.next.random
//                = start.next.next.next.next;
//
//        // 5's random points to 2
//        start.next.next.next.next.random = start.next;
//
//        System.out.println("Original list : ");
//        print(start);
//
//        System.out.println("Cloned list : ");
//        Node cloned_list = clone(start);
//        print(cloned_list);
//    }


}
