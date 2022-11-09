import org.testng.annotations.Test;

import java.util.*;
import java.util.LinkedList;

public class Tree {
    static Scanner sc = null;
    public static void main (String[] args){
        sc = new Scanner(System.in);
        Node root = createTree();
        inOrder(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
        System.out.println(height(root));
        System.out.println();
        System.out.println("size of a tree is ::: "+size(root));
        System.out.println();
        System.out.println("max in tree is ::: "+max(root));

        for(int i=1;i<=height(root);i++){
            levelOrderTraversal(root,i);
        }

        System.out.println("Level order traversal using queue approach");
        levelOrderTraversalUsingQueue(root);
        System.out.println("Level order traversal but printing the values vertically");
        verticalOrderTraversalUsingQueue(root);

        System.out.println("\nLeft view is ::: ");
        leftViewTraversal(root);

        System.out.println("\nRight view is ::: ");
        rightViewTraversal(root);

        System.out.println("\nTop view is ::: ");
        ArrayList lst = topViewOfBT(root);
        for(int i=0;i<lst.size();i++){
            System.out.print(lst.get(i)+" ");
        }

        System.out.println("\nBottom view is ::: ");
        ArrayList lst1 = bottomViewOfBT(root);
        for(int i=0;i<lst1.size();i++){
            System.out.print(lst1.get(i)+" ");
        }

        System.out.println("\nDiameter of BT");
        System.out.println(diameterOfABinaryTree(root));

        System.out.println("\nDiameter of BT in more optimized way");
        diameterOfABinaryTreeUsingHeightMethodItself(root);
        System.out.println(ans);

        System.out.println("\nLCA of BT");
        System.out.println(lowestCommonAncestorInBT(root, 7,4).data);
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

    static void inOrder(Node root){
        if(root==null) return;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    static void preOrder(Node root){
        if(root==null) return;
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root){
        if(root==null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    static int height(Node root){
        if(root==null) return 0;
        return Math.max(height(root.left), height(root.right))+1;
    }

    static int size(Node root){
        if(root==null) return 0;

        return size(root.left)+size(root.right)+1;
    }

    static int max(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(max(root.left), max(root.right)));
    }


    static void levelOrderTraversal(Node root, int level){ // O(n^2)
        if(root==null) return;
        if(level==1) System.out.print(root.data+" ");
        else {
            levelOrderTraversal(root.left,level-1);
            levelOrderTraversal(root.right,level-1);
        }
    }

    static void levelOrderTraversalUsingQueue(Node root){
        if(root==null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.data + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    static void verticalOrderTraversalUsingQueue(Node root){
        if(root==null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if(cur==null){
                if(queue.isEmpty()) return;
                queue.add(null);
                System.out.println();
                continue;
            }

            System.out.print(cur.data + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    static void leftViewTraversal(Node root){
        HashMap<Integer, Node> map = new HashMap<>();
        leftViewTraversalUtil(root,map,0);
        for (int i = 0; i < map.size(); i++) {
            System.out.print(map.get(i).data + " ");
        }
    }

    static void rightViewTraversal(Node root){
        HashMap<Integer, Node> map = new HashMap<>();
        rightViewTraversalUtil(root,map,0);
        for (int i = 0; i < map.size(); i++) {
            System.out.print(map.get(i).data + " ");
        }
    }

    static void leftViewTraversalUtil(Node root, HashMap<Integer, Node> map, int level) {
        if (root == null) return;

        if (!map.containsKey(level)) {
            map.put(level, root);
        }

        leftViewTraversalUtil(root.left, map, level + 1);
        leftViewTraversalUtil(root.right, map, level + 1);
    }

    static void rightViewTraversalUtil(Node root, HashMap<Integer, Node> map, int level) {
        if (root == null) return;

        if (!map.containsKey(level)) {
            map.put(level, root); //Either update this by removing if condition or put root.right as first statement
        }

        rightViewTraversalUtil(root.right, map, level + 1);
        rightViewTraversalUtil(root.left, map, level + 1);
    }



    static ArrayList<Integer> topViewOfBT(Node root){ // based on level order traversal concept
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair(0, root));

        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (!map.containsKey(cur.hd)) {
                map.put(cur.hd, cur.node.data);
            }

            if (cur.node.left != null) {
                queue.add(new Pair(cur.hd-1, cur.node.left));
            }

            if (cur.node.right != null) {
                queue.add(new Pair(cur.hd+1, cur.node.right));
            }
        }

        ArrayList<Integer> lst = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            lst.add(entry.getValue());
        }
        return lst;
    }

    static ArrayList<Integer> bottomViewOfBT(Node root) { // based on level order traversal concept and removed if condition
        Queue<Pair> queue = new ArrayDeque<>(); // to update to bottom values. top values will keep on updating with bottom values.
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair(0, root));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            map.put(cur.hd, cur.node.data);

            if (cur.node.left != null) {
                queue.add(new Pair(cur.hd - 1, cur.node.left));
            }

            if (cur.node.right != null) {
                queue.add(new Pair(cur.hd + 1, cur.node.right));
            }
        }

        ArrayList<Integer> lst = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            lst.add(entry.getValue());
        }
        return lst;
    }

    static int diameterOfABinaryTree(Node root){ // O(N^2)
        if(root==null) return 0;
        int leftDiameter = diameterOfABinaryTree(root.left); //diameter of subtree from left of root element
        int rightDiameter = diameterOfABinaryTree(root.right); //diameter of subtree from right of root element

        int lh = height(root.left);
        int rh = height(root.right);
        int curElementDiameter = lh+rh+1; // diameter from current root element
        return Math.max(curElementDiameter, Math.max(leftDiameter,rightDiameter));
    }

    static int ans = 0;
    static int diameterOfABinaryTreeUsingHeightMethodItself(Node root) // O(N)
    {
        if(root==null) return 0;

        int lh = diameterOfABinaryTreeUsingHeightMethodItself(root.left);
        int rh = diameterOfABinaryTreeUsingHeightMethodItself(root.right);

        ans = Math.max(ans, 1+lh+rh);
        return 1+Math.max(lh,rh);
    }


    static Node lowestCommonAncestorInBT(Node root, int n1, int n2){
        /**
         * cur = n1 or cur = n2 then return cur
         * n1 in left subtree and n2 in right subtree
         * both n1 n2 in any of the subtree
         * None of n1 n2 in any of the subtree
         */

        if(root==null) return null;

        if(root.data == n1 || root.data==n2) return root;
        Node left = lowestCommonAncestorInBT(root.left, n1, n2);
        Node right = lowestCommonAncestorInBT(root.right, n1, n2);

        if(left == null) return right;
        if(right == null) return left;
        return root;
    }


    static int minTimeToBurnBT(Node root, int leaf){
        Depth depth = new Depth(-1);
        burnABT(root, leaf, depth);
        return time;
    }
    static int time = -1;
    static int burnABT(Node root, int leaf, Depth depth){
        if(root == null) return 0;
        if(leaf == root.data) {
            depth.d = 1;
            return 1;
        }

        Depth ld = new Depth(-1);
        Depth rd = new Depth(-1);

        int lh = burnABT(root.left, leaf, ld);
        int rh = burnABT(root.right, leaf, rd);

        if(ld.d!=-1){
            time = Math.max(time, ld.d+1+rh);
            depth.d = ld.d+1;
        } else {
            time = Math.max(time, rd.d+1+lh);
            depth.d = rd.d+1;
        }
        return 1+Math.max(lh, rh);
    }

    static ArrayList<Integer> verticalTraversalInBT(Node root) {
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        queue.add(new Pair(0, root));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (map.containsKey(cur.hd)) {
                map.get(cur.hd).add(cur.hd, cur.node.data);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(cur.node.data);
                map.put(cur.hd, temp);
            }

            if (cur.node.left != null) {
                queue.add(new Pair(cur.hd - 1, cur.node.left));
            }
            if (cur.node.right != null) {
                queue.add(new Pair(cur.hd + 1, cur.node.right));
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            ans.addAll(entry.getValue());
        }

        return ans;
    }




    Node prev=null, head=null;
    Node root;
    void convertToDoublyLinkedList(Node root){
        if(root==null) return;

        convertToDoublyLinkedList(root.left);
        if(prev==null)
            head=root;
        else {
            root.left = prev;
            prev.right = root;
        }
        prev=root;
        convertToDoublyLinkedList(root.right);
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    @Test(description = "Convert binary tree to DLL")
    public void test(){
        Tree tree = new Tree();
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        tree.convertToDoublyLinkedList(tree.root);
        tree.printList(tree.head);
    }
}



class Pair {
    int hd;
    Node node;

    public Pair(int hd, Node node){
        this.hd=hd;
        this.node=node;
    }
}

class Node {
    Node left, right;
    int data;

    Node(int data) {
        this.data = data;
    }
}

class Depth {
    int d;
    Depth(int d){
        this.d=d;
    }
}

