package Practice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Stack;

public class Pract {

    String swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }

    void permutation(String s, int l, int r) {
        if (l == r) {
            System.out.println(s + " ");
            return;
        } else {
            for (int i = l; i <= r; i++) {
                s = swap(s.toCharArray(), l, i);
                permutation(s, l + 1, r);
                s = swap(s.toCharArray(), l, i);
            }
        }
    }

    @Test
    public void test1() {
        String s = "abc";
        permutation(s, 0, s.length() - 1);
    }

    void powerSet(String s, String cur, int i) {
        char[] ch = s.toCharArray();
        if (i == s.length()) {
            System.out.print(cur + " ");
            return;
        } else {
            powerSet(s, cur + ch[i], i + 1);
            powerSet(s, cur, i + 1);
        }
    }

    @Test
    public void test2() {
        String s = "abc";
        powerSet(s, "", 0);
    }

    void possibleString(char[] ch, int k, int n, String prefix) {
        if (k == 0) {
            System.out.print(prefix + " ");
            return;
        } else {
            for (int i = 0; i < n; i++) {
                String newPrefix = prefix + ch[i];
                possibleString(ch, k - 1, n, newPrefix);
            }
        }
    }

    @Test
    public void test3() {
        char[] ch = {'A', 'B'};
        int k = 3;
        int n = ch.length;
        String prefix = "";
        possibleString(ch, k, n, prefix);
    }

    int myAtoiRec(String s, int n) {
        if (n == 1) {
            return s.charAt(0) - '0';
        } else {
            return (10 * myAtoiRec(s, n - 1) + s.charAt(n - 1)) - '0';
        }
    }

    @Test
    public void test4() {
        String s = "121";
        int n = s.length();
        System.out.println(myAtoiRec(s, n));
    }

    boolean isPalindrome(String s, int l, int r) {
        char[] ch = s.toCharArray();
        if (l >= r) {
            return true;
        }
        if (ch[l] != ch[r]) {
            return false;
        } else {
            return isPalindrome(s, l + 1, r - 1);
        }
    }

    @Test
    public void test5() {
        String s = "121";
        System.out.println(isPalindrome(s, 0, s.length() - 1));
    }


    int bruteSearch(int[] a, int n, int key) {
        for (int i = 0; i < n; i++) {
            if (a[i] == key) {
                return i;
            }
        }
        return -1;
    }

    int bsIterativeApp(int[] a, int n, int key) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (key > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    int bsRecursiveApp(int[] a, int low, int high, int key) {
        if (low > high) {
            return -1;
        } else {
            int mid = (low + high / 2);
            if (a[mid] == key) {
                return mid;
            }
            if (key > a[mid]) {
                return bsRecursiveApp(a, mid + 1, high, key);
            } else {
                return bsRecursiveApp(a, low, mid - 1, key);
            }
        }
    }

    int bsInInfiniteArray(int[] a, int n, int key) {
        int low = 0;
        int high = 1;
        while (a[high] < key) {
            low = high;
            high = 2 * high;
        }
        return bsRecursiveApp(a, low, high, key);
    }

    int bsInRotatedArray(int a[], int key) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[low] < a[mid]) {
                if (key >= a[low] && key < a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (key > a[mid] && key <= a[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    @Test(description = "binary search")
    public void test6() {
        int[] a = {-4, -1, 3, 7, 10, 11};
        int n = a.length;
        //using brute force
        int index = bruteSearch(a, n, 12);
        System.out.println(index == -1 ? "Key is not present" : index);
        //using binary search - Iterative approach
        int index1 = bsIterativeApp(a, n, 11);
        System.out.println(index1 == -1 ? "Key is not present" : index1);
        //using binary search - Recursive approach
        int index2 = bsRecursiveApp(a, 0, n - 1, -1);
        System.out.println(index2 == -1 ? "Key is not present" : index2);
        //using binary search in infinite array
        int index3 = bsInInfiniteArray(a, n, 7);
        System.out.println(index3 == -1 ? "Key is not present" : index3);
        // binary search in rotated array
        int index4 = bsInRotatedArray(a, 10);
        System.out.println(index4 == -1 ? "Key is not present" : index4);
    }

    int maxOfAll(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    int sumOfAll(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];
        }
        return sum;
    }

    boolean isFeasible(int[] a, int mid, int k) {
        int sum = 0;
        int student = 1;
        for (int i = 0; i < a.length; i++) {
            if (sum + a[i] > mid) {
                student++;
                sum = a[i];
            } else {
                sum = sum + a[i];
            }
        }
        return student <= k;
    }

    int minPages(int a[], int n, int k) {
        int res = 0;
        int low = maxOfAll(a);
        int high = sumOfAll(a);

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isFeasible(a, mid, k)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    @Test(description = "minimum of maximum pages to be allocated to students")
    public void test7() {
        int[] a = {12, 34, 67, 90};
        int k = 2;
        System.out.println(minPages(a, a.length, k));
    }

    boolean opening(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    boolean isMatching(char c1, char c2) {
        return c1 == '{' && c2 == '}' ||
                c1 == '(' && c2 == ')' ||
                c1 == '[' && c2 == ']';
    }

    boolean checkParenthesis(String s) {
        char[] ch = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (opening(ch[i])) {
                stack.push(ch[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (!isMatching(stack.peek(), ch[i])) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return true;
    }

    int maxAreaUsingBruteForce(int[] a) {
        int maxArea = 0;

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
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
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

    int maxHistogram(int[] a) {
        int maxArea = 0;
        int ps[] = prevSmaller(a);
        int ns[] = nextSmaller(a);
        for (int i = 0; i < a.length; i++) {
            int curArea = (ns[i] - ps[i] - 1) * a[i];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }

    @Test(description = "previous smaller element")
    public void test8() {
        String s = "]}";
        System.out.println(checkParenthesis(s));

        int a[] = {4, 2, 1, 5, 6, 3, 2, 4, 2};
        System.out.println(maxAreaUsingBruteForce(a));
        System.out.println(maxHistogram(a));


        int b[][] = {
                {0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 0}
        };

        int cur[] = b[0];
        int maxArea = maxHistogram(cur);
        for (int i = 1; i < b.length; i++) {
            for (int j = 0; j < cur.length; j++) {
                if (b[i][j] == 1) {
                    cur[j] = cur[j] + 1;
                } else {
                    cur[j] = 0;
                }
            }
            int curArea = maxHistogram(cur);
            maxArea = Math.max(curArea, maxArea);
        }
        System.out.println(maxArea);
    }
}
