import org.testng.annotations.Test;

import java.util.*;

public class StackImplementation {

    @Test(description = "Nearest Smaller Element on Left")
    public void test(){

        int a[] = {4,10,5,8,20,15,3,12};
        //Stack<Integer> s = new Stack<>();
        Deque<Integer> s = new ArrayDeque<>();

        for (int i = 0; i < a.length; i++) {
            while (!s.isEmpty() && s.peek() >= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                System.out.print("-1,");
            } else {
                System.out.print(s.peek()+",");
            }
            s.push(a[i]);
        }
    }

    @Test(description = "Nearest Greater Element on Left")
    public void test1(){

        int a[] = {4,10,5,8,20,15,3,12};
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < a.length; i++) {
            while (!s.isEmpty() && s.peek() <= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                System.out.print("-1,");
            } else {
                System.out.print(s.peek()+",");
            }
            s.push(a[i]);
        }
    }

    @Test(description = "Nearest Smaller Element on right")
    public void test2() {

        int a[] = {3, 10, 5, 1, 15, 10, 7, 6};
        int res[] = new int[a.length];
        //Stack<Integer> s = new Stack<>();
        Deque<Integer> s = new ArrayDeque<>();

        for (int i = a.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() >= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = s.peek();
            }
            s.push(a[i]);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }

    @Test(description = "Nearest Greater Element on right")
    public void test3() {

        int a[] = {3, 10, 5, 1, 15, 10, 7, 6};
        int res[] = new int[a.length];
        //Stack<Integer> s = new Stack<>();
        Deque<Integer> s = new ArrayDeque<>();

        for (int i = a.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = s.peek();
            }
            s.push(a[i]);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }

    boolean isOpening(char c){
        return c=='(' || c=='[' || c=='{';
    }

    boolean isMatching(char c1, char c2){
        return (c1=='(' && c2==')') ||
                (c1=='[' && c2==']') ||
                (c1=='{' && c2=='}');
    }

    boolean checkParenthesis(String s) {
        char c[] = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < c.length; i++) {
            char cur = c[i];
            if (isOpening(cur)) {
                deque.push(cur);
            } else {
                if (deque.isEmpty()) {
                    return false;
                } else if (!isMatching(deque.peek(), cur)) {
                    return false;
                } else {
                    deque.pop();
                }
            }
        }
        return deque.isEmpty();
    }

    @Test(description = "Parenthesis checker")
    public void test4() {
        String s = "()";
        System.out.println(checkParenthesis(s));
    }

    int maxAreaUsingBruteForce(int a[]) {
        int area = 0;
        for (int i = 0; i < a.length; i++) {
            int left = i;
            int right = i;
            while (a[left] >= a[i]) {
                left--;
                if (left < 0) {
                    break;
                }
            }
            while (a[right] >= a[i]) {
                right++;
                if (right >= a.length) {
                    break;
                }
            }

            int curArea = (right - left - 1) * a[i];
            if (curArea > area) {
                area = curArea;
            }
        }
        return area;
    }

    int[] prevSmaller(int[] a) {
        int[] res = new int[a.length];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < a.length; i++) {
            while (!s.isEmpty() && a[s.peek()] >= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = s.peek();
            }
            s.push(i);
        }
        return res;
    }

    int[] nextSmaller(int[] a) {
        int[] res = new int[a.length];
        Stack<Integer> s = new Stack<>();

        for (int i = a.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && a[s.peek()] >= a[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                res[i] = a.length;
            } else {
                res[i] = s.peek();
            }
            s.push(i);
        }
        return res;
    }

    int maxHistogram(int[] a){
        int res = 0;
        int[] ps = prevSmaller(a);
        int[] ns = nextSmaller(a);

        for(int i=0;i<a.length;i++){
            int area = (ns[i]-ps[i]-1)*a[i];
            res = Math.max(area,res);
        }
        return res;
    }

    @Test(description = "maximum area in histogram")
    public void test5(){
        int a [] = {4,2,1,5,6,3,2,4,2};
        System.out.println(maxAreaUsingBruteForce(a));
        System.out.println(maxHistogram(a));
    }

    @Test(description = "Maximum Size Rectangle in Binary Matrix : Largest area sub-matrix with all 1's")
    public void test6() {
        int a[][] = {
                {0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 0}
        };
        int cur[] = a[0];
        int maxAns = maxHistogram(cur);

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1) {
                    cur[j] = cur[j] + 1;
                } else {
                    cur[j] = 0;
                }
            }
            int curAns = maxHistogram(cur);
            maxAns = Math.max(curAns, maxAns);
        }
        System.out.println(maxAns);
    }

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    String toPostfix(String s){
        String res = new String("");
        Stack<Character> stack =  new Stack<>();
        char[] ch = s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(ch[i]=='('){
                stack.push(ch[i]);
            } else if (ch[i]==')'){
                while(!stack.isEmpty() && stack.peek()!='('){
                    res+=stack.peek();
                    stack.pop();
                }
                stack.pop();
            } else if (Character.isLetterOrDigit(ch[i])){
                res+=ch[i];
            } else {
                while(!stack.isEmpty() && precedence(ch[i])<=precedence(stack.peek())){
                    res+=stack.peek();
                    stack.pop();
                }
                stack.push(ch[i]);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            res += stack.peek();
            stack.pop();
        }

        return res;
    }
    @Test(description = "Infix to postfix conversion")
    public void test7(){
        //String exp = "a+b*(c^d-e)^(f+g*h)-i";
        String exp = "a+b*(d+e)";
        System.out.println(toPostfix(exp));
    }

    boolean isOperand(char c){
        return (c>='a' && c<='z') ||
                (c>='A' && c<='Z');
    }
    String toInfix(String s){
        Stack<String > stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(isOperand(s.charAt(i))){
                stack.push(s.charAt(i)+"");
            } else {
                String op1 = stack.peek();
                stack.pop();
                String op2 = stack.peek();
                stack.pop();
                stack.push("("+op2+s.charAt(i)+op1+")");
            }
        }
        return stack.peek();
    }
    @Test(description = "Postfix to infix conversion")
    public void test8(){
        //String exp = "a+b*(c^d-e)^(f+g*h)-i";
        String exp = "ab*c+";
        System.out.println(toInfix(exp));
    }

    void spanBruteForce(int[] stock) {
        int span = 0;
        for (int i = 0; i < stock.length; i++) {
            span = 1;
            int j = i - 1;
            while (j >= 0 && stock[i] > stock[j]) {
                span += 1;
                j--;
            }
            System.out.print(span + ",");
        }
    }

    static void calculateSpan(int price[], int n, int S[])
    {
        Deque<Integer> st = new ArrayDeque<Integer>();
        st.push(0);

        S[0] = 1;
        for (int i = 1; i < n; i++) {
            while (!st.isEmpty() && price[st.peek()] <= price[i])
                st.pop();

            // If stack becomes empty, then price[i] is greater than all elements on left of it, i.e.,
            // price[0], price[1], ..price[i-1]. Else price[i]
            // is greater than elements after top of stack
            S[i] = (st.isEmpty()) ? (i + 1) : (i - st.peek());
            st.push(i);
        }
    }

    static void printArray(int arr[])
    {
        System.out.print(Arrays.toString(arr));
    }

    @Test(description = "Stock span problem")
    public void test9(){
        int[] stock = {100,80,60,70,60,75,85};
        //int[] stock = {10,4,5,90,120,80};
        spanBruteForce(stock);
        System.out.println();


        //int price[] = { 10, 4, 5, 90, 120, 80 };
        int price[] = {100,80,60,70,60,75,85};;
        int n = price.length;
        int S[] = new int[n];

        calculateSpan(price, n, S);
        printArray(S);
    }



    static void NFG(int a[], int n, int freq[])
    {

        // stack data structure to store the position
        // of array element
        Stack<Integer> s = new Stack<Integer>();
        s.push(0);

        // res to store the value of next greater
        // frequency element for each element
        int res[] = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = 0;

        for (int i = 1; i < n; i++)
        {
            /* If the frequency of the element which is
                pointed by the top of stack is greater
                than frequency of the current element
                then push the current position i in stack*/

            if (freq[a[s.peek()]] > freq[a[i]])
                s.push(i);
            else
            {
                /*If the frequency of the element which
                is pointed by the top of stack is less
                than frequency of the current element, then
                pop the stack and continuing popping until
                the above condition is true while the stack
                is not empty*/

                while (freq[a[s.peek()]] < freq[a[i]]
                        && s.size() > 0)
                {
                    res[s.peek()] = a[i];
                    s.pop();
                }

                // now push the current element
                s.push(i);
            }
        }

        while (s.size() > 0)
        {
            res[s.peek()] = -1;
            s.pop();
        }

        for (int i = 0; i < n; i++)
        {
            // Print the res list containing next
            // greater frequency element
            System.out.print(res[i] + " ");
        }
    }


    Stack<Pair> mystack = new Stack<>();
    HashMap<Integer,Integer> mymap = new HashMap<>();

    class Pair{
        int data;
        int freq;
        Pair(int data,int freq){
            this.data = data;
            this.freq = freq;
        }
    }

    /*NFG function to find the next greater frequency
    element for each element and for placing it in the
    resultant array */
    void NGF(int[] arr,int[] res) {
        int n = arr.length;

        //Initially store the frequencies of all elements
        //in a hashmap
        for(int i = 0;i<n;i++) {
            if(mymap.containsKey(arr[i]))
                mymap.put(arr[i], mymap.get(arr[i]) + 1);
            else
                mymap.put(arr[i], 1);
        }

        //Get the frequency of the last element
        int curr_freq = mymap.get(arr[n-1]);
        //push it to the stack
        mystack.push(new Pair(arr[n-1],curr_freq));
        //place -1 as next greater freq for the last
        //element as it does not have next greater.
        res[n-1] = -1;

        //iterate through array in reverse order
        for(int i = n-2;i>=0;i--) {
            curr_freq = mymap.get(arr[i]);

            /* If the frequency of the element which is
            pointed by the top of stack is greater
            than frequency of the current element
            then push the current position i in stack*/
            while(!mystack.isEmpty()  &&  curr_freq >= mystack.peek().freq)
                mystack.pop();

            //If the stack is empty, place -1. If it is not empty
            //then we will have next higher freq element at the top of the stack.
            res[i] = (mystack.isEmpty()) ? -1 : mystack.peek().data;

            //push the element at current position
            mystack.push(new Pair(arr[i],mymap.get(arr[i])));
        }
    }


    void NGF1(int[] arr, int[] res){
        int n = arr.length;
        for(int i=0;i<arr.length;i++){
            if(mymap.containsKey(arr[i])){
                mymap.put(arr[i],mymap.get(arr[i])+1);
            } else {
                mymap.put(arr[i],1);
            }
        }

        int cur_freq = mymap.get(arr[n-1]);
        mystack.push(new Pair(arr[n-1],cur_freq));
        res[n-1]=-1;

        for(int i=n-2;i>=0;i--){
            cur_freq= mymap.get(arr[i]);

            while(!mystack.isEmpty() && cur_freq>=mystack.peek().freq){
                mystack.pop();
            }
            res[i] = (mystack.isEmpty()) ? -1 : mystack.peek().data;
            mystack.push(new Pair(arr[i], cur_freq));
        }
    }


    @Test(description = "Next Greater Frequency Element")
    public void test10(){
        int[] arr = {1, 1, 1, 2, 2, 2, 2, 11, 3, 3};

        int res[] = new int[arr.length];
//        NGF(arr, res);
//        System.out.println(Arrays.toString(res));

        NGF1(arr, res);
        System.out.println(Arrays.toString(res));
    }




}
