package phase2;

import org.testng.annotations.Test;

import java.util.HashMap;

public class LinkedList {

    static Node head,head1,head2;
    static class Node {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    Node reverse(Node head){
        Node cur = head;
        Node prev=null;

        while(cur!=null){
            Node temp = cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        return prev;
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

    @Test(description = "reverse a linked list : Iterative approach")
    public void test11() {
        LinkedList list = new LinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(head);
        head = list.reverse1(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(head);
    }

    Node reverse1(Node head){
        Node cur=head;
        Node prev=null;

        while(cur!=null){
            Node tmp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=tmp;
        }
        return prev;
    }


    @Test
    public void deleteMiddle(){
        /* Start with the empty list */
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        System.out.println("Given Linked List");
        printList(head);
        head = deleteMid(head);
        System.out.println("Linked List after deletion of middle");
        printList(head);
    }

    static Node deleteMid(Node head) {
        // Base cases
        if (head == null) return null;
        if (head.next == null) return null;

        // Initialize slow and fast pointers
        // to reach middle of linked list
        Node slow_ptr = head;
        Node fast_ptr = head;

        // Find the middle and previous of middle.
        Node prev = null;

        // To store previous of slow_ptr
        while (fast_ptr != null && fast_ptr.next != null) {
            fast_ptr = fast_ptr.next.next;
            prev = slow_ptr;
            slow_ptr = slow_ptr.next;
        }

        // Delete the middle node
        prev.next = slow_ptr.next;

        return head;
    }

    @Test
    public void deleteMiddle1(){
        /* Start with the empty list */
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        System.out.println("Given Linked List");
        printList(head);
        head = deleteMid1(head);
        System.out.println("Linked List after deletion of middle");
        printList(head);
    }

    Node deleteMid1(Node head) {
        if (head == null || head.next == null) return null;

        Node slow = head;
        Node fast = head;
        Node prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

    void push(int data){
        Node toAdd = new Node(data);
        toAdd.next=head;
        head=toAdd;
    }

    void printList()
    {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    void removeDuplicates1(){
        Node cur = head;
        while(cur!=null){
            Node temp=cur;
            while(temp!=null && temp.data==cur.data){
                temp=temp.next;
            }
            cur.next=temp;
            cur=cur.next;
        }
    }

    public static void main(String args[])
    {
        LinkedList llist = new LinkedList();
        llist.push(20);
        llist.push(13);
        llist.push(13);
        llist.push(11);
        llist.push(11);
        llist.push(11);

//        System.out.println("List before removal of duplicates");
//        llist.printList();
//        llist.removeDuplicates();
//        System.out.println("List after removal of elements");
//        llist.printList();

        System.out.println("List before removal of duplicates");
        llist.printList();
        llist.removeDuplicates1();
        System.out.println("List after removal of elements");
        llist.printList();
    }


    void removeDuplicates()
    {
        Node curr = head;
        while (curr != null) {
            Node temp = curr;
            /*Compare current node with the next node and
            keep on deleting them until it matches the
            current node data */
            while (temp != null && temp.data == curr.data) {
                temp = temp.next;
            }
            curr.next = temp; //by pointing it to different element to ensure duplicates are deleted
            curr = curr.next; // move cur to next different element
        }
    }

     Node addOneUtil(Node head)
    {
        // res is head node of the resultant list
        Node res = head;
        Node temp = null, prev = null;

        int carry = 1, sum;
        while (head != null) // while both lists exist
        {
            sum = carry + head.data;
            carry = (sum >= 10) ? 1 : 0;
            sum = sum % 10;

            // Create a new node with sum as data
            head.data = sum;
            temp = head;
            head = head.next;
        }

        // if some carry is still there, add a new node to
        // result list.
        if (carry > 0)
            temp.next = new Node(carry);

        return res;
    }

    // This function mainly uses addOneUtil().
     Node addOne(Node head)
    {
        head = reverse(head);
        head = addOneUtil(head);
        return reverse(head);
    }

    @Test
    public void add1() {
        Node head = new Node(1);
        head.next = new Node(9);
        head.next.next = new Node(9);
        head.next.next.next = new Node(9);

        System.out.print("List is ");
        printList(head);

        head = addOne(head);
        System.out.println();
        System.out.print("Resultant list is ");
        printList(head);
    }

    @Test
    public void add11() {
        Node head = new Node(1);
        head.next = new Node(9);
        head.next.next = new Node(9);
        head.next.next.next = new Node(9);

        System.out.print("List is ");
        printList(head);

        head = addOne1(head);
        System.out.println();
        System.out.print("Resultant list is ");
        printList(head);
    }

    Node addOne1(Node head){
        head=reverse1(head);
        head=addOneUtil1(head);
        head=reverse(head);
        return head;
    }

    Node addOneUtil1(Node head){
        Node res=head;
        Node temp=head;

        int carry=1,sum;
        while(head!=null){
            sum=carry+head.data;
            carry=(sum>=10)?1:0;
            sum=sum%10;

            head.data=sum;
            temp=head;
            head=head.next;
        }

        if(carry>0) temp.next=new Node(carry);

        return res;
    }

    @Test
    public void reverseWithKSize(){
        LinkedList llist = new LinkedList();

        /* Constructed Linked List is 1->2->3->4->5->6->
           7->8->8->9->null */
        llist.push(9);
        llist.push(8);
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List");
        llist.printList();

        llist.head = llist.reverse(llist.head, 3);

        System.out.println("Reversed list");
        llist.printList();
    }

    @Test
    public void reverseWithKSize1(){
        LinkedList llist = new LinkedList();

        /* Constructed Linked List is 1->2->3->4->5->6->
           7->8->8->9->null */
        llist.push(9);
        llist.push(8);
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List");
        llist.printList();

        llist.head = llist.reverse1(llist.head, 3);

        System.out.println("Reversed list");
        llist.printList();
    }

    Node reverse1(Node head, int k){
        if(head==null) return null;
        Node cur = head;
        Node prev=null;
        Node next=null;
        int count=0;

        while(count<k && cur!=null){
            next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
            count++;
        }

        if(next!=null){
            head.next=reverse1(next,k);
        }

        return prev;
    }

    Node reverse(Node head, int k) {
        if (head == null)
            return null;
        Node current = head;
        Node next = null;
        Node prev = null;

        int count = 0;


        /* Reverse first k nodes of linked list */
        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }



        /* next is now a pointer to (k+1)th node
           Recursively call for the list starting from
           current. And make rest of the list as next of
           first node */
        if (next != null)
            head.next = reverse(next, k);

        // prev is now head of input list
        return prev;
    }

    void printNthFromLast(int N) {
        Node main_ptr = head;
        Node ref_ptr = head;

        int count = 0;
        if (head != null) {
            while (count < N) {
                if (ref_ptr == null) {
                    System.out.println(N + " is greater than the no " + " of nodes in the list");
                    return;
                }
                ref_ptr = ref_ptr.next;
                count++;
            }

            if (ref_ptr == null) {
                if (head != null)
                    System.out.println("Node no. " + N + " from last is " + head.data);
            } else {
                while (ref_ptr != null) {
                    main_ptr = main_ptr.next;
                    ref_ptr = ref_ptr.next;
                }
                System.out.println("Node no. " + N + " from last is " + main_ptr.data);
            }
        }
    }

    @Test
    public void nthNode() {
        LinkedList llist = new LinkedList();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);

        // Function call
        llist.printNthFromLast(2);
        llist.printNthFromLastNaive(2);
    }

    @Test
    public void nthNode1() {
        LinkedList llist = new LinkedList();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);

        // Function call
        llist.printNthFromLastNaive1(2);
    }

    void printNthFromLastNaive1(int k){
        int len=0;
        Node cur = head;
        while(cur!=null){
            cur=cur.next;
            len++;
        }
        cur=head;
        for(int i=1;i<len-k+1;i++){
           cur=cur.next;
        }
        System.out.println(cur.data);
    }

    void printNthFromLastNaive(int N){
        int len=0;
        Node cur = head;
        while(cur!=null){
            cur=cur.next;
            len++;
        }
        cur=head;
        for(int i=1;i<len-N+1;i++){
            cur=cur.next;
        }
        System.out.println(cur.data);
    }

    Node kAltReverse(Node node, int k) {
        Node current = node;
        Node next = null, prev = null;
        int count = 0;

        /*1) reverse first k nodes of the linked list */
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        /* 2) Now head points to the kth node.  So change next
         of head to (k+1)th node*/
        if (node != null) {
            node.next = current;
        }

        /* 3) We do not want to reverse next k nodes. So move the current
         pointer to skip next k nodes */
        count = 0;
        while (count < k -1 && current != null) {
            current = current.next;
            count++;
        }

        if (current != null) {
            current.next = kAltReverse(current.next, k);
        }
        return prev;
    }

    @Test
    public void reverseAlternateK(){
        LinkedList list = new LinkedList();
        for (int i = 20; i > 0; i--) {
            list.push(i);
        }
        System.out.println("Given Linked List :");
        list.printList(head);
        head = list.kAltReverse(head, 3);
        System.out.println("");
        System.out.println("Modified Linked List :");
        list.printList(head);
    }

    @Test
    public void reverseAlternateK1(){
        LinkedList list = new LinkedList();
        for (int i = 20; i > 0; i--) {
            list.push(i);
        }
        System.out.println("Given Linked List :");
        list.printList(head);
        head = list.kAltReverse1(head, 3);
        System.out.println("");
        System.out.println("Modified Linked List :");
        list.printList(head);
    }

    Node kAltReverse1(Node head, int k) {
        return _kAltReverse(head, k, true);
    }

    Node _kAltReverse(Node head, int k, boolean isAlternate) {
        if (head == null) {
            return null;
        }
        Node current = head;
        Node prev = null;
        Node next = null;
        int count = 0;

        /* The loop serves two purposes
         1) If b is true, then it reverses the k nodes
         2) If b is false, then it moves the current pointer */
        while (current != null && count < k) {
            next = current.next;
            if (isAlternate == true) { // Reverse the nodes only if b is true
                current.next = prev;
            }
            prev = current;
            current = next;
            count++;
        }
        if (isAlternate == true) {
            head.next = _kAltReverse(next, k, !isAlternate);
            return prev;
        } else {
            prev.next = _kAltReverse(next, k, !isAlternate);
            return head;
        }
    }

    @Test
    public void reverseAlternateK11(){
        LinkedList list = new LinkedList();
        for (int i = 20; i > 0; i--) {
            list.push(i);
        }
        System.out.println("Given Linked List :");
        list.printList(head);
        head = list.kAltReverse11(head, 3);
        System.out.println("");
        System.out.println("Modified Linked List :");
        list.printList(head);
    }

    Node kAltReverse11(Node head, int k){
        boolean isAlternative=true;
        head=_kAltReverse1(head, k, isAlternative);
        return head;
    }


    Node _kAltReverse1(Node head, int k, boolean isAlternative){
        if(head==null) return null;

        Node cur=head;
        Node prev=null;
        Node next=null;

        int count = 0;
        while (count < k && cur != null) {
            next = cur.next;
            if (isAlternative) {
                cur.next = prev;
            }
            prev = cur;
            cur = next;
            count++;
        }
        if(isAlternative){
            head.next=_kAltReverse1(next,k,!isAlternative);
            return prev;
        } else {
            prev.next=_kAltReverse1(next,k,!isAlternative);
            return head;
        }
    }


    @Test
    public void deleteLastOccurrence(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(10);
        head.next.next.next.next.next.next = new Node(13);

        System.out.printf("Created Linked List: ");
        printList(head);
        //deleteLast(head, 2);
        deleteLast1(head, 2);
        System.out.printf("\nLinked List after Deletion of 2: ");
        printList(head);
    }

    @Test
    public void deleteLastOccurrence1(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(10);
        head.next.next.next.next.next.next = new Node(13);

        System.out.printf("Created Linked List: ");
        printList(head);
        //deleteLast(head, 2);
        deleteLast2(head, 2);
        System.out.printf("\nLinked List after Deletion of 2: ");
        printList(head);
    }

    void deleteLast2(Node head, int key){
        Node cur = head;
        Node x = null;
        while(cur!=null){
            if(cur.data==key){
                x=cur;
            }
            cur=cur.next;
        }

        if(x!=null){
            x.data=x.next.data;
            x.next=x.next.next;
        }

    }


    static Node deleteLast1(Node head, int key) {
        Node cur = head;
        Node x = null;
        while (cur != null) {
            if (cur.data == key) {
                x = cur;
            }
            cur = cur.next;
        }

        if (x != null) {
            x.data = x.next.data;
            x.next = x.next.next;
        }
        return head;
    }

    static Node deleteLast(Node head, int key) {
        Node x = null;
        Node temp = head;
        Node prev=null;
        while (temp != null) {
            if (temp.data == key)
                x = temp;

            temp = temp.next;
        }

        // key occurs at-least once
        if (x != null) {
            x.data = x.next.data; // Copy key of next Node to x
            x.next = x.next.next; // using this approach we copy next node data to x and unlink next with next.next
        }
        return head;
    }

    @Test
    public void testRotate(){
        LinkedList llist = new LinkedList();
        // create a list 10->20->30->40->50->60
        for (int i = 60; i >= 10; i -= 10)
            llist.push(i);

        System.out.println("Given list");
        llist.printList();
        //llist.rotate(4);
        llist.rotate1(4);
        System.out.println("Rotated Linked List");
        llist.printList();
    }

    @Test
    public void testRotate1(){
        LinkedList llist = new LinkedList();
        // create a list 10->20->30->40->50->60
        for (int i = 60; i >= 10; i -= 10)
            llist.push(i);

        System.out.println("Given list");
        llist.printList();
        llist.rotate2(4);
        System.out.println("Rotated Linked List");
        llist.printList();
    }

    void rotate2(int k){
        int count=1;
        Node cur=head;
        while(count<k && cur!=null){
            cur=cur.next;
            count++;
        }
        if(cur==null) return;
        Node nthNode=cur;
        while(cur.next!=null){
            cur=cur.next;
        }

        cur.next=head;
        head=nthNode.next;
        nthNode.next=null;
    }

    void rotate1(int k){
        if(k==0) return;
        Node cur=head;
        int count=1;
        while(count<k && cur!=null){
            cur= cur.next;
            count++;
        }

        Node kthNode=cur;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=head;
        head=kthNode.next;
        kthNode.next=null;
    }

    void rotate(int k) {
        if (k == 0)
            return;
        Node current = head;
        int count = 1;
        while (count < k && current != null) {
            current = current.next;
            count++;
        }
        if (current == null)
            return;

        Node kthNode = current; //this points to 40
        while (current.next != null)
            current = current.next;

        current.next = head; // Change next of last node to previous head
        // Next of 60 is now changed to node 10
        head = kthNode.next; // Change head to (k+1)th node
        // head is now changed to node 50
        kthNode.next = null; // change next of kth node to null
    }

    @Test
    public void skipMDeleteN(){
        LinkedList llist = new LinkedList();
        // create a list 10->20->30->40->50->60
        for (int i = 11; i > 0; i--) {
            llist.push(i);
        }

        System.out.println("Given list");
        llist.printList();
        //llist.skipMdeleteN( head,3,2);
        llist.skipMdeleteN1( head,3,2);
        System.out.println("Rotated Linked List");
        llist.printList();
    }

    @Test
    public void skipMDeleteN1(){
        LinkedList llist = new LinkedList();
        // create a list 10->20->30->40->50->60
        for (int i = 11; i > 0; i--) {
            llist.push(i);
        }

        System.out.println("Given list");
        llist.printList();
        //llist.skipMdeleteN( head,3,2);
        llist.skipMDeleteN11( head,3,2);
        System.out.println("Rotated Linked List");
        llist.printList();
    }

    void skipMDeleteN11(Node head, int M, int N) {
        int count;
        Node cur = head;
        Node t=head;
        while (cur != null) {
            for(count=1;count<M && cur!=null;count++){
                cur = cur.next;
            }
            if(cur==null) return;
            t=cur.next;
            for(count=1;count<=N && t!=null;count++){
                t=t.next;
            }
            cur.next=t;
            cur=t;
        }
    }


    static void skipMdeleteN1(Node head, int M, int N){
        Node cur= head,t;
        int count=1;
        while(cur!=null){
            for(count=1;count<M &&cur!=null;count++){
                cur=cur.next;
            }
            if (cur==null) return;

            t=cur.next;
            for(count=1;count<=N&&t!=null;count++){
                t=t.next;
            }
            cur.next=t;
            cur=t;
        }
    }

    static void skipMdeleteN(Node head, int M, int N) {
        Node curr = head, t;
        int count;
        while (curr != null) {
            // Skip M nodes
            for (count = 1; count < M && curr != null; count++)
                curr = curr.next;

            // If we reached end of list, then return
            if (curr == null)
                return;

            // Start from next node and delete N nodes
            t = curr.next;
            for (count = 1; count <= N && t != null; count++) {
                t = t.next;
            }

            curr.next = t;
            curr = t;
        }
    }

    @Test
    public void merge1(){
//        LinkedList abc = new LinkedList();
//        LinkedList xyz = new LinkedList();
//        abc.push(3);
//        abc.push(2);
//        abc.push(1);
//
//        System.out.println("First Linked List:");
//        abc.printList();
//
//        xyz.push(8);
//        xyz.push(7);
//        xyz.push(6);
//        xyz.push(5);
//        xyz.push(4);
//
//        System.out.println("Second Linked List:");
//        //xyz.printList();

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);

        Node head2 = new Node(4);
        head2.next = new Node(5);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(7);
        head2.next.next.next.next = new Node(8);

        merge(head1, head2);

//        System.out.println("Modified first linked list:");
//        abc.printList();
//
//        System.out.println("Modified second linked list:");
//        xyz.printList();
    }

