package com.text.rexiufu.dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Test {

    static int fib(int n) {
        if (n == 1 || n == 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    static int fib1(int n) {
        if (n < 1) return 0;

        // 备忘录全初始化为 0
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 0);

        // 初始化最简情况
        memo[1] = memo[2] = 1;
        return helper(memo, n);
    }

    static int helper(int[] memo, int n) {
        if (n > 0 && memo[n] == 0)
            memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    static int fib2(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 0);

        memo[1] = memo[2] = 1;
        for (int i = 3; i <= n; i++)
            memo[i] = memo[i - 1] + memo[i - 2];
        return memo[n];
    }

    static int fib3(int n) {
        if (n < 2) return n;
        int prev = 0, curr = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;

    }


    public static void main(String[] args) {

//        char c = s.charAt(" ");
//        System.out.print("cccc = " + c);

        String aaa = null;
        aaa.toString();
//        空的是扩展
//                不空走object



        long time1 = System.currentTimeMillis();
        System.out.print("fib = " + fib(40) + "\n");
        long time2 = System.currentTimeMillis();
        System.out.print("fib 耗时 = " + (time2 - time1) + "\n");

        long time3 = System.currentTimeMillis();
        System.out.print("fib1 = " + fib1(40) + "\n");
        long time4 = System.currentTimeMillis();
        System.out.print("fib1 耗时 = " + (time4 - time3) + "\n");

        long time5 = System.currentTimeMillis();
        System.out.print("fib2 = " + fib2(4) + "\n");
        long time6 = System.currentTimeMillis();
        System.out.print("fib2 耗时 = " + (time6 - time5) + "\n");


//        //创建动态代理对象        
//        ProxyCompany proxyCompany = new ProxyCompany();
//        //公司A        
//        IFactoryA factoryA = new FactoryA();
//        //动态代理引入真实对象        
//        proxyCompany.setFactory(factoryA);
//        //动态的创建代理类        
//        IFactoryA proxyA = (IFactoryA) proxyCompany.getDynamicProxy();
//        proxyA.login("公司A", "800");


//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//
//        IFactoryA userService = (IFactoryA) Proxy.newProxyInstance(IFactoryA.class.getClassLoader(),
//                new Class[]{IFactoryA.class},
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("method = " + method.getName() + " , args = " + Arrays.toString(args));
//                        return null;
//                    }
//                });
//
//        System.out.println(userService.getClass());
//        userService.login("zhy", "123");
    }
}

