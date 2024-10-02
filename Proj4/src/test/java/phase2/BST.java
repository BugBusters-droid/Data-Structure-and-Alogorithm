package phase2;

import org.testng.annotations.Test;

import java.util.Arrays;

public class BST {

    public static Node1 insert(Node1 root, int x)
    {
        if (root == null)
            return new Node1(x);
        if (x < root.data)
            root.left = insert(root.left, x);
        else if (x > root.data)
            root.right = insert(root.right, x);
        return root;
    }

    public static void main(){
        Node1 root = null;
        int keys[] = { 20, 8, 22, 4, 12, 10, 14 };
        for (int x : keys)
            root = insert(root, x);
    }

    static void printInorder(Node1 node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    static int countNodes(Node1 root)
    {
        if (root == null)
            return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    static int index;
    static void storeInorder(Node1 node, int inorder[])
    {
        // Base Case
        if (node == null)
            return;

        /* first store the left subtree */
        storeInorder(node.left, inorder);

        /* Copy the root's data */
        inorder[index] = node.data;
        index++; // increase index for next entry

        /* finally store the right subtree */
        storeInorder(node.right, inorder);
    }

    static void arrayToBST(int[] arr, Node1 root)
    {
        // Base Case
        if (root == null)
            return;

        /* first update the left subtree */
        arrayToBST(arr, root.left);

        /* Now update root's data and increment index */
        root.data = arr[index];
        index++;

        /* finally update the right subtree */
        arrayToBST(arr, root.right);
    }
    void binaryTreeToBST(Node1 root){
        if (root == null)
            return;

        /* Count the number of nodes in Binary Tree so that
        we know the size of temporary array to be created */
        int n = countNodes(root);

        // Create a temp array arr[] and store inorder traversal of tree in arr[]
        int arr[] = new int[n];
        storeInorder(root, arr);

        // Sort the array using library function for quick sort
        Arrays.sort(arr);


        // Copy array elements back to Binary Tree
        index = 0;
        arrayToBST(arr, root);
    }

    @Test
    public void test(){
        Node1 root = null;

        /* Constructing tree given in the above figure
            10
            / \
          30   15
          /     \
        20       5 */
        root = new Node1(10);
        root.left = new Node1(30);
        root.right = new Node1(15);
        root.left.left = new Node1(20);
        root.right.right = new Node1(5);

        binaryTreeToBST(root);

        System.out.println("Following is Inorder Traversal of the converted BST: ");
        printInorder(root);
    }

    @Test
    public void test1(){
        Node1 root = null;

        /* Constructing tree given in the above figure
               10
            /     \
           5       50
         /        /    \
        1       40     100 */
        root = new Node1(10);
        root.left = new Node1(5);
        root.right = new Node1(50);
        root.left.left = new Node1(1);
        root.right.left = new Node1(40);
        root.right.right = new Node1(100);

        int l=0;
        int h=51;
        System.out.println("Count of nodes between [" + l + ", " + h+ "] is " + getCount(root, l, h));
    }

    int nodesCount=0;

    int getCount(Node1 root, int l, int h) {
        if (root == null) return 0;

        getCount(root.left, l, h);
        if (root.data >= l && root.data <= h) {
            nodesCount++;
            System.out.print(root.data + ",");
        }
        getCount(root.right, l, h);

        return nodesCount;
    }

    static Node1 root;
    static Node1 succ;
    static Node1 insert1(Node1 node, int data)
    {
        if (node == null) {
            node = new Node1(data);
        }

        // If key is smaller than root's key,
        // go to left subtree and set successor
        // as current node
        if (data < node.data) {
            succ = node;
            node.left = insert1(node.left, data);
        }

        // Go to right subtree
        else if (data > node.data)
            node.right = insert1(node.right, data);

        return node;
    }
    static void replace(int arr[], int n)
    {
        // start from right to left
        for (int i = n - 1; i >= 0; i--) {
            succ=null;
            root = insert1(root, arr[i]);

            if (succ != null)
                arr[i] = succ.data;

                // No inorder successor
            else
                arr[i] = -1;
        }
    }

    @Test
    public void test2(){
        int arr[] = new int[] { 8,  58, 71, 18, 31, 32, 63, 92, 43, 3,  91, 93, 25, 80, 28 };
        int n = arr.length;

        //replace(arr, n);
        replace1(arr, n);

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    void replace1(int[] arr, int n){
        for(int i=n-1;i>=0;i--){
            succ=null;
            root=insert2(root,arr[i]);

            if(succ!=null){
                arr[i]=succ.data;
            } else {
                arr[i]=-1;
            }
        }
    }

    Node1 insert2(Node1 root, int data){
        if(root==null){
            root=new Node1(data);
        }

        if(data < root.data){
            succ=root;
            root.left = insert2(root.left,data);
        } else if(data> root.data){
            root.right= insert2(root.right, data);
        }

        return root;
    }



    @Test
    public void test3(){
        Node1 root = null;

        /* Constructing tree given in the above figure
               10
            /     \
           5       50
         /        /    \
        1       40     100 */
        root = new Node1(10);
        root.left = new Node1(5);
        root.right = new Node1(50);
        root.left.left = new Node1(1);
        root.right.left = new Node1(40);
        root.right.right = new Node1(100);


        System.out.print("Size of the largest BST is " + largestBst(root) + "\n");
    }

    int largestBst(Node1 root){
        if(root==null) return 0;

        int cL=largestBst(root.left)+1;
        int rL=largestBst(root.right)+1;

        return Math.max(cL,rL);
    }



    @Test
    public void kthSmallest(){
        Node1 root = null;

        /* Constructing tree given in the above figure
               10
            /     \
           5       50
         /   \     /    \
        1     6  40     100 */
        root = new Node1(10);
        root.left = new Node1(5);
        root.right = new Node1(50);
        root.left.left = new Node1(1);
        root.left.right = new Node1(6);
        root.right.left = new Node1(40);
        root.right.right = new Node1(100);

        System.out.print("kth smallest element is :: " + kthSmallestElement(root,2).data + "\n");
    }

    int count;
    Node1 kthSmallestElement(Node1 root, int k){
        if(root==null) return null;

        Node1 left=kthSmallestElement(root.left, k);
        if(left!=null) return left;

        count++;
        if(count==k){
            return root;
        }

        return kthSmallestElement(root.right,k);
    }

    @Test
    public void lca(){
        Node1 root = null;

        /* Constructing tree given in the above figure
               20
            /     \
           8       22
         /   \
        4     12
              / \
             10  14   */
        root = new Node1(20);
        root.left = new Node1(8);
        root.right = new Node1(22);
        root.left.left = new Node1(4);
        root.left.right = new Node1(12);
        root.left.right.left = new Node1(10);
        root.left.right.right = new Node1(14);

        System.out.print("kth smallest element is :: " + lcaInBST(root,10,14).data + "\n");
    }

    Node1 lcaInBST(Node1 root, int n1, int n2){
        while(root!=null){
            if(n1<root.data && n2<root.data){
                root=root.left;
            } else if(n1>root.data && n2>root.data){
                root=root.right;
            } else {
                break;
            }
        }
        return root;
    }
}


class Node1 {
    int data;
    Node1 left, right;
    Node1(int x)
    {
        data = x;
        left = right = null;
    }
}
