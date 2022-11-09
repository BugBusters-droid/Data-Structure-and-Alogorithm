import org.testng.annotations.Test;

import java.util.Arrays;

public class RecursionConcept {

    /**
     * 1. Find the Base case
     * 2. Find the relation between problem and sub-problems
     * 3. Generalise the relation.
     */

    int sum(int n) {
        int sum = 0;
        if (n == 1) {
            return 1;
        } else {
            return n + sum(n - 1); //sum(n-1) is called "recursive leap of faith"
        }
    }

    @Test(description = "Find the sum of n Natural numbers using Recursion")
    public void test1(){
        System.out.println(sum(5));
    }

    int power(int a, int b){
        if(b==0){
            return 1;
        } else{
            return a * power(a,b-1);
            //a^b= a*a^b-1;
        }
    }

    @Test(description = "Calculate a to the power of b")
    public void test2(){
        System.out.println(power(3,4));
    }

    int count(int n, int m){
        if(n==1 || m==1){
            return 1;
        } else{
            return count(n,m-1)+count(n-1,m);
        }
    }

    @Test(description = "Number of ways to reach last cell in an nxm matrix")
    public void test3(){
        System.out.println(count(4,3));
    }


    int kill(int n, int k){
        if(n==1){
            return 0;
        } else{
            return (kill(n-1,k)+k)%n;
        }
    }

    @Test(description = "Josephus problem : Game of death")
    public void test4(){
        /**
         * n=5, k=3 (person to kill)
         * 1 2 3 4 5
         * .... and n decrease by 1 everytime a person is killed
         * "https://www.youtube.com/watch?v=46zD5d9y9b4&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=9"
         */
        System.out.println(kill(5,3));
    }

    /**
     * Strings recursive problems
     */
    boolean isPalindrome(String s, int l, int r){
        char ch[] = s.toCharArray();
        if(l>=r){
            return true;
        }
        if(ch[l]!=ch[r]){
            return false;
        } else{
            return isPalindrome(s,l+1,r-1);
        }
    }

    @Test(description = "Palindrome string using recursion")
    public void test5(){
        String s = "racecar";
        System.out.println(isPalindrome(s, 0, s.length()-1));
    }

    public void powerSet(String s , int i, String cur){
        char[] ch = s.toCharArray();
        if(i==s.length()){
            System.out.print(cur+" ");
            return;
        } else{
            powerSet(s, i+1, cur+ch[i]);
            powerSet(s, i+1, cur);
        }

    }
    @Test(description = "Print All power set of a String")
    public void test6(){
        String s ="abc";
        powerSet(s,0,"");
    }

    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
    void permutation(String s, int l, int r){
       if(l==r){
           System.out.print(s+" ");
           return;
       } else{
           for(int i=l;i<=r;i++){
               s= swap(s, l, i);
               permutation(s, l+1, r);
               s= swap(s,l,i);
           }

       }
    }

    @Test(description = "Permutation of a given string")
    public void test7(){
        String s = "abc";
        permutation(s,0,s.length()-1);

    }

    void possibleStrings(char[] set, String prefix, int n, int k) {
        if (k == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < n; i++) {
            String newPrefix = prefix + set[i];
            possibleStrings(set, newPrefix, n, k - 1);
        }
    }

    @Test(description = "Print all possible strings of length k that can be formed from a set of n characters")
    public void test8(){
        char[] set1 = {'a', 'b'};
        int k = 3;
        String prefix="";
        int n = set1.length;
        possibleStrings(set1,prefix,n, k);
    }

    int myAtoiRecursive(String str, int n){
        int res=0;
        if(n==1){
            res=str.charAt(0)-'0';
            return res;
        }
        res = (10 * myAtoiRecursive(str, n - 1) + str.charAt(n - 1) - '0');
        return res;
    }

    @Test(description = "Recursive Implementation of atoi() : string (which represents an integer) " +
            "as an argument and returns its value.")
    public void test9(){
        String str = "112";
        int n = str.length();
        System.out.println(myAtoiRecursive(str, n));
    }


    void copy(String s1, char[] s2, int index){
        s2[index]= s1.toCharArray()[index];
        if(index== s1.length()-1){
            return ;
        } else{
            copy(s1,s2,index+1);
        }
    }
    @Test(description = "Function to copy string (Iterative and Recursive)")
    public void test10(){
        String s1 = "venkatesh";
        char[] s2 = new char[s1.length()];
//        for(int i=0;i<s1.length();i++){
//            s2[i]=s1.toCharArray()[i];
//        }
//        System.out.println("Result through Iterative approach is ::: "+String.valueOf(s2));
        copy(s1,s2,0);
        System.out.println("Result through Recursive approach is ::: "+String.valueOf(s2));
    }


