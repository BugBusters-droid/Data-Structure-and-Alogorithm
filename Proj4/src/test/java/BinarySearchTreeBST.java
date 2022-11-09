import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BinarySearchTreeBST {

    static Scanner sc = null;
    public static void main (String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();

        System.out.print("\n Element is present ::: "+search(root, 5));
        System.out.println(insert(root, 7));
        System.out.println(insertIterativeWay(root, 7));
    }

    static Node createTree(){
        Node root = null;
        System.out.println("Enter data : ");
        int data = sc.nextInt();

        if(data==-1) return null;

        root = new Node(data);
        System.out.println("Enter left for data : "+data);
        root.left=createTree();

        System.out.println("Enter right for data : "+data);
        root.right=createTree();

        return root;
    }

    static boolean search(Node root, int x) {
        if (root == null) return false;

        if (root.data == x) return true;

        if (x < root.data) {
            return search(root.left, x);
        } else {
            return search(root.right, x);
        }
    }

    static Node insert(Node root, int x){ // TC: O(logN) and SC: O(H)
        if(root ==  null) return new Node(x);

        if(x<root.data){
            root.left = insert(root.left, x); // code on left side of equal is required to create a reference of current
        } else {                              // tree with newly created node. Same applies for down code.
            root.right = insert(root.right, x);
        }
        return root;
    }

    static Node insertIterativeWay(Node root, int x) { // TC: O(N) and SC: O(1) bcz binary tree is not balanced. If balanced
        Node cur = root;  // then TC becomes O(log N)
        Node newNode = new Node(x);
        Node parent = null;

        while (cur != null) {
            parent = cur;
            if (x < cur.data) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

            if (parent == null) {
                parent = newNode;
            } else if (x < parent.data) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        return parent;
    }

    static Node delete(Node root, int x){
        /**
         * Case 1: (both left and right of x is null)
         * X can be a leaf node  - It will be very easy to delete x since no other element is linked to it. So, will
         * mark parent of x as left right null
         *
         * Case 2: (Left of X is null or vice versa)
         * Null is present on left of X but on right some subtree is present -So will link parent of X with subtree of X.
         *
         * Case 3: (Left n right both are not null )
         * There is a subtree present on both left and right of X. So find the smallest element from right of X and replace X
         * with that smallest element and delete that smallest element from right subtree.
         */
        if(root == null) return root;

        if(x< root.data){
            root.left = delete(root.left, x);
        } else if (x> root.data){
            root.right = delete(root.right, x);
        } else {
            if(root.left==null){
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                root.data = minValueFromSubtree(root.right); // basically deleting the x node and putting smallest element from right subtree.
                root.right = delete(root.right, root.data);
            }
        }
        return root;
    }

    static int minValueFromSubtree(Node root){
        int minv = root.data;
        while(root.left!=null){
            minv = root.left.data;
            root=root.left;
        }
        return minv;
    }

    static boolean isBSTUsingRange(Node root){
        /**
         * Way 1: Check if largest element from left of root is smaller than root and smallest element from right
         * of root is greater than root element. Not much efficient bcz this will be O(N^2) TC
         *
         * Way 2: Range check - LNR
         * For first root element range will be -infinity to +infinity
         * For root.left range will be -infinity to root.data (root will become upper bound for left element)
         * For root.right range shall be root.data and prev of root.data.
         * So, current element should satisfy the range
         *
         * Way 3.1: Using inOrder traversal and Storing the data in Array - TC:O(N)
         * If data is sorted form then it means it's BST.
         * This uses extra space since we would be storing the data in Array
         * SC: O(N)
         *
         * Way 3.2: Using inOrder traversal and comparing cur element with prev element - TC:O(N)
         * If cur element is greater than prev element then it means its a sorted element.
         * We need to store cur and prev for this.
         * There wont be any space used so SC: O(1)
         */

        return isBSTUtilUsingRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean isBSTUtilUsingRange(Node root, int min, int max){
        if(root == null) return true;

        if(root.data < min || root.data > max) return false;

        return isBSTUtilUsingRange(root.left, min, root.data-1) &&
                isBSTUtilUsingRange(root.right, root.data+1, max);
    }

    static Node prev = null;
    static boolean isBSTUsingInOrderTraversal(Node root){ //LNR
        if(root != null)
        {
            if(!isBSTUsingInOrderTraversal(root.left)) return false;

            if(prev!=null && prev.data>= root.data) return false;

            prev=root;
            return isBSTUsingInOrderTraversal(root.right);
        }
        return true;
    }

    static int floorValue(Node root, int key){
        /**
         * Floor Value: Value which is lower than key and greater among all the values in BT
         * Ceil Value: Value which is greater than key and lower among all the values in BT
         *
         * Way 1.1 : Use in order traversal and store in array
         * Then find prev and next of the X to find floor and ceil value
         * This will use O(H) space
         *
         * Way 1.2 : Use in order traversal and use binary sort for find floor and ceil value
         * This will use O(1) space
         *
         * Way 2 : Use iterative approach since iterative approach is better than recursive. Recursive always
         * uses extra space
         *
         */

        int ans = Integer.MAX_VALUE;
        while(root!=null){
            if(root.data==key) return root.data;

            if(key<root.data){
                root=root.left;
            } else {
                ans = root.data;
                root=root.right;
            }
        }
        return ans;
    }

    static int ceilValue(Node root, int key){
        int ans = Integer.MIN_VALUE;
        while(root!=null){

            if(root.data==key) return root.data;

            if(key<root.data){
                ans = root.data;
                root=root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
    }

    static int twoSumInBST(Node root, int sum) {
        /**
         * Way 1: In order approach and save the sorted data in any array. Then use two pointer approach
         * from front and tail side to check if their sum is expected one.
         *
         * Way 2:In order approach, For each element from BST, we add in set until number we are adding from BST has no compliment
         * present in hashmap. For ex- Sum is 150 and root node data is 100. Then we check in hashmap if 50 is present
         * .If present then means 50 and 100 together making 150 if not add 100 in hashmap and proceed to other root
         * This also follows in order approach.
         *
         */

        Set<Integer> set = new HashSet<>();
        boolean ans = sumInBSTUtil(root, set, sum);
        return ans ? 1 : 0;

    }

    static boolean sumInBSTUtil(Node root, Set<Integer> set, int sum) {
        if (root == null) return false;

        if (sumInBSTUtil(root.left, set, sum) == true) return true;

        if (set.contains(sum - root.data)) return true;

        set.add(root.data);
        return sumInBSTUtil(root.right, set, sum);
    }



}
