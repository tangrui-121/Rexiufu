package com.text.rexiufu.dongtaidaili;

public class FactoryA implements IFactoryA {
    @Override
    public void login(String username, String password) {
        System.out.println(username + "出货了" + password + "个车子");
    }
}
