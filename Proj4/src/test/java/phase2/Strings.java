package phase2;

import org.testng.annotations.Test;

import java.util.*;

public class Strings {


    static void reverse(char str[], int start, int end)
    {
        char temp;
        while (start <= end) {
            // Swapping the first and last character
            temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
    static char[] reverseWords(char[] s)
    {
        // Reversing individual words as
        int start = 0;
        for (int end = 0; end < s.length; end++) {
            if (s[end] == ' ') {
                reverse(s, start, end);
                start = end + 1;
            }
        }
        // Reverse the last word if space is not found after
        // space from above reverse
        reverse(s, start, s.length - 1);

        // Reverse the entire String
        reverse(s, 0, s.length - 1);
        return s;
    }

    public static String swap(String str, int i, int j)
    {
        char ch[] = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
    public static String reverse_words(String s) {
        int left = 0, i = 0, n = s.length();
        while (s.charAt(i) == ' ') {
            i++;
        }
        left = i; //starting index of any word

        while (i < n) {
            if (i + 1 == n || s.charAt(i) == ' ') { //to stop traversing when its space or end of string
                int j = i - 1; //end index of any word
                if (i + 1 == n) //inc j if last letter is reached since we dec j above bcz of space
                    j++; // and there is no space at last

                while (left < j) //swap when a word is found
                    s = swap(s, left++, j--);
                left = i + 1;
            }
            i++;
        }
        // Use StringBuilder ".reverse()" method to reverse the whole string.
        s = new StringBuilder(s).reverse().toString();
        return s;
    }

    static String longestCommonPrefix(String[] a)
    {
        int size = a.length;
        /* if size is 0, return empty string */
        if (size == 0)
            return "";
        if (size == 1)
            return a[0];

        /* sort the array of strings */
        Arrays.sort(a);

        /* find the minimum length from first and last string */
        int end = Math.min(a[0].length(), a[size-1].length());

        /* find the common prefix between the first and last string */
        int i = 0;
        while (i < end && a[0].charAt(i) == a[size-1].charAt(i) ) {
            i++;
        }

        String pre = a[0].substring(0, i);
        return pre;
    }

    public static void main(String[] args) {

        String s[] = "i like this program very much".split(" ");
        String ans = "";
        for (int i = s.length - 1; i >= 0; i--) {
            ans += s[i] + " ";
        }
        System.out.println(ans.substring(0, ans.length() - 1));

        String s1 = "i like this program very much ";
        char[] p = reverseWords(s1.toCharArray());
        System.out.print(p);


        String str = "i like this program very much";
        str = reverse_words(str);
        System.out.println();
        System.out.println(str);


        String[] input = {"geeksforgeeks", "geeks", "geek", "geezer"};
        System.out.println( "The longest Common Prefix is : " + longestCommonPrefix(input));
        System.out.println( "The longest Common Prefix is : " + longestCommonPrefix1(input));


        String[] S = { "the", "quick", "brown", "fox", "quick" };
        String word1 = "the", word2 = "quick";
        System.out.println("shortest distance between two words is : "+shortestDistance(S, word1, word2));

        //String strBrackets = "(()))(()()())))";
        //String strBrackets = "(())))(";
        String strBrackets = "(((((((((((((((())";
        System.out.println(findIndex(strBrackets));
        System.out.println(findIndex1(strBrackets));


        String str1 = "aac";
        String str2 = "xxy";
        if (str1.length() == str2.length() && areIsomorphic(str1, str2))
            System.out.println("True");
        else
            System.out.println("False");
        if (str1.length() == str2.length() && areIsomorphic1(str1, str2))
            System.out.println("Yes: They are isomorphic");
        else
            System.out.println("No: They are not isomorphic");


        String strAn1 = "anagram";
        String strAn2 = "grammar";
        int k = 2;
        if (arekAnagrams(strAn1, strAn2, k))
            System.out.println("Yes");
        else
            System.out.println("No");

        if (arekAnagrams1(strAn1, strAn2, k))
            System.out.println("Yes: They are anagram");
        else
            System.out.println("No: They are not anagram");


        String strMinPal = "aebcbda";
        System.out.println("Minimum element of deletions = " +utility_fun_for_del(strMinPal, 0, strMinPal.length() - 1));
        System.out.println("Minimum element of deletions = " +utility_fun_for_del1(strMinPal, 0, strMinPal.length() - 1));

        List<String> list = Arrays.asList("abab","gfg");
        for(String s2 : list)
        {
            int cnt = countSub(s2);
            int withEmptyString = cnt+1;
            System.out.println("With empty string count for " + s2 +" is " + withEmptyString);
            System.out.println("Without empty string count for " + s2 + " is " + cnt);

            int cnt1 = countSub1(s2);
            int withEmptyString1 = cnt1+1;
            System.out.println("With empty string count for " + s2 +" is " + withEmptyString1);
            System.out.println("Without empty string count for " + s2 + " is " + cnt1);
        }

        String strRot1 = "amazon";
        String strRot2 = "azonam";
        System.out.println(isRotated(strRot1, strRot2) ?  "Yes" : "No");
        System.out.println(isRotated1(strRot1, strRot2) ?  "Yes" : "No");


        String[] arr = {"ale", "apple", "monkey", "plea"};
        Vector dict = new Vector(Arrays.asList(arr));
        String strSub = "abpcplea";
        System.out.println("largest word is :: "+findLongestString(dict, strSub));

        ArrayList<String> dict1 = new ArrayList<String>(Arrays.asList( "ale", "apple", "monkey", "plea"));
        String strSub1 = "abpcplea";
        System.out.println(LongestWord(dict1, strSub1));

        String strEqual012 = "102100211";
        //System.out.println(getSubstringWithEqual012(strEqual012));
        System.out.println("count with equal 0,1,2 is :: "+getSubstringWithEqual0121(strEqual012));


        String Str = "geeksforgeeks";
        String S1 = "eek";
        String S2 = "ok";
        //modifyString(Str, S1, S2);
        modifyString1(Str, S1, S2);
        modifyString2(Str, S1, S2);
        modifyString3(Str, S1, S2);
        modifyString4(Str, S1, S2);


//        String sPreSuf = "abcaabc";
//        System.out.println();
//        System.out.println(longestPrefixSuffix(sPreSuf));

        String sPreSuf = "abcvggabc";
        System.out.println();
        System.out.println("longest prefix sufix is :: "+longestPrefixSuffix1(sPreSuf));
        System.out.println("longest prefix sufix is :: "+longestPrefixSuffix2(sPreSuf));
        System.out.println("longest prefix sufix is :: "+longestPrefixSuffix3(sPreSuf));

        String sKSubStr = "aabacbebebe";
        int k1 = 3;
        longestKSubstr(sKSubStr, k1);

//        String sKSubStr = "aabacbebebe";
//        int k1 = 3;
        longestKSubstr1(sKSubStr, k1);
        longestKSubstr2(sKSubStr, k1);

        String sSliding = "this is a test string";
        String t = "tist";
        System.out.print(findSubString(sSliding, t));

//        System.out.println();
//        String strNonRep = "geeksforgeeks";
//        System.out.println("The input string is " + strNonRep);
//        int len = longestUniqueSubsttr(strNonRep);
//        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len);
//        int len1 = longestUniqueSubsttr1(strNonRep);
//        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len1);

        System.out.println();
        String strNonRep = "geeksforgeeks";
        System.out.println("The input string is " + strNonRep);
        int len = longestUniqueSubsttr11(strNonRep);
        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len);
        int len2 = longestUniqueSubsttr112(strNonRep);
        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len2);
        int len1 = longestUniqueSubsttr111(strNonRep);
        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len1);
        int len12 = longestUniqueSubsttr1112(strNonRep);
        System.out.println("The length of the longest " + "non-repeating character " + "substring is " + len12);

//        String strMax = "((()()";
//        System.out.println(findMaxLen(strMax));
//        str = "()(()))))";
//        System.out.println(findMaxLen(str));
//        //System.out.print(solve("((()()()()(((())", 16));
//        System.out.print(solve(strMax, 6));

        String strMax = "()()";
        System.out.println(findMaxLen1(strMax));
        str = "()(()))))";

        System.out.println(findMaxLen1(str));
        System.out.println(findMaxLen2(str));
        System.out.print("Length of valid substring is : "+solve1("((()()()()(((())", 16));
        System.out.print(solve2("((()()()()(((())", 16));



        String wordArr[] = { "cat", "dog", "tac", "god", "act" };
        int size = wordArr.length;
        printAnagramsTogether(wordArr, size);


        String sDis = "abc";
        int kDis = 2;
        System.out.println("Total substrings with exactly " + k + " distinct characters : " + exact_k_chars(sDis, kDis));
        String sDis1 = "aba";
        System.out.println("Total substrings with exactly " + k + " distinct characters : " + exact_k_chars(sDis1, kDis));
        System.out.println("Total substrings with exactly " + k + " distinct characters with exactly "+k+" length : " + countSubstrings("aabcdabbcdc", 3));
        System.out.println("Total substrings with exactly " + k + " distinct characters with exactly "+k+" length : " + countSubstrings1("aabcdabbcdc", 3));


        String strPal = "geeks";
        strPal = str.toLowerCase();
        if (isPalindrome(strPal))
            System.out.print("Yes");
        else
            System.out.print("No");
        if (isPalindrome(0, strPal.length() - 1, strPal))
            System.out.print("Yes");
        else
            System.out.print("No");


        String str1Rot = "ABACD";
        String str2Rot = "CDABA";
        System.out.println();
        if (areRotations(str1Rot, str2Rot))
            System.out.println("Strings are rotations of each other");
        else
            System.out.println("Strings are not rotations of each other");

        if (areRotations1(str1Rot, str2Rot))
            System.out.println("Strings are rotations of each other");
        else
            System.out.println("Strings are not rotations of each other");


        if (isCyclicRotation(str1Rot, str2Rot)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        if (isCyclicRotation1(str1Rot, str2Rot)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


        String first = "XY";
        String second = "12";
        String[] results = {"1XY2", "Y1X2", "Y21XX"};
        for (String result : results) {
            if (checkLength(first, second, result) == true && shuffleCheck(first, second, result) == true) {
                System.out.println(result + " is a valid shuffle of " + first + " and " + second);
            }
            else {
                System.out.println(result + " is not a valid shuffle of " + first + " and " + second);
            }
        }

        for (String result : results) {
            if (checkLength1(first, second, result) == true && shuffleCheck1(first, second, result) == true) {
                System.out.println(result + " is a valid shuffle of " + first + " and " + second);
            }
            else {
                System.out.println(result + " is not a valid shuffle of " + first + " and " + second);
            }
        }

        String sub="01010";
        findSubsequences(sub,0, ""); // Calling a function
        System.out.println();
        findSubsequences1(sub,0, ""); // Calling a function
        System.out.println();
        permute(sub, 0, sub.length()-1);
        System.out.println();
        permute1(sub, 0, sub.length()-1);

        System.out.println();
        String expr = "([{}])[)";
        if (areBracketsBalanced(expr))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");

        System.out.println();
        if (areBracketsBalanced1(expr))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");

        System.out.println();
        String expr1 = "}}{{";
        //String expr1 = "{}{}";
        System.out.print("number of reversal required is "+countMinReversals(expr1));
        System.out.print("number of reversal required is "+countMinReversals1(expr1));

        String swap = "[]][][";
        System.out.println();
        System.out.println("minimum swaps required :: "+swapCount(swap) );
        System.out.println("minimum swaps required :: "+swapCount1(swap) );

        swap = "[[][]]";
        System.out.println(swapCount(swap) );

        String strFlips = "0001010111";
        System.out.println(minFlipToMakeStringAlternate(strFlips));
        System.out.println(minFlipToMakeStringAlternate1(strFlips));

        String A = "EACBD";
        String B = "EABCD";
        System.out.println("Minimum number of operations required is " + minOps(A, B));


        String sDel = "geeks for geeks is best";
        System.out.println("Input  : " + sDel);
        System.out.println("Output : " + deleteConsecutiveStrings(sDel));
        System.out.println("Output : " + deleteConsecutiveStrings1(sDel));
    }

    static void modifyString4(String s, String s1, String s2){
        String ans="";

        for(int i=0;i<s.length();i++){
            int k=0;
            if(s.charAt(i)==s1.charAt(k) && i+s1.length()<=s.length()){
                int j;
                for(j=i;j<i+s1.length();j++){
                    if(s.charAt(j)!=s1.charAt(k)){
                        break;
                    } else {
                        k++;
                    }
                }
                if(j==i+s1.length()){
                   ans=ans+s2;
                   i=j-1;
                } else {
                    ans=ans+s.charAt(i);
                }

            } else {
                ans=ans+s.charAt(i);
            }
        }
        System.out.println("modified string is ::" + ans);
    }

    static void modifyString3(String s , String s1, String s2){
        String res="";
        for(int i=0;i<s.length();i++){
            int k=0;
            if(s.charAt(i)==s1.charAt(k)){
                int j;
                for(j=i;j<i+s1.length();j++){
                    if(s.charAt(j)==s1.charAt(k)){
                        k++;
                    } else{
                        break;
                    }
                }
                if(j==i+s1.length()){
                    res=res+s2;
                    i=j-1;
                } else {
                    res=res+s.charAt(i);
                }
            } else {
                res=res+s.charAt(i);
            }
        }
        System.out.println("modified string is ::" + res);
    }

    static void modifyString2(String s, String s1, String s2) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int k = 0;
            if (s.charAt(i) == s1.charAt(k)) {
                int j;
                for (j = i; j < i + s1.length(); j++) {
                    if (s.charAt(j) == s1.charAt(k)) {
                        k++;
                    } else {
                        break;
                    }
                }

                if (j == i + s1.length()) {
                    res = res + s2;
                    i = j - 1;
                } else {
                    res = res + s.charAt(i);
                }

            } else {
                res = res + s.charAt(i);
            }
        }

        System.out.println("modified string is ::" + res);
    }

