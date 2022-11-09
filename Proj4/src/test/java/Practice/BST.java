package Practice;

import org.testng.annotations.Test;

import java.util.Scanner;
import java.util.Set;

public class BST {

    static Scanner sc;
    public static void main(String[] args){
       sc = new Scanner(System.in);
       Node root = create(); // 10, 20, 30

       System.out.println("Element is present : "+search(root, 10));
       insert(root, 3);
//       insertIterativeWay(root, 15);
//       //delete(root,10);
//       System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
//       System.out.println(isBST(root));
//       System.out.println("Floor value is : "+floor(root, 17)+" and ceil value is : "+ceil(root, 17));
    }


    static Node create() {
        Node root = null;
        System.out.println("Enter data : ");
        int data = sc.nextInt();

        if (data == -1) return null;
        root = new Node(data);

        System.out.println("Enter left for data : " + data);
        root.left = create();
        System.out.println("Enter right for data : " + data);
        root.right = create();

        return root;
    }

    static boolean search(Node root, int key) {
        if (root == null) return false;

        if (root.data == key) return true;
        if (key < root.data) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    static Node insert(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    static Node insertIterativeWay(Node root, int key) {
        Node cur = root;
        Node newNode = new Node(key);
        Node parent = null;

        while (cur != null) {
            parent = cur;
            if (key < cur.data) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (parent == null) parent = newNode;
        else if (key < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return parent;
    }

    static Node delete(Node root, int key) {
        if (root == null) return root;

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                root.data = minValueFromSubtree(root.right);
                root.right = delete(root.right, root.data);
            }
        }
        return root;
    }

    static int minValueFromSubtree(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    static boolean isBST(Node root, int min, int max) {
        if (root == null) return true;

        if (root.data < min || root.data > max) return false;

        return isBST(root.left, min, root.data - 1) && isBST(root.right, root.data + 1, max);
    }

    static Node prev = null;

    static boolean isBST(Node root) {
        if (root != null) {
            if (!isBST(root.left)) return false;

            if (prev != null && prev.data > root.data) return false;

            prev = root;
            return isBST(root.right);
        }
        return true;
    }

    static int floor(Node root, int key) {
        int ans = Integer.MAX_VALUE;
        while (root != null) {
            if (root.data == key) return root.data;

            if (root.data > key) { // floor value should be less than key
                root = root.left;
            } else {
                ans = root.data;
                root = root.right;
            }
        }
        return ans;
    }

    static int ceil(Node root, int key) {
        int ans = Integer.MIN_VALUE;
        while (root != null) {
            if (root.data == key) return root.data;

            if (key < root.data) {
                ans = root.data;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
    }

    static boolean sumInBST(Node root, Set<Integer> set, int sum) {
        if (root == null) return false;

        if (sumInBST(root.left, set, sum)) return true;

        if (set.contains(sum - root.data)) return true;

        set.add(root.data);
        return sumInBST(root.right, set, sum);

    }




}

class Node {
    Node left, right;
    int data;

    Node(int data) {
        this.data = data;
    }
}
