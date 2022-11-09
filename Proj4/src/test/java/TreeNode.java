import java.util.*;
import java.util.LinkedList;

//Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args){
        Tree tree = new Tree();
        tree.root = new Node(3);
        tree.root.left = new Node(9);
        tree.root.right = new Node(20);
        tree.root.right.left = new Node(15);
        tree.root.right.right = new Node(7);

        List<List<Integer>> out = levelOrderBottom(tree.root);
        for(int i=0;i<out.size();i++){
            System.out.print(out.get(i)+" ");
        }
    }

        public static List<List<Integer>> levelOrderBottom(Node root) {

            List<List<Integer>> ans = new ArrayList();

            if (root == null) return ans;

            Queue<Node> queue = new LinkedList();
            queue.add(root);
            int level = 0;


            while (!queue.isEmpty()) {
                List<Integer> lst = new ArrayList();
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    Node cur = queue.poll();
                    lst.add(cur.data);

                    if (cur.left != null) {
                        queue.add(cur.left);
                    }

                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
                ans.add(lst);
            }

            System.out.println(ans.get(0));
            System.out.println(ans.get(1));
            System.out.println(ans.get(2));
            System.out.println(ans.size());

            Stack<List<Integer>> stack = new Stack<>();
            for (int i = 0; i < ans.size(); i++) {
                stack.push(ans.get(i));
            }


            System.out.println(stack.size());

            List<List<Integer>> output = new ArrayList();
            for (int i = 0; i < ans.size(); i++) {
                output.add(stack.pop());
                // stack.pop();
            }
            return output;
        }

}
