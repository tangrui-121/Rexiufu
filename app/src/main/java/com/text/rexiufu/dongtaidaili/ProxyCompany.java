package com.text.rexiufu.dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyCompany implements InvocationHandler {

    //依旧持有原有对象
    private Object mFactory;

    public void setFactory(Object factory) {
        this.mFactory = factory;
    }

    //获取动态代理对象
    public Object getDynamicProxy() {
        return Proxy.newProxyInstance(mFactory.getClass().getClassLoader(), mFactory.getClass().getInterfaces(), this);
    }

    /**
     * InvocationHandler 接口方法
     *
     * @param proxy  代理类本身
     * @param method 我们所要调用某个对象真实的方法的 Method 对象
     * @param args   method 对象中本身需要传入的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        //调用真实对象的方法
        Object result = method.invoke(mFactory, args);
//        super.h.invoke(this, m3, new Object[]{var1, var2});
        doAfter();
        return result;
    }

    private void doBefore() {
        System.out.println("代理公司——>方案制定");
    }

    private void doAfter() {
        System.out.println("代理公司——>收集反馈");
    }

}
