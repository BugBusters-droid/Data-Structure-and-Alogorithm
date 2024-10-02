package phase2;

public class Swap {

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

    /* Function to pairwise swap elements of a linked list */
    Node pairWiseSwap(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = head;
        Node curr = head.next;

        head = curr;

        while (true) {
            Node temp = curr.next;
            curr.next = prev; // Change next of current as previous node

            // If next NULL or next is the last node
            if (temp == null || temp.next == null) {
                prev.next = temp;
                break;
            }
            prev.next = temp.next;

            // Update previous and curr
            prev = temp;
            curr = prev.next;
        }
        return head;
    }

    /* Function to print nodes in a given linked list */
    void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    // Driver program to test above functions
    public static void main(String[] args)
    {

        /* The constructed linked list is:
         1->2->3->4->5->6->7 */
        Swap list = new Swap();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);
        list.head.next.next.next.next.next.next = new Node(7);

        System.out.println("Linked list before calling pairwiseSwap() ");
        list.printList(head);
        Node st = list.pairWiseSwap(head);
        System.out.println("");
        System.out.println("Linked list after calling pairwiseSwap() ");
        list.printList(st);
        System.out.println("");
    }

}