    public Node merge( Node node1, Node node2 ){
        Node head = node1;
        while( node1 != null && node2 != null ){
            Node next1 = node1.next;
            Node next2 = node2.next;
            node1.next = node2;
            node2.next = next1;
            if( next1 != null ){
                node1 = next1;
            }
            if( next2 != null ){
                node2 = next2;
            }
        }
        return head;
    }

    void merge2(Node p, Node q)
    {
        Node p_curr = p, q_curr = q;
        Node p_next, q_next;

        // While there are available positions in p;
        while (p_curr != null && q_curr != null) {

            // Save next pointers
            p_next = p_curr.next;
            q_next = q_curr.next;

            // make q_curr as next of p_curr
            q_curr.next = p_next; // change next pointer of q_curr
            p_curr.next = q_curr; // change next pointer of p_curr

            // update current pointers for next iteration
            p_curr = p_next;
            q_curr = q_next;
        }
        q = q_curr;
    }

    void printList1(Node node)
    {
        Node temp = node;
        if (node != null) {
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != node);
        }
    }
    @Test
    public void splitInToTwoHalves(){
        LinkedList list = new LinkedList();

        // Created linked list will be 12->56->2->11
        list.head = new Node(12);
        list.head.next = new Node(56);
        list.head.next.next = new Node(2);
        list.head.next.next.next = new Node(11);
        list.head.next.next.next.next = list.head;

        System.out.println("Original Circular Linked list ");
        list.printList1(head);

        // Split the list
        list.splitList();
        System.out.println("");
        System.out.println("First Circular List ");
        list.printList1(head1);
        System.out.println("");
        System.out.println("Second Circular List ");
        list.printList1(head2);
    }

