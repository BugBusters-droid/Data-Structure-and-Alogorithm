import org.testng.annotations.Test;

public class BitManipulation {

    /**
     * https://www.geeksforgeeks.org/bitwise-algorithms/#basics
     * Basic Questions from geeks for geeks
     */

    @Test(description = "first non repeating element")
    public void test1(){
        int[] arr = {1,2,3,1,2,4,2,1};
        int res = 0;

        for(int i=0;i<arr.length;i++){
            res = res^arr[i];
        }

        System.out.println(res);
    }

    @Test(description = "Detect if two integers have opposite signs")
    public void test2() {
        int x = 100;
        int y = 200;
        if ((x ^ y) < 0) {
            System.out.println("number has -ve sign");
        } else {
            System.out.println("number has +ve sign");
        }
    }

    @Test(description = "add 1 in a given number")
    public void test3(){
        int x=100;

        System.out.println(-(~x));
    }

    @Test(description = "multiple a given number by 3.5")
    public void test4(){
        int x = 10;
        //Method 1
        /**
         * We can get x*3.5 by adding 2*x, x and x/2. To calculate 2*x, left shift x by 1 and
         * to calculate x/2, right shift x by 1.
         */
        int result = (x<<1)+x+(x>>1);
        System.out.println(result);

        //Method 2
        /**
         * Another way of doing this could be (8*x – x)/2
         */
        System.out.println(((x<<3)-x)>>1);

        //multiply by 7.5 so left shift by 4
        System.out.println(((x<<4)-x)>>1);
    }

    @Test(description = "Turn off the rightmost set bit")
    public void test5(){
        //Let the input number be x.
        // x-1 would have all the bits flipped after the rightmost set bit (including the set bit).
        // So, doing x&(x-1) would give us the required result.
        int x =5;
        System.out.println(x&(x-1));
    }

    @Test(description = "Find whether a given number is a power of 4 or not")
    public void test6(){
        int n = 16;
        /**
         * A number n is a power of 4 if the following conditions are met.
         * a) There is only one bit set in the binary representation of n (or n is a power of 2)
         * b) The bits don’t AND(&) any part of the pattern 0xAAAAAAAA
         * For example: 16 (10000) is power of 4 because there is only one bit set and 0x10 & 0xAAAAAAAA is zero.
         */

        System.out.println(n != 0 && ((n&(n-1)) == 0) && (n & 0xAAAAAAAA) == 0);
    }

    @Test(description = "Compute n modulo d")
    public void test7(){
        int n=6;
        int d =4;
        System.out.println(n&(d-1));
    }

    @Test(description = "Find the Number Occurring Odd Number of Times")
    public void test8(){
        int[] arr = {5, 7, 2, 7, 5, 2, 5};

        int result=0;
        for(int i=0;i<arr.length;i++){
            result=result^arr[i];
        }
        System.out.println(result);
    }

    @Test(description = "Count set bits in an integer")
    public void test9(){
        int n=6;
        int count = 0;
        while (n > 0) {
            count = count + (n & 1);
            n = n>>1;
        }
        System.out.println(count);
    }

    @Test(description = "Count number of bits to be flipped to convert A to B")
    public void test10(){

        int a =7;
        int b =10;
        // a^b would give the result and then count the bits changed bcz 1^1 and 0^0=0 always (no change required)
        int result = a^b;

        int count=0;
        while (result > 0) {
            count = count + (result & 1);
            result = result>>1;
        }
        System.out.println(count);
    }

    @Test(description = "multiple by 7")
    public void test11(){
        //First left shift the number by 3 bits (you will get 8n) then subtract the original number
        // from the shifted number and return the difference (8n – n).
        int n = 3;
        System.out.println((n<<3)-n);
    }

    @Test(description = "Program to find whether a given number is power of 2")
    public void test12(){
        //2 to the power of 3 is 8
        int n = 8;
        System.out.println(n!=0 && (n&(n-1))==0 ? "Yes":"No");
    }