    char isUpperRecursive(String s, int index){
        char res = 0;
        if (index == s.length() - 1) {
            return 0;
        }
        if (Character.isUpperCase(s.charAt(index))) {
            res = s.charAt(index);
            return res;
        } else{
            return isUpperRecursive(s,index+1);
        }
    }
    char isUpper(String s){
        for(int i=0;i<s.length();i++){
            if(Character.isUpperCase(s.charAt(i))){
                return s.charAt(i);
            }
        }
        return 0;
    }
    @Test(description = "First uppercase letter in a string (Iterative and Recursive)")
    public void test11(){
        String s = "venkaTesh";
        //char res = isUpper(s);
        char res = isUpperRecursive(s, 0);
        if (res == 0) {
            System.out.println("No upper case");
        } else {
            System.out.println(res);
        }
    }

    int count(String s){
        if(s.equalsIgnoreCase("")){
            return 0;
        }
        return count(s.substring(1))+1;
    }
    @Test(description = "Program for length of a string using recursion")
    public void test12(){
        String s = "venkatesh";
        System.out.println(count(s));
    }


    boolean isConsonent(char ch) {
        char c = Character.toUpperCase(ch);
        if (!(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')) {
            return true;
        }
        return false;
    }

    int countConsonents(String s, int n) {
        if (n == 1) {
            if (isConsonent(s.charAt(0)))
                return 1;
            else
                return 0;
        }
        if (isConsonent(s.charAt(n - 1))) {
            return countConsonents(s, n - 1) + 1;
        } else {
            return countConsonents(s, n - 1);
        }
    }

    @Test(description = "Count consonants in a string (Iterative and recursive methods)")
    public void test13() {
        String s = "v";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toUpperCase(s.charAt(i));
            if (isConsonent(c)) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println(countConsonents(s,s.length()));
    }

    static void printCombinations(char[] input, int index, char[] output, int out_index) {
        if (input.length == index) {
            System.out.println(String.valueOf(output));
            return;
        }
        output[out_index] = input[index];
        output[out_index + 1] = ' ';
        printCombinations(input, index + 1, output, out_index + 2);

        if (input.length != index + 1) {
            printCombinations(input, index + 1, output, out_index + 1);
        }
    }
    @Test(description = "Combinations in a String of Digits")
    public void test14(){
        char input[] = "1214".toCharArray();
        char output[] = new char[100];
        printCombinations(input, 0, output, 0);
    }

    boolean checkEquality(String s){
        return (s.charAt(0)==s.charAt(s.length()-1));
    }

    int countSubstringWithEqualEnds(String s) {
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int len = 1; len <= n - i; len++)
                if (checkEquality(s.substring(i, i + len))) {
                    res++;
                }
        }
        return res;
    }

    static int countSubstrs(String str, int i, int j, int n) {
        // base cases
        if (n == 1)
            return 1;
        if (n <= 0)
            return 0;
        int res = countSubstrs(str, i + 1, j, n - 1) + countSubstrs(str, i, j - 1, n - 1) - countSubstrs(str, i + 1, j - 1, n - 2);
        if (str.charAt(i) == str.charAt(j))
            res++;
        return res;
    }

    @Test(description = "Iterative/Recursive solution to count substrings with same first and last characters")
    public void test15(){
        //Iterative
        //https://www.geeksforgeeks.org/recursive-solution-count-substrings-first-last-characters/
        String s = "abcab";
        System.out.println(countSubstringWithEqualEnds(s));

        //Recursive
        String str = "abcab";
        int n = str.length();
        System.out.print(countSubstrs(str, 0, n - 1, n));
    }

    public static String toString(char[] a) {
        String string = new String(a);
        return string;
    }
    static void generate(int k, char[] ch, int n) {
        // Base Condition when we reached at the end of Array**
        if (n == k) {
            // Printing the Generated String** Return to the previous case*
            System.out.print(toString(ch) + " ");
            return;
        }
        // If the first Character is Zero then adding**
        if (ch[n - 1] == '0') {
            ch[n] = '0';
            generate(k, ch, n + 1);
            ch[n] = '1';
            generate(k, ch, n + 1);
        }
        // If the Character is One then add Zero to next**
        if (ch[n - 1] == '1') {
            ch[n] = '0';
            // Calling Recursively for the next value of Array
            generate(k, ch, n + 1);
        }
    }
    static void fun(int k) {
        if (k <= 0) {
            return;
        }
        char[] ch = new char[k];
        ch[0] = '0'; // Initializing first character to Zero
        generate(k, ch, 1);
        ch[0] = '1'; // Initialized first Character to one--
        generate(k, ch, 1);
    }

    @Test(description = "Generate all binary strings without consecutive 1’s")
    public void test16(){
        int k = 3;
        fun(k);
    }

    /**
     * Array recursion questions
     */

    void possibleCombs(int[] arr, int r, int[] out, int outIndex, int i){
        if(outIndex==r){
            for (int j=0; j<r; j++)
                System.out.print(out[j]+" ");
            System.out.println("");
            return;
        }
        if(i>=arr.length){
            return;
        }
        out[outIndex]=arr[i];
        possibleCombs(arr,r,out,outIndex+1,i+1); // at this first pointer is fixed and second pointer in moving to all other nums
        possibleCombs(arr,r,out,outIndex,i+1); // at this first pointer is shifted with i+1
    }
    @Test(description = "Print all possible combinations of r elements in a given array of size n")
    public void test17(){
        int[] arr = {1,2,3,4};
        int r=2;
        int[] out = new int[r];
        //Iterative approach
        for(int i=0;i<arr.length;i++){
            int c = arr[i];
            for(int j=i+1;j<arr.length;j++){
                System.out.print("{"+c+","+arr[j]+"}");
                System.out.print(",");
            }
        }
        //Recursive approach
        System.out.println();
        int[] out1 = new int[r];
        possibleCombs(arr,r, out1,0,0);
    }

    void incSeq(int k, int n, int[] out, int outIndex, int count){
        if(outIndex==k){
            for (int j=0; j<k; j++)
                System.out.print(out[j]+" ");
            System.out.println("");
            return;
        }
        if(count==n+1){
            return;
        }
        out[outIndex] = count;
        incSeq(k,n,out,outIndex+1, count+1);
        incSeq(k,n,out,outIndex,count+1);
    }
    @Test(description = "Print all increasing sequences of length k from first n natural numbers")
    public void test18(){
        int k=3;
        int n=7;
        int[] out = new int[k];
        incSeq(k,n, out, 0, 1);
        //https://ide.geeksforgeeks.org/843acbc1-8866-407c-9c7f-d4e6d17f04c2
    }

    void possibleSortedArr(int[] A, int[] B, int[] out, int outIndex, int indexA, int indexB){
        if(outIndex==B.length-1){
            for (int j=0; j<B.length; j++)
                System.out.print(out[j]+" ");
            System.out.println("");
            return;
        }
        if(indexA==A.length || indexB==B.length){
            return;
        }
        out[outIndex]=A[indexA];
        if(B[indexB]>A[indexA]){
            out[outIndex]=B[indexB];
        }
        for(int count=1;count<B.length;count++){
            possibleSortedArr(A,B,out, outIndex+1, indexA+1, indexB+1);
            possibleSortedArr(A,B,out, outIndex, indexA+1, indexB+1);
            possibleSortedArr(A,B,out, outIndex, indexA, indexB+1);
        }
    }

    void printArr(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println("");
    }

    void generateUtil(int A[], int B[], int C[], int i, int j, int m, int n, int outIndex, boolean flag) {
        if (flag)
        {
            if (outIndex != 0)
                printArr(C, outIndex + 1);

            //For A array
            for (int k = i; k < m; k++) {
                if (outIndex == 0) { //For placing first element at outIndex = 0 , for all other indexes else part is required
                    C[outIndex] = A[k];
                    generateUtil(A, B, C, k + 1, j, m, n, outIndex, !flag);
                } else if (A[k] > C[outIndex]) {
                    C[outIndex + 1] = A[k];
                    generateUtil(A, B, C, k + 1, j, m, n, outIndex + 1, !flag);
                }
            }
        }
        //For B array
        else {
            for (int l = j; l < n; l++) {
                if (B[l] > C[outIndex]) {
                    C[outIndex + 1] = B[l];
                    generateUtil(A, B, C, i, l + 1, m, n, outIndex + 1, !flag);
                }
            }
        }
    }
    @Test(description = "Generate all possible sorted arrays from alternate elements of two given sorted arrays")
    public void test19(){
        int[] A = {10, 15, 25};
        int[] B = {1, 5, 20, 30};
        int m=A.length;
        int n=B.length;
        int[] out = new int[m+n];
        generateUtil(A, B, out, 0, 0, m, n, 0, true);    }

    int getMin(int[] arr, int n, int index, int res) {
        if (index == n) {
            return res;
        }
        if(index==0){
            res=arr[index];
        } else{
            if (arr[index] < res) {
                res = arr[index];
            }
        }
        return getMin(arr, n, index + 1, res);
    }

    int getMax(int[] arr, int n, int index, int res){
        if(index==n){
            return res;
        }
        if(arr[index]>res){
            res=arr[index];
        }
        return getMax(arr,n,index+1, res);
    }
    @Test(description = "Program to find the minimum (or maximum) element of an array")
    public void test20() {
        int arr[] = {12, 1234, 45, 67, 1};
        int n = arr.length;
        System.out.println("Minimum element"
                + " of array: " + getMin(arr, n,0,0));
        System.out.println("Maximum element"
                + " of array: " + getMax(arr, n,0, 0));
    }



    void sumTriangle(int[] A){
        if (A.length < 1)
            return;

        int[] temp = new int[A.length - 1];
        for (int i = 0; i < A.length - 1; i++)
        {
            int x = A[i] + A[i + 1];
            temp[i] = x;
        }
        sumTriangle(temp);
        System.out.println(Arrays.toString(A));

    }
    @Test(description = "Sum triangle from array")
    public void test21(){
        int[] arr = {1, 2, 3, 4, 5};
        sumTriangle(arr);
    }
}
