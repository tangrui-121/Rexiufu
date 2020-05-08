package com.text.rexiufu.niming;

public class TextAc {

    public static void main(String[] args) {
        aaa(5);
    }


    public static void aaa(int num) {

        DateBean bean = new DateBean("nihao");

        Inout inout = new Inout() {
            @Override
            void dosomething() {
//                num = 7;
                bean.mBean = "sdsds";
                System.out.print("num = " + num);
                System.out.print("bean = " + bean.mBean);
            }
        };
        inout.dosomething();
    }

    public static class Inout {
        void dosomething() {

        }
    }

    public static class DateBean {
        String mBean;

        public DateBean(String bean) {
            this.mBean = bean;
        }
    }
}