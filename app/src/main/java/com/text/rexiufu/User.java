package com.text.rexiufu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Integer age;
    private String name;

    public User() {
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(age, user.age) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public static void main(String[] args) {
//        User user1 = new User(12, "lyf");
//        User user2 = new User(12, "lyf");
//        System.out.println("hashcode值:");
//        System.out.println(user1.hashCode());
//        System.out.println(user2.hashCode());
//        System.out.println("是否相等:");
//        System.out.println(user1.equals(user2));


//        System.out.println(longestPalindrome("adawqwqeqwdsadsss"));

        reverse(1534236469);
    }

    //equals返回true  hashcode一定相同
    //equals返回false 不要求hashcode也不同，但要最好是不同的，以提高哈希表性能
    //重写equals方法，必须重写hashcode方法，以保证equals方法相等时两个对象hashcode返回相同的值


    public static String longestPalindrome(String s) {
        int num = 0;
        String strings = "";
        if (s.length() == 1 || "".equals(s)) {
            return s;
        }
        if (PalindromicString(s)) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (PalindromicString(s.substring(i, j + 1))) {
                    if (s.substring(i, j + 1).length() > num) {
                        num = s.substring(i, j + 1).length();
                        strings = s.substring(i, j + 1);
                    }
                }
            }
        }
        if (num == 0) {
            return s.substring(0, 1);
        }
        return strings;
    }

    static boolean PalindromicString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static int reverse(int x) {
        boolean isfu = false;
        if (x < 0) {
            isfu = true;
        }
        long result = 0;
        for (int i = 0; i < String.valueOf(Math.abs(x)).length(); i++) {
            result += Integer.parseInt(String.valueOf(Math.abs(x)).substring(i, i + 1)) * Math.pow(10, i);

            System.out.println("当前 = " + String.valueOf(x).substring(i, i + 1));
            System.out.println("当前结果 = " + Integer.parseInt(String.valueOf(x).substring(i, i + 1)) * Math.pow(10, i));
            System.out.println("当前result = " + result);
        }

        if (result < -Math.pow(2, 31) || result > (Math.pow(2, 31) - 1)) {
            return 0;
        } else {
            return Math.toIntExact(result);
        }
    }

//    public int reverse(int x) {
//        boolean isfu = false;
//        if (x < 0) {
//            isfu = true;
//        }
//        int result = 0;
//        for (int i = 0; i < String.valueOf(Math.abs(x)).length(); i++) {
//            result += Integer.parseInt(String.valueOf(Math.abs(x)).substring(i, i + 1)) * Math.pow(10, i);
//        }
//        if (isfu)
//            result = -1 * result;
//
//        if (result < (Math.pow(2, 31) * -1) || result > (Math.pow(2, 31) - 1)) {
//            return 0;
//        } else {
//            return result;
//        }
//    }
}