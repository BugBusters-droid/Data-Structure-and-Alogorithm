package phase2;

import org.testng.annotations.Test;

import java.util.*;

public class StackPractice {

    @Test(description = "Nearest Smaller Element on Left")
    public void test() {

        int a[] = {4, 10, 5, 8, 20, 15, 3, 12};
        Stack<Integer> s = new Stack<>();

        for(int i=0;i<a.length;i++){
            while(!s.isEmpty() && s.peek()>a[i]){
                s.pop();
            }
            if(s.isEmpty()){
                System.out.print("-1,");
            } else {
                System.out.print(s.peek()+",");
            }
            s.push(a[i]); //we need to add each number to stack which we are checking
        }
    }

    @Test(description = "Nearest Smaller Element on Left")
    public void test1() {

        int a[] = {4, 10, 5, 8, 20, 15, 3, 12};
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < a.length; i++) {
            while (!s.isEmpty() && s.peek() >= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                System.out.print("-1,");
            } else {
                System.out.print(s.peek() + ",");
            }
            s.push(a[i]);
        }
    }

    @Test(description = "Parenthesis checker")
    public void test4() {
        //String s = "{[()]}";
        String s = "{}()[]([]";
        System.out.println(checkParenthesis(s));
    }

    @Test(description = "Parenthesis checker")
    public void test41() {
        //String s = "{[()]}";
        String s = "]";
        System.out.println(checkParenthesis1(s));
    }


    boolean isOpening1(char c){
        return c=='(' || c=='{' || c=='[';
    }

    boolean isMatching1(char c1, char c2){
        return (c1=='{' && c2=='}') ||
                (c1=='(' && c2==')') ||
                (c1=='[' && c2==']');
    }

    boolean checkParenthesis1(String s) {
        Stack<Character> stack = new Stack<>();
        char[] ch = s.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isOpening1(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (!isMatching1(stack.peek(), c)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
            //stack.push(c);
        }
        return stack.isEmpty();
    }


    boolean isOpening(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    boolean isMatching(char ch1, char ch2) {
        return (ch1 == '(' && ch2 == ')') ||
                (ch1 == '{' && ch2 == '}') ||
                (ch1 == '[' && ch2 == ']');
    }

    boolean checkParenthesis(String s) {
        char[] c = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < c.length; i++) {
            char ch = c[i];
            if (isOpening(ch)) {
                deque.push(ch);
            } else {
                if (deque.isEmpty()) {
                    return false;
                } else if (!isMatching(deque.peek(), ch)) {
                    return false;
                } else {
                    deque.pop();
                }
            }
        }
        return deque.isEmpty();
    }

    Stack<Pair> mystack = new Stack<>();
    HashMap<Integer, Integer> mymap = new HashMap<>();

    class Pair {
        int data;
        int freq;

        Pair(int data, int freq) {
            this.data = data;
            this.freq = freq;
        }
    }

    int[] NGF(int[] arr, int[] res) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            if (mymap.containsKey(arr[i])) {
                mymap.put(arr[i], mymap.get(arr[i]) + 1);
            } else {
                mymap.put(arr[i], 1);
            }
        }

        int cur_freq = mymap.get(arr[n - 1]);
        mystack.push(new Pair(arr[n - 1], cur_freq));
        res[n - 1] = -1;

        for (int i = n - 2; i >= 0; i--) {
            cur_freq = mymap.get(arr[i]);
            while (!mystack.isEmpty() && mystack.peek().freq <= cur_freq) {
                mystack.pop();
            }
            res[i] = (mystack.isEmpty()) ? -1 : mystack.peek().data;
            mystack.push(new Pair(arr[i], cur_freq));
        }
        return res;
    }


    @Test
    public void ngf(){
        int[] arr = {1, 1, 2, 3, 4, 2, 1};

        int res[] = new int[arr.length];
        NGF(arr, res);
        System.out.println(Arrays.toString(res));
    }

    class DataIndexPair{
        int data;
        int index;

        DataIndexPair(int data, int index){
            this.data=data;
            this.index=index;
        }
    }

    int maxProdLR(int[] arr){
        Stack<DataIndexPair> stackL = new Stack<>();
        //left greater element index+1
        int[] L=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            while(!stackL.isEmpty() && stackL.peek().data<=arr[i]){
                stackL.pop();
            }
            L[i]=(stackL.isEmpty()) ? 0 : stackL.peek().index;
            stackL.push(new DataIndexPair(arr[i],i+1));
        }

        Stack<DataIndexPair> stackR = new Stack<>();
        int[] R=new int[arr.length];

        //right greater element index+1
        for(int i=arr.length-1;i>=0;i--){
            while(!stackR.isEmpty() && stackR.peek().data<=arr[i]){
                stackR.pop();
            }
            R[i]=(stackR.isEmpty()) ? 0 : stackR.peek().index;
            stackR.push(new DataIndexPair(arr[i],i+1));
        }

        int res=0;
        for(int i=0;i<arr.length;i++){
            int prod=L[i]*R[i];
            res=Math.max(res,prod);
        }

        return res;
    }

    @Test
    public void maxProd(){
        int[] arr={5,4,3,4,5};

        //System.out.println(maxProdLR(arr));
        System.out.println(maxProdLR1(arr));
    }

    int maxProdLR1(int[] arr){
        int[] pg=new int[arr.length];
        Stack<DataIndexPair> stackL = new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!stackL.isEmpty() && stackL.peek().data<=arr[i]){
                stackL.pop();
            }
            pg[i]=(stackL.isEmpty()?0:stackL.peek().index);
            stackL.push(new DataIndexPair(arr[i], i+1));
        }

        int[] ng=new int[arr.length];
        Stack<DataIndexPair> stackR = new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!stackR.isEmpty() && stackR.peek().data<=arr[i]){
                stackR.pop();
            }
            ng[i]=(stackR.isEmpty()?0:stackR.peek().index);
            stackR.push(new DataIndexPair(arr[i], i+1));
        }

        int res=0;
        for(int i=0;i<arr.length;i++){
          int prod=pg[i]*ng[i];
          res=Math.max(res,prod);
        }
        return res;
    }

    Stack<Integer> sortStack(Stack<Integer> stack){
        Stack<Integer> tempStack= new Stack<>();

        while(!stack.isEmpty()){
            int tmp=stack.pop();
            while(!tempStack.isEmpty() && tempStack.peek()<tmp){
                stack.push(tempStack.pop());
            }
            tempStack.push(tmp);
        }
        return tempStack;
    }

    @Test
    public void sortUsingTempStack(){
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);

        // This is the temporary stack
        Stack<Integer> tmpStack=sortStack(input);
        System.out.println("Sorted numbers are:");

        while (!tmpStack.empty())
        {
            System.out.print(tmpStack.pop()+" ");
        }
    }

    @Test
    public void sortUsingTempStack1(){
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);

        // This is the temporary stack
        Stack<Integer> tmpStack=sortStack1(input);
        System.out.println("Sorted numbers are:");

        while (!tmpStack.empty())
        {
            System.out.print(tmpStack.pop()+" ");
        }
    }

    Stack<Integer> sortStack1(Stack<Integer> st){
        Stack<Integer> tmpStack= new Stack<>();
        while(!st.isEmpty()){
            int tmp=st.pop();
            while(!tmpStack.isEmpty() && tmpStack.peek()<tmp){
                st.push(tmpStack.pop());
            }
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    void deleteMid(Stack<Character> stack){
        int size = stack.size();
        int count=0;
        Stack<Character> tmp = new Stack<>();
        while(count<size/2){
            tmp.push(stack.pop());
            count++;
        }
        stack.pop();
        while(!tmp.isEmpty()){
            stack.push(tmp.pop());
        }
    }

    @Test
    public void deleteMiddle(){
        Stack<Character> st = new Stack();

        // push elements into the stack
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');
        st.push('5');
        st.push('6');
        //st.push('7');

        deleteMid(st);

        while (!st.empty()) {
            char p = st.peek();
            st.pop();
            System.out.print(p + " ");
        }
    }

    @Test
    public void deleteMiddle1(){
        Stack<Character> st = new Stack();

        // push elements into the stack
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');
        st.push('5');
        st.push('6');
        //st.push('7');

        deleteMid1(st);

        while (!st.empty()) {
            char p = st.peek();
            st.pop();
            System.out.print(p + " ");
        }
    }

    void deleteMid1(Stack<Character> st){
        int size=st.size();
        int count=0;
        Stack<Character> tmp=new Stack<>();

        while(count<size/2){
            tmp.push(st.pop());
            count++;
        }
        st.pop();
        while(!tmp.isEmpty()){
            st.push(tmp.pop());
        }
    }

    static void insert_at_bottom(char x) {

        if (st.isEmpty())
            st.push(x);

        else {

            // All items are held in Function
            // Call Stack until we reach end
            // of the stack. When the stack becomes
            // empty, the st.size() becomes 0, the
            // above if part is executed and
            // the item is inserted at the bottom
            char a = st.peek();
            st.pop();
            insert_at_bottom(x);

            // push allthe items held
            // in Function Call Stack
            // once the item is inserted
            // at the bottom
            st.push(a);
        }
    }
    static void reverse()
    {
        if (st.size() > 0) {

            // Hold all items in Function
            // Call Stack until we
            // reach end of the stack
            char x = st.peek();
            st.pop();
            reverse();

            // Insert all the items held
            // in Function Call Stack
            // one by one from the bottom
            // to top. Every item is
            // inserted at the bottom
            insert_at_bottom(x);
        }
    }
    static Stack<Character> st = new Stack<>();
    @Test
    public void reverseUsingRecursion(){
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');

        System.out.println("Original Stack");
        System.out.println(st);
        reverse();
        System.out.println("Reversed Stack");
        System.out.println(st);
    }

    @Test
    public void reverseUsingRecursion1(){
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');

        System.out.println("Original Stack");

        System.out.println(st);

        // function to reverse
        // the stack
        reverse1();

        System.out.println("Reversed Stack");

        System.out.println(st);
    }

    void reverse1(){
        if(!st.isEmpty()){
            char x=st.peek();
            st.pop();
            reverse1();

            insert_at_bottom1(x);
        }
    }

    void insert_at_bottom1(char x){
        if(st.isEmpty()){
            st.push(x);
        } else {
            char a=st.peek();
            st.pop();
            insert_at_bottom1(x);

            st.push(a);
        }
    }

    static boolean findDuplicateParenthesis(String s) {
        Stack<Character> Stack = new Stack<>();
        char[] str = s.toCharArray();
        for (char ch : str) {
            if (ch == ')') {
                char top = Stack.peek();
                Stack.pop();

                // stores the number of characters between a
                // closing and opening parenthesis
                // if this count is less than  1
                // then the brackets are redundant else not
                int elementsInside = 0;
                while (top != '(') {
                    elementsInside++;
                    top = Stack.peek();
                    Stack.pop();
                }
                if (elementsInside < 1) {
                    return true;
                }
            } else {
                Stack.push(ch); // push open parenthesis '(', operators and operands to stack
            }
        }
        return false; // No duplicates found
    }

    @Test
    public void duplicateBrackets(){
       // String str = "(((a+(b))+(c+d)))";
       // String str="(a+(b)/c)";
        String str="((a+b))";

        if (findDuplicateParenthesis(str)) {
            System.out.println("Duplicate Found ");
        } else {
            System.out.println("No Duplicates Found ");
        }
    }

    @Test
    public void duplicateBrackets1(){
         String str = "(((a+(b))+(c+d)))";
        //String str="(a+(b)/c)";

        if (findDuplicateParenthesis1(str)) {
            System.out.println("Duplicate Found ");
        } else {
            System.out.println("No Duplicates Found ");
        }
    }

    boolean findDuplicateParenthesis1(String s){
        char[] ch = s.toCharArray();


        Stack<Character> st = new Stack<>();
        for(int i=0;i<ch.length;i++){
            if(ch[i]==')'){
                char top=st.peek();
                st.pop();
                int count=0;

                while(top!='('){
                    top=st.peek();
                    st.pop();
                    count++;
                }

                if(count<1){
                    return true;
                }
            } else {
                st.push(ch[i]);
            }
        }
        return false;
    }
}
