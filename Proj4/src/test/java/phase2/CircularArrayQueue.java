package phase2;

public class CircularArrayQueue {

    static int[] a;
    int n;
    int front = -1;
    int rear = -1;

    public CircularArrayQueue(int n) {
        this.n = n;
        a = new int[n];
    }

    void enqueue(int data) {
        if ((rear + 1) % n == front) {
            return;
        }
        if (front == -1) front = 0;
        rear = (rear + 1) % n;
        a[rear] = data;
    }

    int dequeue() {
        if (front == -1) return -1; //empty
        int result = a[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % n;
        }
        return result;
    }

    public static void main(){
        CircularArrayQueue arrayQueue = new CircularArrayQueue(4);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);

        System.out.println("a size is :: "+a.length);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+",");
        }
    }
}