    @Test
    public void splitInToTwoHalves1(){
        LinkedList list = new LinkedList();

        // Created linked list will be 12->56->2->11
        list.head = new Node(12);
        list.head.next = new Node(56);
        list.head.next.next = new Node(2);
        list.head.next.next.next = new Node(11);
        list.head.next.next.next.next = list.head;

        System.out.println("Original Circular Linked list ");
        list.printList1(head);

        // Split the list
        list.splitList1();
        System.out.println("");
        System.out.println("First Circular List ");
        list.printList1(head1);
        System.out.println("");
        System.out.println("Second Circular List ");
        list.printList1(head2);
    }
    void splitList1(){
        Node slow=head;
        Node fast= head;
        while(fast.next!=head && fast.next.next!=head){
            slow=slow.next;
            fast=fast.next.next;
        }
        if(fast.next.next==head){
            fast=fast.next;
        }
        head1=head;
        head2=slow.next;
        fast.next=head2;
        slow.next=head1;
    }

    void splitList() {
        if (head == null) return;
        Node slow = head;
        Node fast = head;

        while (fast.next != head & fast.next.next != head) { // to restrict it to meet head since its circular
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next.next == head) { //to move fast one pointer ahead bcz of above restriction
            fast = fast.next;
        }

        head1 = head; //assigning the heads and circular links
        head2 = slow.next;
        fast.next = head2;
        slow.next = head;
    }


