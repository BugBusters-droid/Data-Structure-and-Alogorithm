package Practice;


import java.util.*;

public class BinaryTree {

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

        System.out.println("\nLevel order traversal using queue approach");
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

        System.out.println("\n Burn a BT");
        System.out.println(burn(root, 8));

        System.out.println("\n Convert to DLL");
        convertToDLL(root);

        System.out.println("\n Vertical traversal in BT");
        ArrayList<Integer> lst2 = verticalTraversalInBT(root);
        for(int i=0;i<lst2.size();i++){
            System.out.print(lst2.get(i)+" ");
        }
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
        if (root==null) return;

        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    static void preOrder(Node root){
        if (root==null) return;

        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root){
        if (root==null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    static int height(Node root){
        if (root == null) return 0;
        return 1+Math.max(height(root.left), height(root.right));
    }

    static int size(Node root){
        if(root==null) return 0;
        return 1+size(root.left)+size(root.right);
    }

    static int max(Node root){
        if(root==null) return Integer.MIN_VALUE;

        return Math.max(root.data, Math.max(max(root.left), max(root.right)));
    }

    static void levelOrderTraversal(Node root, int level){
        if(root == null) return;

        if(level==1) {
            System.out.print(root.data+" ");
        }

        levelOrderTraversal(root.left, level-1);
        levelOrderTraversal(root.right, level-1);
    }

    static void levelOrderTraversalUsingQueue(Node root){
        if(root==null) return;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.data+" ");

            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }

    static void verticalOrderTraversalUsingQueue(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur == null) {
                if (queue.isEmpty()) return;
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
        leftViewTraversalUtil(root, 1, map);
        for(int i=1;i<=map.size();i++){
            System.out.print(map.get(i).data + " ");
        }
    }
    static void leftViewTraversalUtil(Node root, int level, HashMap<Integer, Node> map){
        if(root==null) return;
        if(!map.containsKey(level)){
            map.put(level, root);
        }
        leftViewTraversalUtil(root.left, level+1, map);
        leftViewTraversalUtil(root.right, level+1, map);
    }

    static void rightViewTraversal(Node root){
        HashMap<Integer, Node> map = new HashMap<>();
        rightViewTraversalUtil(root, 1, map);
        for(int i=1;i<=map.size();i++){
            System.out.print(map.get(i).data + " ");
        }
    }

    static void rightViewTraversalUtil(Node root, int level, HashMap<Integer, Node> map){
        if(root==null) return;
        map.put(level, root);

        rightViewTraversalUtil(root.left, level+1, map);
        rightViewTraversalUtil(root.right, level+1, map);
    }


    static ArrayList topViewOfBT(Node root){
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair(0, root));

        while(!queue.isEmpty()){
           Pair cur = queue.poll();

           if(!map.containsKey(cur.hd)){
               map.put(cur.hd, cur.root.data);
           }

           if(cur.root.left!=null){
               queue.add(new Pair(cur.hd-1,cur.root.left));
           }
            if(cur.root.right!=null){
                queue.add(new Pair(cur.hd+1,cur.root.right));
            }
        }

        ArrayList lst = new ArrayList();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            lst.add(entry.getValue());
        }
        return lst;
    }

    static ArrayList bottomViewOfBT(Node root) {
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair(0, root));

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();


            map.put(cur.hd, cur.root.data);


            if (cur.root.left != null) {
                queue.add(new Pair(cur.hd - 1, cur.root.left));
            }
            if (cur.root.right != null) {
                queue.add(new Pair(cur.hd + 1, cur.root.right));
            }
        }

        ArrayList lst = new ArrayList();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            lst.add(entry.getValue());
        }
        return lst;
    }



    static int diameterOfABinaryTree(Node root){
        if(root==null) return 0;

        int lh = diameterOfABinaryTree(root.left);
        int rh = diameterOfABinaryTree(root.right);

        int cur = 1+height(root.left)+height(root.right);
        return Math.max(cur, Math.max(lh,rh));
    }

    static int ans=0;
    static int diameterOfABinaryTreeUsingHeightMethodItself(Node root){
        if(root==null) return 0;

        int lh = diameterOfABinaryTree(root.left);
        int rh = diameterOfABinaryTree(root.right);

        ans = Math.max(ans, 1+lh+rh);
        return 1+Math.max(lh,rh);
    }

    static Node lowestCommonAncestorInBT(Node root, int n1, int n2){
        if(root==null) return null;

        if(root.data==n1 || root.data == n2) return root;
        Node left = lowestCommonAncestorInBT(root.left, n1, n2);
        Node right = lowestCommonAncestorInBT(root.right, n1, n2);

        if(left==null) return right;
        if(right==null) return left;

        return root;
    }

    static int time=-1;
    static int burn(Node root, int leaf){
        Depth depth = new Depth(-1);
        burnABT(root, leaf, depth);
        return time;
    }
    static int burnABT(Node root, int leaf, Depth depth){
       if(root==null) return 0;

       if(leaf==root.data) {
           depth.d=1;
           return 1;
       }

       Depth ld = new Depth(-1);
       Depth rd = new Depth(-1);

       int lh = burnABT(root.left, leaf, ld);
       int rh = burnABT(root.right, leaf, rd);

        if(ld.d!=-1){
            time = Math.max(time, ld.d+rh+1);
            depth.d = ld.d+1;
        } else {
            time = Math.max(time, rd.d+lh+1);
            depth.d = rd.d+1;
        }

        return 1+Math.max(lh,rh);
    }

    static Node prev = null, head = null;
    static void convertToDLL(Node root){
        if(root==null) return;

        convertToDLL(root.left);

        if(prev==null) head=root;
        else {
            root.left = prev;
            prev.right=root;
        }
        prev=root;
        convertToDLL(root.right);
        System.out.println();
    }

    static ArrayList<Integer> verticalTraversalInBT(Node root) {
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();

        queue.add(new Pair(0, root));

        while(!queue.isEmpty()){
            Pair cur = queue.poll();

            if(map.containsKey(cur.hd)){
                map.get(cur.hd).add(cur.hd, cur.root.data);
            } else {
                ArrayList<Integer> lst = new ArrayList<>();
                lst.add(cur.root.data);
                map.put(cur.hd, lst);
            }

            if(cur.root.left != null){
                queue.add(new Pair(cur.hd-1, cur.root.left));
            }
            if(cur.root.right != null){
                queue.add(new Pair(cur.hd+1, cur.root.right));
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Integer>> entrySet : map.entrySet()){
            ans.addAll(entrySet.getValue());
        }
        return ans;
    }
}

class Pair{
    int hd;
    Node root;
    Pair(int hd, Node root){
        this.hd=hd;
        this.root=root;
    }
}

class Depth{
    int d;
    Depth(int d){
        this.d=d;
    }
}
