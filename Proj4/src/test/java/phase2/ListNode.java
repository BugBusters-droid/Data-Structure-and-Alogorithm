package phase2;

// Java program for the above approach
import java.util.*;

// A Linked List Node
class ListNode {
    int val;
    ListNode next;

    // Constructor
    ListNode(int val)
    {
        this.val = val;
        this.next = null;
    }
}

class GFG {

    // Function to create Node
    static ListNode getNode(int data)
    {
        ListNode temp = new ListNode(data);
        return temp;
    }

    // Function to print the Linked List
    static void printList(ListNode head)
    {
        while (head.next != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print(head.val);
    }

    // Function that removes continuous nodes
    // whose sum is K
    static ListNode removeZeroSum(ListNode head, int K) {
        ListNode root = new ListNode(0);
        root.next = head;  // Append at the front of the given Linked List

        Map<Integer, ListNode> umap = new HashMap<Integer, ListNode>(); //to store sum and reference
        umap.put(0, root);

        int sum = 0;

        while (head != null) {
            sum += head.val;

            if (umap.containsKey(sum - K)) {
                ListNode prev = umap.get(sum - K); // starting node when sum is found
                ListNode start = prev;
                int aux = sum;
                sum = sum - K;
                while (prev != head) {
                    prev = prev.next;
                    aux += prev.val;
                    if (prev != head) {
                        umap.remove(aux);
                    }
                }
                start.next = head.next; // to connect the nodes after deleting sum nodes
            } else if (!umap.containsKey(sum)) {
                umap.put(sum, head);
            }
            head = head.next;
        }
        return root.next;
    }

    // Driver code
    public static void main(String[] args)
    {
        // head Node
        ListNode head;

        // Create Linked List
        head = getNode(1);
        head.next = getNode(2);
        head.next.next = getNode(3);
        head.next.next.next = getNode(-3);
        head.next.next.next.next = getNode(1);

        // Given sum K
        int K = 0;

        // Function call to get head node
        // of the updated Linked List
        head = removeZeroSum(head, K);

        // Print the updated Linked List
        if (head != null)
            printList(head);
    }
}

// This code is contributed by jitin.