    static NodeD headD;
    static class NodeD {

        int data;
        NodeD next, prev;

        NodeD(int d) {
            data = d;
            next = prev = null;
        }
    }

    void reverseDouble() {
        NodeD cur = headD;
        NodeD temp = null;

        while (cur != null) {
            temp = cur.prev;
            cur.prev = cur.next;
            cur.next = temp;
            cur = cur.prev;
        }

        if (temp != null) {
            headD = temp.prev;
        }
    }

    void pushD(int new_data)
    {
        /* allocate node */
        NodeD new_node = new NodeD(new_data);

        /* since we are adding at the beginning,
         prev is always NULL */
        new_node.prev = null;

        /* link the old list of the new node */
        new_node.next = headD;

        /* change prev of head node to new node */
        if (headD != null) {
            headD.prev = new_node;
        }

        /* move the head to point to the new node */
        headD = new_node;
    }


    @Test
    public void reverseDoubleLL(){
        LinkedList list = new LinkedList();

        /* Let us create a sorted linked list to test the
         functions Created linked list will be 10->8->4->2
       */
        list.pushD(2);
        list.pushD(4);
        list.pushD(8);
        list.pushD(10);

        System.out.println("Original linked list ");
        list.printListD(headD);

        // Function call
        list.reverseDouble();

        System.out.println("");
        System.out.println("The reversed Linked List is ");
        list.printListD(headD);
    }

