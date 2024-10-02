package phase2;

/* Java program to merge two
sorted linked lists */

/* Link list node */
class Node {
    int data;
    Node next;
    Node(int d)
    {
        data = d;
        next = null;
    }
}

class MergeLists {
     Node head;

    /* Method to insert a node at
    the end of the linked list */
    public void addToTheLast(Node node)
    {
        if (head == null) {
            head = node;
        }
        else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = node;
        }
    }

    /* Method to print linked list */
    void printList()
    {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    void printList(Node head)
    {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Driver Code
    public static void main(String args[])
    {
		/*
			llist1: 5->10->15,
			llist2: 2->3->20
		*/
        MergeLists llist1 = new MergeLists();
        MergeLists llist2 = new MergeLists();

        // Node head1 = new Node(5);
        llist1.addToTheLast(new Node(5));
        llist1.addToTheLast(new Node(10));
        llist1.addToTheLast(new Node(15));

        // Node head2 = new Node(2);
        llist2.addToTheLast(new Node(2));
        llist2.addToTheLast(new Node(3));
        llist2.addToTheLast(new Node(20));

//        llist1.head = sortedMerge(llist1.head, llist2.head);
//        System.out.println("Merged Linked List is:");
//        llist1.printList();

//        llist1.head = sortedMerge1(llist1.head, llist2.head);
//        System.out.println("Merged Linked List is:");
//        llist1.printList();

//        llist1.head = sortedMerge2(llist1.head, llist2.head);
//        System.out.println("Merged Linked List is:");
//        llist1.printList();

//        Node head = sortedMergeAlternate(llist1.head, llist2.head);
//        System.out.println("Merged Linked List is:");
//        llist1.printList(head);

//        Node head = sortedMergeAlternate1(llist1.head, llist2.head);
//        System.out.println("Merged Linked List is:");
//        llist1.printList(head);

        Node head = sortedMergeAlternate2(llist1.head, llist2.head);
        System.out.println("Merged Linked List is:");
        llist1.printList(head);
    }

    static Node sortedMergeAlternate2(Node a , Node b){
      Node res=new Node(0);
      Node tem=res;
      res.next=a;

      while(a!=null && b!=null){
        Node a_next=a.next;
        Node b_next=b.next;

        b.next=a_next;
        a.next=b;

        a=a_next;
        b=b_next;
      }

      return tem.next;
    }

    static Node sortedMerge2(Node a, Node b){
        Node res=new Node(0);
        Node temp=res;

        while(a!=null && b!=null){
            if(a.data < b.data){
                res.next=a;
                res=res.next;
                a=a.next;
            } else {
                res.next=b;
                res=res.next;
                b=b.next;
            }
        }

        if(a!=null) res.next=a;
        if(b!=null) res.next=b;

        return temp.next;
    }

    static Node sortedMerge1(Node A, Node B){
        Node dummyNode=new Node(0);
        Node right=dummyNode;

        while(true){
            if(A==null) {
                right.next=B;
                break;
            }
            if(B==null){
                right.next=A;
                break;
            }
            if(A.data<=B.data){
               right.next=A;
               right=right.next;
               A=A.next;
            } else {
                right.next=B;
                right=right.next;
                B=B.next;
            }
        }

        return dummyNode.next;
    }

    static Node sortedMergeAlternate1(Node A, Node B) {
        Node res = A;

        while (A != null && B != null) {
            Node A_next = A.next;
            Node B_next = B.next;

            B.next = A.next;
            A.next = B;

            A = A_next;
            B = B_next;
        }
        return res;
    }


    static Node sortedMerge(Node A, Node B) {
        Node dummyNode = new Node(0);//dummy node to store the result
        Node tail = dummyNode;//tail node to move further
        while (true) {
            if (A == null) { //when A linked list is null
                tail.next = B;
                break;
            }
            if (B == null) { //when B linked list is null
                tail.next = A;
                break;
            }

            if (A.data <= B.data) { //comparison
                tail.next = A;
                tail = tail.next;
                A = A.next;
            } else {
                tail.next = B;
                tail = tail.next;
                B = B.next;
            }
        }
        return dummyNode.next;
    }

    static Node sortedMergeAlternate(Node A, Node B) {
        Node A_next, B_next;

        Node res = new Node(0);
        res.next = A;
        // While there are available positions in p;
        while (A != null && B != null) {
            // Save next pointers
            A_next = A.next;
            B_next = B.next;

            B.next = A_next; //Since we have to put B element in A, make B.next pointing to A_next element
            A.next = B; //Then make A_next to point to B

            A = A_next; //increment the pointers
            B = B_next;
        }
        return res.next;
    }
}


