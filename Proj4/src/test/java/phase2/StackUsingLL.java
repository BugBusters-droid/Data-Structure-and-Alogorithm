package phase2;

class StackNode {
    int data;
    StackNode next;
    public StackNode(int data)
    {
        this.data = data;
        this.next = null;
    }
}

public class StackUsingLL {
    StackNode head;

    // Push and pop operations
    public void push(int data) {
        if (head == null) {
            head = new StackNode(data);
            return;
        }
        StackNode s = new StackNode(data);
        s.next = head;
        head = s;
    }

    public StackNode pop() {
        StackNode s = this.head;
        this.head = this.head.next;
        return s;
    }

    // prints contents of stack
    public void display() {
        StackNode s = this.head;
        while (s != null) {
            System.out.print(s.data + " ");
            s = s.next;
        }
        System.out.println();
    }

    // Reverses the stack using simple
    // linked list reversal logic.
//    public void reverse() {
//        StackNode prev, cur, succ;
//        cur = prev = head;
//        cur = cur.next;
//        prev.next = null;
//        while (cur != null) {
//            succ = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = succ;
//        }
//        this.head = prev;
//    }

    public void reverse() {
        StackNode prev, cur, succ;
        cur = head;
        prev = null;
        while (cur != null) {
            succ = cur.next;
            cur.next = prev;
            prev = cur;
            cur = succ;
        }
        this.head = prev;
    }

    public static void main(String[] args) {
        StackUsingLL s = new StackUsingLL();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println("Original Stack");
        s.display();

        // reverse
        s.reverse();

        System.out.println("Reversed Stack");
        s.display();
    }

}