    void printListD(NodeD node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    @Test
    public void sumZero(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(-3);
        head.next.next.next.next = new Node(1);

        // Given sum K
        int K = 0;

        // Function call to get head node
        // of the updated Linked List
        head = removeZeroSum(head, K);

        // Print the updated Linked List
        if (head != null)
            printList(head);
    }

    @Test
    public void sumZero1(){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(-3);
        head.next.next.next.next = new Node(1);

        // Given sum K
        int K = 0;

        // Function call to get head node
        // of the updated Linked List
        head = removeZeroSum1(head, K);

        // Print the updated Linked List
        if (head != null)
            printList(head);
    }

    Node removeZeroSum1(Node head, int k){
        Node cur = head;
        Node prev,start;
        HashMap<Integer,Node> map = new HashMap<>();
        Node root=new Node(0);
        root.next=head;
        map.put(0,root);
        int sum=0;

        while(cur!=null){
            sum+=cur.data;
            if(map.containsKey(sum-k)){
                prev=map.get(sum-k);
                start=prev;
                int aux=sum;
                while(prev!=cur){
                    prev=prev.next;
                    aux=aux+prev.data;
                    if(prev!=cur){
                        map.remove(aux);
                    }
                }
                start.next=cur.next;
            } else if(!map.containsKey(sum)){
                map.put(sum,cur);
            }
            cur=cur.next;
        }
        return root.next;
    }

    static Node removeZeroSum(Node head, int k){
        Node cur=head;
        Node prev,start;
        HashMap<Integer,Node> map = new HashMap<>();
        Node root= new Node(0);
        root.next=head;
        map.put(0,root);

        int sum=0;
        while (cur!=null){
            sum+=cur.data;
            if(map.containsKey(sum-k)){
                 prev=map.get(sum-k);
                 start=prev;
                 int aux=sum;
                 sum=sum-k;
                 while(prev!=cur){
                     prev=prev.next;
                     aux+=prev.data;
                     if(prev!=cur){
                         map.remove(aux);
                     }
                 }
                 start.next=cur.next;
            } else if(!map.containsKey(sum)){
                map.put(sum,cur);
            }
            cur=cur.next;
        }
        return root.next;
    }


    @Test
    public void testDeleteNodesOnRight(){
        Node head = new Node(12);
        head.next = new Node(15);
        head.next.next = new Node(10);
        head.next.next.next = new Node(11);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(3);

//        Node head = new Node(10);
//        head.next = new Node(20);
//        head.next.next = new Node(30);
//        head.next.next.next = new Node(40);
//        head.next.next.next.next = new Node(50);
//        head.next.next.next.next.next = new Node(60);

        //before delettion
        printList(head);
        head = delLesserNodes(head);
        System.out.println("After deletion");
        printList(head);
    }

    @Test
    public void testDeleteNodesOnRight1(){
        Node head = new Node(12);
        head.next = new Node(15);
        head.next.next = new Node(10);
        head.next.next.next = new Node(11);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(3);

//        Node head = new Node(10);
//        head.next = new Node(20);
//        head.next.next = new Node(30);
//        head.next.next.next = new Node(40);
//        head.next.next.next.next = new Node(50);
//        head.next.next.next.next.next = new Node(60);

        //before delettion
        printList(head);
        head = delLesserNodes1(head);
        System.out.println("After deletion");
        printList(head);
    }

    Node delLesserNodes1(Node head)
    {
        head=reverse(head);
        head=deleteRight1(head);
        head=reverse(head);
        return head;
    }
    Node deleteRight1(Node head){
        Node cur = head;

        while (cur!=null && cur.next!=null){
          if(cur.next.data<cur.data){
              cur.next= cur.next.next;
          } else {
              cur=cur.next;
          }
        }
        return head;
    }

    Node delLesserNodes(Node head)
    {
        head=reverse(head);
        head=deleteRight(head);
        head=reverse(head);
        return head;
    }

    Node deleteRight(Node head) {
        if (head == null || head.next == null) return null;
        Node cur = head;

        while (cur != null && cur.next != null) {
            if (cur.next.data < cur.data) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }


    @Test
    public void seggregateEvenOdd(){
        LinkedList llist = new LinkedList();
        llist.push(11);
        llist.push(10);
        llist.push(9);
        llist.push(6);
        llist.push(4);
        llist.push(1);
        llist.push(0);
        System.out.println("Original Linked List");
        llist.printList();

        llist.segregateEvenOdd();

        System.out.println("Modified Linked List");
        llist.printList();
    }

    @Test
    public void seggregateEvenOdd1(){
        LinkedList llist = new LinkedList();
        llist.push(11);
        llist.push(10);
        llist.push(9);
        llist.push(6);
        llist.push(4);
        llist.push(1);
        llist.push(0);
        System.out.println("Original Linked List");
        llist.printList();

        llist.segregateEvenOdd1();

        System.out.println("Modified Linked List");
        llist.printList();
    }

    void segregateEvenOdd1(){
        Node evenStart=null;
        Node evenEnd=null;
        Node oddStart=null;
        Node oddEnd=null;

        Node cur = head;
        while(cur!=null){
            int data = cur.data;
            if(data%2==0){
                if(evenStart==null){
                    evenStart=cur;
                    evenEnd=cur;
                } else {
                    evenEnd.next=cur;
                    evenEnd=evenEnd.next;
                }
            } else {
                if(oddStart==null){
                    oddStart=cur;
                    oddEnd=cur;
                } else {
                    oddEnd.next=cur;
                    oddEnd=oddEnd.next;
                }
            }
            cur=cur.next;
        }

        evenEnd.next=oddStart;
        oddEnd.next=null;
        head=evenStart;
    }

    public void segregateEvenOdd() {

        Node evenStart = null;
        Node evenEnd = null;
        Node oddStart = null;
        Node oddEnd = null;
        Node currentNode = head;

        while (currentNode != null) {
            int element = currentNode.data;
            if (element % 2 == 0) {
                if (evenStart == null) {
                    evenStart = currentNode;
                    evenEnd = evenStart;
                } else {
                    evenEnd.next = currentNode;
                    evenEnd = evenEnd.next;
                }
            } else {
                if (oddStart == null) {
                    oddStart = currentNode;
                    oddEnd = oddStart;
                } else {
                    oddEnd.next = currentNode;
                    oddEnd = oddEnd.next;
                }
            }
            currentNode = currentNode.next;
        }

        if (oddStart == null || evenStart == null) {
            return;
        }
        evenEnd.next = oddStart;
        oddEnd.next = null;
        head = evenStart;
    }


    @Test
    public void addTwoLists(){
        LinkedList list = new LinkedList();

        // creating first list
        list.head1 = new Node(5);
        list.head1.next = new Node(6);
        list.head1.next.next = new Node(3);
        //list.head1.next.next.next = new Node(4);
        //list.head1.next.next.next.next = new Node(6);

        // creating second list
        list.head2 = new Node(8);
        list.head2.next = new Node(4);
        list.head2.next.next = new Node(2);

        System.out.print("Sum List : ");
        // add the two lists and see the result
        list.addTwoLists(head1, head2);
    }

    @Test
    public void addTwoLists1(){
        LinkedList list = new LinkedList();

        // creating first list
        list.head1 = new Node(5);
        list.head1.next = new Node(6);
        list.head1.next.next = new Node(3);
        //list.head1.next.next.next = new Node(4);
        //list.head1.next.next.next.next = new Node(6);

        // creating second list
        list.head2 = new Node(8);
        list.head2.next = new Node(4);
        list.head2.next.next = new Node(2);

        System.out.print("Sum List : ");
        // add the two lists and see the result
        list.addTwoLists11(head1, head2);
    }

    void addTwoLists11(Node first, Node second){
        first=reverse1(head1);
        second=reverse1(head2);
        int carry=0;
        Node sum=null;

        while(first!=null || second!=null || carry==1){
            int newVal=carry;
            if(first!=null){
                newVal+=first.data;
            }
            if(second!=null){
                newVal+=second.data;
            }
            carry=newVal/10;
            newVal=newVal%10;

            Node newNode=new Node(newVal);
            newNode.next=sum;
            sum=newNode;

            if(first!=null) first=first.next;
            if(second!=null) second=second.next;
        }

        printList(sum);
    }


    void addTwoLists(Node first, Node second)
    {
        first = reverse(first);
        second = reverse(second);
        int carry = 0;
        Node sum = null;

        while (first != null || second != null || carry == 1) // if any one of these is left
        // we are stil left with addition
        {
            int newVal = carry;
            if (first != null)
                newVal += first.data;
            if (second != null)
                newVal += second.data;

            carry = newVal / 10;
            newVal = newVal % 10;

            Node newNode = new Node(newVal);
            newNode.next = sum; //appending the previous sums to current node calculation
            sum = newNode;//moving sum to new node

            if (first != null)
                first = first.next;
            if (second != null)
                second = second.next;
        }
        printList(sum);
    }





}