    static int solve2(String s, int n){
        int res=0;
        int left=0;
        int right=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left++;
            } else {
                right++;
            }
            if(left==right){
                res=Math.max(res,2*right);
            }
            if(right>left){
                left=right=0;
            }
        }

        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='('){
                left++;
            } else {
                right++;
            }
            if(left==right){
                res=Math.max(res,2*left);
            }
            if(left>right){
                left=right=0;
            }
        }

        return res;
    }
    static int findMaxLen2(String s){
        char[] ch = s.toCharArray();
        int len=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<s.length();i++){
            char c= ch[i];
            if(c=='('){
                stack.push(i);
            } else {
               if(!stack.isEmpty()){
                   stack.pop();
               }
                if(!stack.isEmpty()){
                    len=Math.max(len,i-stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return len;
    }
    static int countSubstrings1(String s , int k){
        int count=0;
        HashMap<Character,Integer> map = new HashMap();
        for(int i=0;i<k;i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        if(map.size()==k) count++;

        for (int i = k; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(s.charAt(i - k), map.get(s.charAt(i - k)) - 1);

            if (map.get(s.charAt(i - k)) == 0) {
                map.remove(s.charAt(i - k));
            }
            if(map.size()==k) count++;
        }
        return count;
    }
    static int longestUniqueSubsttr1112(String s){
        String res="";
        int length=0;
        for(char c:s.toCharArray()){
            String cur=String.valueOf(c);
            if(res.contains(cur)){
                res=res.substring(res.indexOf(cur)+1);
            }
            res=res+cur;
            length=Math.max(length,res.length());
        }
        return length;
    }
    static int longestUniqueSubsttr112(String s){
        int n=s.length();
        int res=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(areDistinct(s,i,j)){
                   res=Math.max(res,j-i+1);
                }
            }
        }
        return res;
    }

    static void longestKSubstr2(String s, int k){
        int n=s.length();
        int res=0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<=n;j++){
                HashSet<Character> set = new HashSet<>();
                for(int x=i;x<j;x++){
                    set.add(s.charAt(x));
                }
                if(set.size()==k){
                   res=Math.max(res,j-i);
                }
            }
        }
        System.out.println("longest ::"+res);
    }
    static int longestPrefixSuffix2(String s){
        int len=0;
        int i=(s.length()+1)/2;

        while(i<s.length()){
            if(s.charAt(len)==s.charAt(i)){
                i++;
                len++;
            } else {
                i++;
                len=0;
            }
        }
        return len;
    }
    static String deleteConsecutiveStrings1(String s){
        String res="";
        int i=0;
        int j=0;
        while(j<s.length()){
            if(s.charAt(i)==s.charAt(j)){
                j++;
            } else {
                res=res+s.charAt(i);
                i=j;
                j++;
            }
        }
        res=res+s.charAt(j-1);
        return res;
    }

    static int minFlipToMakeStringAlternate1(String s){
        return Math.min(minFlips(s,'0'),minFlips(s,'1'));
    }

    static int minFlips(String s, char expected){
        int flipCount=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=expected){
                flipCount++;
            }
            expected=flip(expected);
        }
        return flipCount;
    }

    static int swapCount1(String s){
        int leftCount=0;
        int rightCount=0;
        int imbalance=0;
        int res=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='['){
                leftCount++;
                if(imbalance>0){
                    res+=imbalance;
                    imbalance--;
                }
            } else {
                rightCount++;
                imbalance=rightCount-leftCount;
            }
        }
        return res;
    }

    static int countMinReversals1(String s) {
        int temp = 0;
        int res = 0;
        int n = s.length();
        if (n % 2 != 0) return -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                temp++;
            } else if (temp == 0) {
                res++;
                temp++;
            } else {
                temp--;
            }
        }

        if (temp > 0) {
            res += temp / 2;
        }
        return res;
    }
    static void permute1(String str, int l, int r){
        if(l==r){
            System.out.print(str+",");
            return;
        }
        for(int i=l;i<=r;i++){
            str=swap(str,l,i);
            permute1(str,l+1,r);
            str=swap(str,l,i);
        }
    }
    static void findSubsequences1(String str, int i, String cur){
        if(i==str.length()){
            System.out.print(cur+",");
            return;
        }
        findSubsequences1(str,i+1,cur+str.charAt(i));
        findSubsequences1(str,i+1,cur);

    }
    static boolean shuffleCheck1(String s1, String s2, String res){
        s1=sortString(s1);
        s2=sortString(s2);
        res=sortString(res);

        int i=0;
        int j=0;
        for(int x=0;x<res.length();x++){
            if(i<s1.length() && s1.charAt(i)==res.charAt(x)){
                i++;
            } else if(j<s2.length() && s2.charAt(j)==res.charAt(x)){
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    static boolean checkLength1(String s1, String s2, String res){
        return (s1.length()+s2.length())==res.length();
    }

    static boolean isCyclicRotation1(String s1, String s2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int n = s1.length();
        if (s1.length() != s2.length()) return false;
        boolean reset = false;
        while (i < 2 * n) {
            if (k == n) return true;
            if (s1.charAt(i % n) == s2.charAt(j % n)) {
                i++;
                j++;
                k++;
            } else if (reset) {
                i++;
            } else {
                reset = true;
                j = 0;
                k = 0;
            }
        }
        return false;
    }


    static boolean areRotations1(String s1, String s2){
        return (s1.length()==s2.length() && (s1+s1).contains(s2));
    }

    static boolean isOpen(char ch){
        return ch=='(' || ch=='{' || ch=='[';
    }

    static boolean isMatching(char ch1 , char ch2){
        return (ch1=='(' && ch2==')') || (ch1=='{'&&ch2=='}' || (ch1=='[' && ch2==']'));
    }
    static boolean areBracketsBalanced1(String str){
        char[] arr = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<arr.length;i++){
            char ch = arr[i];
            if(isOpen(ch)){
               stack.push(ch);
            } else {
               char popped = stack.pop();
               if(!isMatching(popped,ch)){
                   return false;
               }
            }
        }
        return stack.isEmpty();
    }

    static String longestCommonPrefix1(String[] arr){
        String res="";
        int n=arr.length;
        if(n==0) return "";
        if(n==1) return arr[0];

        Arrays.sort(arr);
        int end=Math.min(arr[0].length(),arr[n-1].length());
        int i=0;
        while(i<end){
            if(arr[0].charAt(i)==arr[n-1].charAt(i)) res+=arr[0].charAt(i);
            i++;
        }
        return res;
    }

    static int countSub1(String str){
        int levelCount=0;
        int allCount=0;
        HashMap<Character,Integer> map = new HashMap<>();

        for(int i=0;i<str.length();i++){
            map.put(str.charAt(i),-1);
        }

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(i==0){
                levelCount=1;
                allCount=1;
                map.put(c,levelCount);
                continue;
            }

            levelCount=allCount+1;
            if(map.get(c)<0){
              allCount=allCount+levelCount;
            } else {
                allCount=allCount+levelCount-map.get(c);
            }
            map.put(c,levelCount);
        }
        return allCount;
    }
    static int utility_fun_for_del1(String str, int l, int r){
        if(l>=r)return 0;
        if(str.charAt(l)==str.charAt(r)){
            return utility_fun_for_del(str,l+1,r-1);
        }
        return 1+Math.min(utility_fun_for_del1(str,l+1,r),utility_fun_for_del1(str,l,r-1));
    }

    static boolean arekAnagrams1(String s1, String s2, int k){
        if(s1.length()!=s2.length()) return false;
        int[] s1Count=new int[26];
        int[] s2Count=new int[26];

        for(int i=0;i<s1.length();i++){
            s1Count[s1.charAt(i)-'a']++;
            s2Count[s2.charAt(i)-'a']++;
        }

        int count=0;
        for(int i=0;i<26;i++){
            if(s1Count[i]>s2Count[i]){
              count=count+Math.abs(s1Count[i]-s2Count[i]);
            }
        }
        return count<=k;

    }
    static boolean areIsomorphic1(String s1, String s2){
        HashMap<Character,Character> map = new HashMap<>();
        if(s1.length()!=s2.length()) return false;

        for(int i=0;i<s1.length();i++){
            if(map.containsKey(s1.charAt(i))){
                if(map.get(s1.charAt(i)) != s2.charAt(i)){
                    return false;
                }
            } else if(!map.containsValue(s2.charAt(i))){
                map.put(s1.charAt(i),s2.charAt(i));
            }
        }
        return true;
    }

    static int getSubstringWithEqual0121(String str){
        int n=str.length();
        ArrayList<String> lst = new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
               String s1="";
               for(int x=i;x<=j;x++){
                   s1=s1+str.charAt(x);
               }
               lst.add(s1);
            }
        }

        int count=0;

        for(int i=0;i<lst.size();i++){
            int countZeros=0;
            int countOnes=0;
            int countTwos=0;
            String cur=lst.get(i);
            for(int j=0;j<cur.length();j++){
                if(cur.charAt(j)=='0') countZeros++;
                if(cur.charAt(j)=='1') countOnes++;
                if(cur.charAt(j)=='2') countTwos++;
            }
            if(countZeros==countOnes && countOnes==countTwos) count++;
        }
        return count;
    }

    static void modifyString1(String str, String s1, String s2){
        String ans="";
        int n=str.length();
        for(int i=0;i<n;i++){
            int k=0;
            if(str.charAt(i)==s1.charAt(k) && i+s1.length()< n){
                int j;
               for(j=i;j<i+s1.length();j++){
                   if(str.charAt(j)==s1.charAt(k)){
                       k++;
                   } else{
                       break;
                   }
               }
               if(j==i+s1.length()){
                   ans+=s2;
                   i=j-1;
               } else{
                   ans+=str.charAt(i);
               }
            } else {
                ans+=str.charAt(i);
            }
        }
        System.out.println("Modified string is :: "+ans);
    }

    @Test
    public void minInsertion(){
        String str="abcd";
        System.out.println("Min insertion required is :: "+findMinInsertion(str.toCharArray(),0,str.length()-1));
        System.out.println("Min insertion required is :: "+findMinInsertion1(str.toCharArray(),0,str.length()-1));
    }

    static int findMinInsertion1(char[] str, int i, int j){
        if(i>j) return Integer.MAX_VALUE;
        if(i==j) return 0;
        if(i==j-1) return (str[i]==str[j])?0:1;

        return (str[i]==str[j])?findMinInsertion1(str,i+1,j-1):Math.min(findMinInsertion1(str,i,j-1),
                findMinInsertion1(str,i+1,j))+1;
    }

    static int findMinInsertion(char[] str, int i, int j){
        if(i>j) return Integer.MAX_VALUE;
        if(i==j) return 0;
        if(i==j-1) return (str[i]==str[j])?0:1;

        return (str[i]==str[j])? findMinInsertion(str,i+1,j-1):(Integer.min(findMinInsertion(str,i,j-1),findMinInsertion(str,i+1,j))+1);
    }

    static int longestPrefixSuffix3(String str){
        int mid=(str.length())/2;
        int start=0;

        while(mid<str.length()){
            if(str.charAt(start)==str.charAt(mid)){
                start++;
                mid++;
            } else {
                start=0;
                mid++;
            }
        }
        return start;
    }

    static int longestPrefixSuffix1(String str) {
        int res = -1;
        int n = str.length();
        int i = 0;
        int len = 0;
        i = n / 2 + 1;
        while (i < n)
            if (str.charAt(i) == str.charAt(len)) {
                ++i;
                ++len;
            } else {
                ++i;
                len = 0;
            }
        return len;
    }


    static void longestKSubstr1(String str, int k){
        int res=-1;
        int n=str.length();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                HashSet<Character> set = new HashSet<>();
                for(int x=i;x<j;x++){
                    set.add(str.charAt(x));
                }
                if(set.size()==k){
                    res=Math.max(res,j-i);
                }
            }
        }
        System.out.println("longest substring with k unique characters is ::"+res);;
    }

    static int longestUniqueSubsttr111(String str) {
        int n = str.length();
        int maxLength = -1;

        String res = "";
        for (char c : str.toCharArray()) {
            String s = String.valueOf(c);
            if (res.contains(s)) {
                res = res.substring(res.indexOf(s) + 1);
            }
            res += s;
            maxLength = Math.max(maxLength, res.length());
        }
        return maxLength;
    }

    static int longestUniqueSubsttr11(String str) {
        int n = str.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (areDistinct1(str, i, j)) {
                    maxLength = Math.max(maxLength, j - i+1);
                }
            }
        }
        return maxLength;
    }

    static boolean areDistinct1(String str, int i, int j) {
        boolean[] mark = new boolean[26];
        for (int x = i; x <= j; x++) {
            if (mark[str.charAt(x) - 'a'] == true) {
                return false;
            } else {
                mark[str.charAt(x)-'a']=true;
           }
        }
        return true;
    }

    static int solve1(String str, int n) {
        int left = 0;
        int right = 0;
        int res = 0;

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * left);
            }
            if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            }
            if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }

    static int findMaxLen1(String str){
        int n=str.length();
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int res=0;

        for(int i=0;i<n;i++){
            char c = str.charAt(i);
            if(c=='(') s.push(i);
            else {
                if(!s.isEmpty()){
                    s.pop();
                }
                if(!s.isEmpty()){
                  res=Math.max(res,i-s.peek());
                } else {
                    s.push(i);
                }
            }
        }
        return res;
    }

    public static String deleteConsecutiveStrings(String s)
    {
        int i = 0;
        int j = 0;
        String newElements = "";

        // Iterate string using j pointer
        while (j < s.length()) {
            // If both elements are same then skip
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            else if (s.charAt(j) != s.charAt(i) || j == s.length() - 1) {
                newElements += s.charAt(i);
                i = j; // After appending, slide over the window
                j++;
            }
        }
        newElements += s.charAt(j - 1);
        return newElements;
    }

    public static int minOps(String A, String B)
    {
        if (A.length() != B.length())
            return -1;

        int i, j, res = 0;
        int count[] = new int[256];

        for (i = 0; i < A.length(); i++) {
            count[A.charAt(i)]++; //inc and dec if same char is present
            count[B.charAt(i)]--; // in both A and B
        }
        // Check if all counts become 0
        for (i = 0; i < 256; i++)  // if any of index has value not equal to 0
            if (count[i] != 0) // means some extra char is present
                return -1;

        i = A.length() - 1;
        j = B.length() - 1;
        while (i >= 0) {
            if (A.charAt(i) != B.charAt(j))
                res++;
            else
                j--;
            i--;
        }
        return res;
    }


    public static char flip(char ch)
    {
        return (ch == '0') ? '1' : '0';
    }

    public static int getFlipWithStartingCharacter(String str, char expected)
    {
        int flipCount = 0;
        for (int i = 0; i < str.length(); i++)
        {
            //  if current character is not expected,
            // increase flip count
            if (str.charAt(i) != expected)
                flipCount++;

            //  flip expected character each time
            expected = flip(expected); // expected is alternatively changing from 0<->1
        }
        return flipCount;
    }

    // method return minimum flip to make binary
    // string alternate
    public static int minFlipToMakeStringAlternate(String str)
    {
        //  return minimum of following two
        //  1) flips when alternate string starts with 0
        //  2) flips when alternate string starts with 1
        return Math.min(getFlipWithStartingCharacter(str, '0'), getFlipWithStartingCharacter(str, '1'));
    }

    static long swapCount(String s)
    {
        char[] chars = s.toCharArray();
        // stores total number of Left and Right brackets encountered
        int countLeft = 0, countRight = 0;
        // swap stores the number of swaps required
        //imbalance maintains the number of imbalance pair
        int swap = 0 , imbalance = 0;

        for(int i =0; i< chars.length; i++)
        {
            if(chars[i] == '[')
            {
                countLeft++;
                if(imbalance > 0)
                {
                    // swaps count is last swap count + total
                    // number imbalanced brackets
                    swap += imbalance;
                    // imbalance decremented by 1 as it solved
                    // only one imbalance of Left and Right
                    imbalance--;
                }
            } else if(chars[i] == ']' )
            {
                countRight++;
                imbalance = (countRight-countLeft);
            }
        }
        return swap;
    }

    static int countMinReversals(String s) {
        int temp = 0, res = 0, n = s.length();
        if (n % 2 != 0) // to check if length is odd
            return -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{')
                temp++;
            else {
                if (temp == 0) {
                    res++;
                    temp++;
                } else
                    temp--;
            }
        }
        if (temp > 0)
            res += temp / 2;
        return res;
    }

    static boolean areBracketsBalanced(String expr) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                stack.push(x);
                continue;
            }
            // If current character is not opening bracket,
            // then it must be closing. So stack cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }
        return (stack.isEmpty());
    }

    static void permute(String s, int l, int r){
        if(l==r){
            System.out.print(s+",");
            return;
        }
        for(int i=l;i<=r;i++){
            s=swap(s,l,i);
            permute(s,l+1,r);
            s=swap(s,l,i);
        }
    }

    private static void findSubsequences(String s, int i, String cur) {
        if(i==s.length()){
            System.out.print(cur+",");
            return;
        }
        findSubsequences(s,i+1,cur+s.charAt(i));
        findSubsequences(s,i+1,cur);
    }

    static String sortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        str = String.valueOf(charArray);
        return str;
    }
    static boolean shuffleCheck(String first, String second, String result) {
        first = sortString(first);
        second = sortString(second);
        result = sortString(result);

        int i = 0, j = 0, k = 0;
        while (k != result.length()) {
            if (i < first.length() && first.charAt(i) == result.charAt(k))
                i++;
            else if (j < second.length() && second.charAt(j) == result.charAt(k))
                j++;
            else {
                return false;
            }
            k++;
        }
        if (i < first.length() || j < second.length()) {
            return false;
        }
        return true;
    }

    static boolean checkLength(String first, String second, String result) {
        if (first.length() + second.length() != result.length()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isCyclicRotation(String p, String q) {
        int i = 0, j = 0, k = 0, n = p.length();
        boolean reset = false;
        while (i < 2 * n) {
            if (k == n) {
                return true;
            }
            if (p.charAt(i % n) == q.charAt(j % n)) {
                i++;
                j++;
                k++;
            } else if (reset) {
                reset = false;
                i++;
            } else {
                reset = true;
                j = 0;
                k = 0;
            }
        }
        return false;
    }
    static boolean areRotations(String str1, String str2)
    {
        // There lengths must be same and str2 must be
        // a substring of str1 concatenated with str1.
        return (str1.length() == str2.length()) && ((str1 + str1).indexOf(str2) != -1);
    }

    public static boolean isPalindrome(int i, int j, String A) {
        if (i >= j) {
            return true;
        }
        if (A.charAt(i) != A.charAt(j)) {
            return false;
        }
        return isPalindrome(i + 1, j - 1, A);
    }
    static boolean isPalindrome(String str)
    {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    public static int countSubstrings(String str, int K) {
        int N = str.length();
        int answer = 0;

        // Store the count of distinct characters in every window
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        // Store the frequency of
        // the first K length substring
        for (int i = 0; i < K; i++) {
            // Increase frequency of i-th character
            if (map.get(str.charAt(i)) == null) {
                map.put(str.charAt(i), 1);
            } else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }
        }

        // If K distinct characters
        // exist
        if (map.size() == K)
            answer++;

        // Traverse the rest of the
        // substring
        for (int i = K; i < N; i++) {
            // Increase the frequency of the last character
            // of the current substring
            if (map.get(str.charAt(i)) == null) {
                map.put(str.charAt(i), 1);
            } else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }

            // Decrease the frequency of the first character
            // of the previous substring
            map.put(str.charAt(i - K), map.get(str.charAt(i - K)) - 1);

            // If the character is not present
            // in the current substring
            if (map.get(str.charAt(i - K)) == 0) {
                map.remove(str.charAt(i - K));
            }
            // If the count of distinct
            // characters is 0
            if (map.size() == K) {
                answer++;
            }
        }
        return answer;
    }


    static int most_k_chars(String s, int k) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int num = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            while (map.size() > k) {
                map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0) - 1);
                if (map.get(s.charAt(left)) == 0) {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            num += i - left + 1;
        }
        return num;
    }

    static int exact_k_chars(String s, int k) {
        return most_k_chars(s, k) - most_k_chars(s, k - 1);
    }


    static class Word {
        String str; // to store word itself
        int index; // index of the word in the original array
        Word(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }

    static class DupArray {
        Word[] array; // Array of words
        int size; // Size of array
        public DupArray(String str[], int size) {
            this.size = size;
            array = new Word[size];
            // One by one copy words from the given wordArray to dupArray
            int i;
            for (i = 0; i < size; ++i) {
                // create a word Object with the
                // str[i] as str and index as i
                array[i] = new Word(str[i], i);
            }
        }
    }

    // Compare two words. Used in Arrays.sort() for
    // sorting an array of words
    static class compStr implements Comparator<Word> {
        public int compare(Word a, Word b)
        {
            return a.str.compareTo(b.str);
        }
    }
    static void printAnagramsTogether(String wordArr[], int size)
    {
        // Step 1: Create a copy of all words present
        // in given wordArr. The copy will also have
        // original indexes of words
        DupArray dupArray = new DupArray(wordArr, size);

        // Step 2: Iterate through all words in
        // dupArray and sort individual words.
        int i;
        for (i = 0; i < size; ++i) {
            char[] char_arr = dupArray.array[i].str.toCharArray();
            Arrays.sort(char_arr);
            dupArray.array[i].str = new String(char_arr);
        }

        // Step 3: Now sort the array of words in
        // dupArray
        Arrays.sort(dupArray.array, new compStr());

        // Step 4: Now all words in dupArray are together,
        // but these words are changed. Use the index
        // member of word struct to get the corresponding
        // original word
        for (i = 0; i < size; ++i)
            System.out.print(wordArr[dupArray.array[i].index] + " ");
    }
    public static int solve(String s, int n)
    {
        int left = 0, right = 0;
        int maxlength = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            // Whenever left is equal to right,
            // it signifies that the subsequence is valid and
            if (left == right)
                maxlength = Math.max(maxlength, 2 * right);
                // Resetting the counters when the subsequence becomes invalid
            else if (right > left)
                left = right = 0;
        }

        left = right = 0;
        // Iterating the string from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            // Whenever left is equal to right,
            // it signifies that the subsequence is valid and
            if (left == right)
                maxlength = Math.max(maxlength, 2 * left);
                // Resetting the counters when the subsequence becomes invalid
            else if (left > right)
                left = right = 0;
        }
        return maxlength;
    }

    static int findMaxLen(String str) {
        int n = str.length();
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int result = 0;

        for (int i = 0; i < n; i++) {
            // If opening bracket, push index of it
            if (str.charAt(i) == '(')
                stk.push(i);
                // // If closing bracket, i.e.,str[i] = ')'
            else {
                // Pop the previous opening bracket's index
                if (!stk.empty())
                    stk.pop();

                // Check if this length formed with base of
                // current valid substring is more than max so far
                if (!stk.empty())
                    result = Math.max(result, i - stk.peek());
                    // If stack is empty. push current index as base
                    // for next valid substring (if any)
                else
                    stk.push(i);
            }
        }
        return result;
    }

    public static int longestUniqueSubsttr1(String str) {
        String test = "";
        int maxLength = -1;

        // Return zero if string is empty
        if (str.isEmpty()) {
            return 0;
        }
        // Return one if string length is one
        else if (str.length() == 1) {
            return 1;
        }
        for (char c : str.toCharArray()) {
            String current = String.valueOf(c);
            // If string already contains the current character
            // Then substring after repeating that character. Take ex of a substring
            // eksforg. Now, after g when e comes which is repeating, below function will remove first e
            // and create a substring as ksforg and then add current e. Basically ignoring previous repeating char
            // and adding current repeating char
            if (test.contains(current)) {
                test = test.substring(test.indexOf(current) + 1);
            }
            test = test + String.valueOf(c);
            maxLength = Math.max(test.length(), maxLength);
        }
        return maxLength;
    }

    public static Boolean areDistinct(String str, int i, int j) {
        // Note : Default values in visited are false
        boolean[] visited = new boolean[26];
        for (int k = i; k <= j; k++) {
            if (visited[str.charAt(k) - 'a'] == true)
                return false;
            visited[str.charAt(k) - 'a'] = true;
        }
        return true;
    }

    public static int longestUniqueSubsttr(String str) {
        int n = str.length();
        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (areDistinct(str, i, j))
                    res = Math.max(res, j - i + 1);
        return res;
    }

    static final int no_of_chars = 256;
    static String findSubString(String str, String pat)
    {
        int len1 = str.length();
        int len2 = pat.length();
        // Check if string's length is less than pattern's
        // length. If yes then no such window can exist
        if (len1 < len2) {
            System.out.println("No such window exists");
            return "";
        }

        int hash_pat[] = new int[no_of_chars];
        int hash_str[] = new int[no_of_chars];

        // Store occurrence ofs
        // characters of pattern
        for (int i = 0; i < len2; i++)
            hash_pat[pat.charAt(i)]++;

        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;

        // Start traversing the string
        // Count of characters
        int count = 0;
        for (int j = 0; j < len1; j++) {

            // Count occurrence of characters
            // of string
            hash_str[str.charAt(j)]++;

            // If string's char matches
            // with pattern's char
            // then increment count
            if (hash_str[str.charAt(j)]
                    <= hash_pat[str.charAt(j)])
                count++;

            // If all the characters are matched
            if (count == len2) {

                // Try to minimize the window
                while (hash_str[str.charAt(start)]
                        > hash_pat[str.charAt(start)]
                        || hash_pat[str.charAt(start)]
                        == 0) {

                    if (hash_str[str.charAt(start)]
                            > hash_pat[str.charAt(start)])
                        hash_str[str.charAt(start)]--;
                    start++;
                }

                // update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }

        // If no window found
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }

        // Return substring starting
        // from start_index
        // and length min_len
        return str.substring(start_index,
                start_index + min_len);
    }

    static void longestKSubstr(String s, int k) {
        int n = s.length();
        int answer = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                HashSet<Character> distinct = new HashSet<Character>();
                for (int x = i; x < j; x++) {
                    distinct.add(s.charAt(x));
                }
                if (distinct.size() == k) {
                    answer = Math.max(answer, j - i);
                }
            }
        }
        System.out.println(answer);
    }

    static int longestPrefixSuffix(String s) {
        int n = s.length();
        // If n is less than 2
        if (n < 2) {
            return 0;
        }
        int len = 0;
        int i = (n + 1) / 2;

        // Iterate i till n
        while (i < n) {
            // If s.charAt(i) is equal to s.charAt(len)
            if (s.charAt(i) == s.charAt(len)) {
                ++len;
                ++i;
            } else {
                i = i - len + 1;
                len = 0;
            }
        }
        return len;
    }


    static void modifyString(String s, String s1, String s2){
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            int k = 0;

            // If the first character of String s1 matches with the
            // current character in String s
            if (s.charAt(i) == s1.charAt(k) && i + s1.length() <= s.length()) {
                int j;
                // If the complete String s1 matches or not with s
                for (j = i; j < i + s1.length(); j++) {
                    if (s.charAt(j) != s1.charAt(k)) {
                        break;
                    }
                    else {
                        k = k + 1;
                    }
                }
                // If complete String matches then replace it with the
                // String s2
                if (j == i + s1.length()) {
                    ans += (s2);
                    i = j - 1;
                }
                else {
                    ans += (s.charAt(i));
                }
            }
            else {
                ans += (s.charAt(i));
            }
        }
        System.out.print(ans);
    }

    static long getSubstringWithEqual012(String s) {

        ArrayList<String> arr = new ArrayList<>();
        int n = s.length();
        // generating subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String s1 = "";
                for (int k = i; k <= j; k++) {
                    s1 = s1+s.charAt(k);
                }
                arr.add(s1);
            }
        }
        int count = 0;
        int countZero, countOnes, countTwo;
        //    iterating over array of all substrings
        for (int i = 0; i < arr.size(); i++) {
            countZero = 0;
            countOnes = 0;
            countTwo = 0;
            String curs = arr.get(i);
            for (int j = 0; j < curs.length(); j++) {
                if (curs.charAt(j) == '0')
                    countZero++;
                if (curs.charAt(j) == '1')
                    countOnes++;
                if (curs.charAt(j) == '2')
                    countTwo++;
            }
            // if number of ones,two and zero are equal in a substring
            if (countZero == countOnes && countOnes == countTwo) {
                count++;
            }
        }

        return count;
    }

    static String res="";

    static void check(String word, String str) {
        int m = word.length();
        int n = str.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (word.charAt(i) == str.charAt(j)) {
                i = i + 1;
                j = j + 1;
            } else {
                j = j + 1;
            }

            if (i == word.length() && res.length() < word.length()) {
                res = word;
            }
        }
    }

    static String LongestWord(ArrayList<String> dict, String str) {
        Collections.sort(dict);
        for (String word : dict) {
            check(word, str);
        }
        return res;
    }
    static boolean isSubsequence(String word, String str) {
        int m = word.length();
        int n = str.length();
        int j = 0;
        for (int i = 0; i < n && j < m; i++) {
            if (word.charAt(j) == str.charAt(i)) {
                j++;
            }
        }
        return (j == m);
    }

    static String findLongestString(Vector<String> dict, String str) {
        String res = "";
        int length = 0;
        for (String word : dict) {
            if (length < word.length() && isSubsequence(word, str)) {
                res = word;
                length = word.length();
            }
        }
        return res;
    }

    static boolean isRotated1(String str1, String str2)
    {
        int n = str1.length();
        int m = str2.length();
        if (n != m) //check both the string length equal or not
            return false;
        boolean clockwise = true;
        boolean anticlockwise = true;
        //check clockwise rotation is possible or not
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i)!= str2.charAt((i + 2) % n)) {
                clockwise = false;
                break;
            }
        }
        // check anticlockwise rotation is possible or not
        for (int i = 0; i < m; i++) {
            if (str1.charAt((i + 2) % n)!= str2.charAt(i)) {
                anticlockwise = false;
                break;
            }
        }
        return (clockwise || anticlockwise);
    }


    static boolean isRotated(String str1, String str2)
    {
        if (str1.length() != str2.length())
            return false;
        if(str1.length() < 2)
        {
            return str1.equals(str2);
        }

        String clock_rot = "";
        String anticlock_rot = "";
        int len = str2.length();

        // Initialize string as anti-clockwise rotation
        anticlock_rot = anticlock_rot + str1.substring(2) + str1.substring(0, 2) ;

        // Initialize string as clock wise rotation
        clock_rot = clock_rot + str1.substring(len-2,len) + str1.substring(0, len-2) ;

        // check if any of them is equal to string1
        return (str2.equals(clock_rot) || str2.equals(anticlock_rot));
    }

    public static int countSub(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        // Iterate from 0 to s.length()
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), -1);
        }
        int allCount = 0;
        int levelCount = 0;

        // Iterate from 0 to s.length()
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Check if i equal to 0
            if (i == 0) {
                allCount = 1;
                map.put(c, 1);
                levelCount = 1;
                continue;
            }
            // Replace levelCount with allCount + 1
            levelCount = allCount + 1;
            // If map is less than 0
            if (map.get(c) < 0) { // checking if map contains that character as initially assigned as -1
                allCount = allCount + levelCount; // Case: No
            } else {
                allCount = allCount + levelCount - map.get(c); //Case: Yes
            }
            map.put(c, levelCount);
        }
        return allCount;
    }


    public static int utility_fun_for_del(String str, int i, int j) {
        if (i >= j)
            return 0;
        if (str.charAt(i) == str.charAt(j)) {
            return utility_fun_for_del(str, i + 1, j - 1);
        }
        // Return value, incrementing by 1
        return 1 + Math.min(utility_fun_for_del(str, i + 1, j), utility_fun_for_del(str, i, j - 1));
    }


    static final int MAX_CHAR = 26;
    static boolean arekAnagrams(String str1, String str2, int k)
    {
        // If both strings are not of equal length then return false
        int n = str1.length();
        if (str2.length() != n)
            return false;

        int[] count1 = new int[MAX_CHAR];
        int[] count2 = new int[MAX_CHAR];
        int count = 0;

        // Store the occurrence of all characters in a hash_array
        for (int i = 0; i < n; i++)
            count1[str1.charAt(i) - 'a']++;
        for (int i = 0; i < n; i++)
            count2[str2.charAt(i) - 'a']++;

        // Count number of characters that are different in both strings
        for (int i = 0; i < MAX_CHAR; i++)
            if (count1[i] > count2[i])
                count = count + Math.abs(count1[i] - count2[i]);

        // Return true if count is less than or equal to k
        return (count <= k);
    }

    static boolean areIsomorphic(String str1, String str2)
    {
        HashMap<Character, Character> map = new HashMap();
        char c = 'a';
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i))) {
                c = map.get(str1.charAt(i));
                if (c != str2.charAt(i))
                    return false;
            }
            else if (!map.containsValue(str2.charAt(i))) {
                map.put(str1.charAt(i), str2.charAt(i));
            }
            else {
                return false;
            }
        }
        return true;
    }

    static int findIndex1(String str)
    {
        int len = str.length();
        int cnt_close = 0;
        for (int i = 0; i < len; i++)
            if (str.charAt(i) == ')')
                cnt_close++;
        for (int i = 0; i < len; i++)
            if (cnt_close == i) // means at this index cnt_close=cnt_close/2 and cnt_open=cnt_close+1
                return i;
        // If no opening brackets
        return len;
    }

    static int findIndex(String str)
    {
        int len = str.length();
        int open[] = new int[len+1];
        int close[] = new int[len+1];
        int index = -1;

        open[0] = 0;
        close[len] = 0;
        if (str.charAt(0)=='(')
            open[1] = 1;
        if (str.charAt(len-1) == ')')
            close[len-1] = 1;

        // Store the number of opening brackets
        // at each index
        for (int i = 1; i < len; i++)
        {
            if ( str.charAt(i) == '(' )
                open[i+1] = open[i] + 1;
            else
                open[i+1] = open[i];
        }

        // Store the number of closing brackets
        // at each index
        for (int i = len-2; i >= 0; i--)
        {
            if ( str.charAt(i) == ')' )
                close[i] = close[i+1] + 1;
            else
                close[i] = close[i+1];
        }

        // check if there is no opening or closing
        // brackets
        if (open[len] == 0)
            return len;
        if (close[0] == 0)
            return 0;

        // check if there is any index at which
        // both brackets are equal
        for (int i=0; i<=len; i++)
            if (open[i] == close[i])
                index = i;

        return index;
    }

    static int shortestDistance(String[] s, String word1, String word2)
    {
        int d1 = -1, d2 = -1;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == word1)
                d1 = i;
            if (s[i] == word2)
                d2 = i;
            if (d1 != -1 && d2 != -1)
                ans = Math.min(ans, Math.abs(d1 - d2)); // min is used for the case if words are repeating
        }
        return ans;
    }

    @Test
    public void test11() {
        String s = "BABABAA";
        int cnt = 0;
        int flag = 0;

        while (s.length() > 0) {
            if (ispalindrome(s)) {
                flag = 1;
                break;
            } else {
                cnt++;
                // erase the last element of the string
                s = s.substring(0, s.length() - 1);
            }
        }
        if (flag == 1) {
            System.out.println(cnt);
        }
    }

    static boolean ispalindrome(String s) {
        int l = s.length();

        for (int i = 0, j = l - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void checkPanagram() {
        String str = "The quick brown fox jumps over the lazy dog";
        boolean[] mark = new boolean[26];

        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                index = str.charAt(i) - 'A';
            } else if ('a' <= str.charAt(i) && str.charAt(i) <= 'z') {
                index = str.charAt(i) - 'a';
            } else {
                continue;
            }

            mark[index] = true;
        }

        boolean isPanagram = true;
        for (int i = 0; i < 26; i++) {
            if (mark[i] == false) {
                isPanagram = false;
            }
        }
        System.out.println(isPanagram ? "is Panagram" : "Not a panagram");
    }

    @Test
    public void printSubSequence() {
        String str = "gfg";
        printSubSeq(str);
    }

    static void printSubSeqRec(String str, int n, int index, String curr) {
        if (index == n) {
            return;
        }
        if (curr != null && !curr.trim().isEmpty()) {
            System.out.print(curr+",");
        }
        for (int i = index + 1; i < n; i++) {
            curr += str.charAt(i);
            printSubSeqRec(str, n, i, curr);
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    static void printSubSeq(String str) {
        int index = -1;
        String curr = "";
        printSubSeqRec(str, str.length(), index, curr);
    }

    @Test
    public void printSubStrings() {
        String str = "gfg";
        printSubstrings(str);
    }

    public static void printSubstrings(String str) {
        int n = str.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(str.charAt(k));
                }
                System.out.print(",");
            }
        }
    }


}
