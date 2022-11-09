import org.testng.annotations.Test;

import java.util.Arrays;

public class MathematicsAndNumberTheoryAlgo {

    @Test(description = "Trailing zeros in a factorial of a number")
    public void test1(){
        /**
         * Suppose 12!= 479001600 so trailing zeros are 2.
         * If factorial of a number is really big that int and long can't handle then we need some other approach
         * Hence, we have to check number 5 coming no of times in factorial to find the trailing zeros
         * For 12, if we divide 12 by 5 then ans is 2 and hence trailing zeros are 2
         * However, we need to construct formula as [n/5]
         * But, what if we do factorial of 30. so it goes like ....5....10....15....20....25....30. Ideally it should be 30/5 and
         * 6 should be ans but if we see closely we have 25 as well which is 5*5 so 5 is repeating twice. hence ans is 7
         *
         * Formula: [n/5]+[n/25]+[n/125]+[n/625]+....
         */

        int n = 12;
        int factorialOfANumber = 1;
        for (int i = 1; i <= n; i++) {
            factorialOfANumber = factorialOfANumber * i;
        }
        System.out.println(factorialOfANumber);
        //optional to implement ^^

        int trailingZeros = 0;
        for (int i = 5; i <= n; i = i * 5) {
            trailingZeros = trailingZeros + (n / i);
        }
        System.out.println(trailingZeros);
    }

    public boolean[] sieveOfEratosthenes(int n){
        //Basically, first we are marking all numbers as true and then checking the multiples less
        // that root of n and marking them false
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        isPrime[0]=false;
        isPrime[1]=false;

        for(int i=2;i*i<=n;i++){
            for(int j=2*i;j<=n;j+=i){
                isPrime[j]=false;
            }
        }
        return isPrime;
    }

    @Test(description = "Prime numbers using sieveOfEratosthenes concept")
    public void test2(){
        //Prime number are those whose factorials are not present in between 1 to root of n
        boolean[] isPrime = sieveOfEratosthenes(20);
        for(int i=0;i<=20;i++){
            if(isPrime[i]==true){
                System.out.println(i);
            }
            //System.out.println(i+" "+isPrime[i]);
        }
    }

    static int GCD(int a, int b){
        /**
         *  GCD is the number which is the biggest factor of both the numbers
         *  For ex: 3 is the biggest factor for 15,27 and 12 is the biggest factor for 12,36
         *  GCD(a,b)=GCD(b,a%b) until a%b==0;
         */
        if(b==0){
            return a;
        } else{
            return GCD(b,a%b);
        }
       // return a%b==0?b:GCD(b,a%b);
    }

    @Test(description = "GCD using euclid method")
    public void test3(){
        System.out.println(GCD(24,60));
    }

    static long fastPower(long a , long b, int n){
        long res=1;
        while(b>0){
            if((b&1)!=0){
                res=(res*a%n)%n;
            }
            a=(a%n * a%n) %n;
            b=b>>1;
        }
        return res;
    }

    @Test(description = "Modulo Arithmatic: Compute a^b%n")
    public void test4(){
        /**
         * a to the power b (a^b)= (a^2)^b/2 if b is even
         *   a*a^b-1 if b is odd
         *   (a*b)%n= (a%n * b%n) %n
         */
        System.out.println(fastPower(3978342,5, 1000000007));
    }

    static int LCMFromGcd(int a , int b){
        int res = 0;
        if(b==0){
            return a;
        } else{
            return LCMFromGcd(b,a%b);
        }
    }
    @Test(description = "LCM of two numbers using GCD")
    public void test5(){
        /**
         *  a x b = LCM(a, b) * GCD (a, b)
         *  LCM(a, b) = (a x b) / GCD(a, b)
         */

        int a=15;
        int b=20;
        int LCM = (a/LCMFromGcd(a,b)) * b;
        System.out.println(LCM);
    }

    static int findGCD(int[] arr){
        int res=arr[0];
        for(int ele:arr){
            res=GCD(ele,res);
            if(res==1){
                return 1;
            }
        }
        return res;
    }

    @Test(description = "GCD of an array")
    public void test6(){
        int[] arr = { 2, 4, 6, 8, 16 };
        System.out.println(findGCD(arr));
    }

    public void productOfFractions(int[] num, int[] den){
        int new_num=1;
        int new_den=1;
        for(int i=0;i<num.length;i++){
            new_num=new_num*num[i];
            new_den=new_den*den[i];
        }

        int gcd = GCD(new_num,new_den);

        new_num=new_num/gcd;
        new_den=new_den/gcd;
        System.out.println(new_num+"/"+new_den);
    }
    @Test(description = "Product of given N fractions in reduced form")
    public void test7(){
        int num[] = { 1, 2, 5 };
        int den[] = { 2, 1, 6 };
        productOfFractions(num,den);
    }



}