    @Test(description = "Position of rightmost set bit")
    public void test13(){
        int n = 19;
        int pos = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                System.out.println(++pos);
                break;
            }
            n = n >> 1;
            pos++;
        }
    }


    public void lcm(long n){
        if (n > 1) {
            lcm(n >> 1);
        }
        System.out.print(n & 1);
    }

    @Test(description = "Binary representation of a given number")
    public void test14() {
        long n = 12;
        lcm(n);
    }

    @Test(description = "Find position of the only set bit")
    public void test15(){
       int n =16;
       int count =0;
       int pos=0;

       while(n>0){
           count=count+(n&1);
           n=n>>1;
           pos++;
       }

       if(count>1){
           System.out.println("-1");
       } else{
           System.out.println(pos);
       }
    }

    @Test(description = "swap two numbers without using a temporary variable")
    public void test16(){
        int a=10;
        int b=12;
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a is :: "+a);
        System.out.println("b is :: "+b);
    }

    @Test(description = "turn off a particular bit in a number")
    public void test17(){
        int n=15;
        int k=3;

        int mask=1<<(k-1);
        int inverseOfMask = ~mask;
        System.out.println(n&inverseOfMask);
    }

    @Test(description = "multiply two numbers")
    public void test18(){
        /**
         * 1) Initialize result 'res' as 0.
         * 2) Do following while 'b' is greater than 0
         *    a) If 'b' is odd, add 'a' to 'res'
         *    b) Double 'a' and halve 'b'
         * 3) Return 'res'.
         */
        int a = 10;
        int b = 5;
        int res=0;
        while(b>0){
            if((b&1)==1){
                res=res+a;
            }
            a=a<<1;
            b=b>>1;
        }
        System.out.println(res);
    }

    @Test(description = "two numbers are equal")
    public void test19(){
        int a =5;
        int b =5;
        System.out.println((a&b)==a && (a&b)==b ? "Equal":"Not Equal");
        System.out.println((a^b)==0 ? "Equal":"Not Equal");
    }

    @Test(description = "XOR of two number without using XOR operator")
    public void test20(){
        // & of (x|y), (~x|~y)
        int x=3;
        int y=5;
        System.out.println((x|y)&(~x|~y));
    }

    /**
     * https://www.geeksforgeeks.org/bitwise-algorithms/#intermediate
     * Intermediate level questions from geeks for geeks
     */

    @Test(description = "integer absolute value (abs)")
    public void test1I(){

        final int CHAR_BIT = 8;
        final int SIZE_INT = 8;
        int n=-6;
        int mask = n >> (SIZE_INT * CHAR_BIT - 1);
        System.out.println((n + mask) ^ mask);
    }

    @Test(description = "minimum or maximum of two integers ")
    public void test2I(){
        int x=15;
        int y=6;
        System.out.println("Min is :: "+(y ^ ((x ^ y) & -(x << y))));
        System.out.println("Max is :: "+(x ^ ((x ^ y) & -(x << y))));
    }

    @Test(description = "two non-repeating elements in an array of repeating elements")
    public void test3I(){
        int [] arr = {2, 4, 6, 8, 2, 4};
        //Method 1
//        int[] set1=new int[arr.length];
//        int[] set2 = new int[arr.length];
//        int res=0;
//
//        for(int i=0;i<arr.length;i++){
//            res=res^arr[i];
//        }
//
//        int k=0;
//        for(int i=0;i<arr.length;i++) {
//        if((arr[i]&1)==1){
//            set1[k]=arr[i];
//        } else{
//            set2[k]=arr[i];
//        }
//        k++;
//        }
//
//        int num1=0;
//        int num2=0;
//        for(int i=0;i<set1.length;i++){
//            num1=num1^res^set1[i];
//            num2=num2^res^set2[i];
//        }
//        System.out.println(num1+","+num2);

        //Method 2 (Above implementation does not work if both elements are same type(even/odd))
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = (sum ^ arr[i]);
        }
        sum = (sum & -sum);
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & sum) > 0) {
                sum1 = (sum1 ^ arr[i]);
            } else {
                sum2 = (sum2 ^ arr[i]);
            }
        }
        System.out.println("The non-repeating elements are "
                + sum1 + " and " + sum2);
    }

    @Test(description = "Find Parity")
    public void test4I(){
        int n = 5;
        boolean parity = false;
        while (n != 0) {
            parity = !parity;
            n = n & (n - 1);
        }
        System.out.println("Parity of no  = " + (parity? "odd": "even"));
    }
}
