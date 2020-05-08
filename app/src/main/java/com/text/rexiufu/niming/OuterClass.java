package com.text.rexiufu.niming;

public class OuterClass {

    int value1 = 5;

    private void outerMethod() {
        System.out.println("It's Method of OuterClass");
    }

    private void iiii() {
        System.out.println("It's Method of iii");
    }

    public void main(String[] args) {
        OuterClass t = new OuterClass();
        OuterClass.Innerclass in = t.new Innerclass();
        in.innerMethod();

//        Innerclass mmm = new Innerclass();//不能在static方法中new内部类
        //why：静态方法是在类实例化之前就可以使用的，通过类名调用，此时内部类还没实例化，总不能调用一个不存在的类吧

        int value2 = 7;

        //内部类访问的外部类的变量必须声明为final
        //内部类和外部类是同一级别的，内部类不会因为定义在方法中就随着方法的执行完毕而被销毁。
        //如果外部类的变量不定义为final，当外部类方法执行完毕后这个局部变量坑定也被GC了
        //然而这时内部类的某些方法还没执行完，这时它所引用的外部变量已经找不到了
        //如果定义为final，java会将这个变量复制一份作为内部类的成员变量，并且由于final变量的值无法改变，所以这个变量指向的内存地址就不会改变。


        class Innerclass_1 {
            public void innerMethod() {
                System.out.println(value2);
            }
        }

//        System.out.println(value1);


        //匿名类
        //必须有实现不能直接();
        Father father = new Father() {

            @Override
            public void eat() {

            }
        };
        father.eat();

        new Father1().say();
        Father father1 = new Father1();
        father1.eat();

        Mother mother = new Mother1();
        mother.drink();

        Mother mother1 = new Mother() {
            @Override
            public void drink() {

            }
        };
        mother1.drink();
    }

    class Innerclass {
        public void innerMethod() {
            iiii();//无同名void可直接调用
            OuterClass.this.outerMethod();// 内部类成员方法与外部类成员方法同名时，使用this调用外部类的方法
            outerMethod();// 内部类没有同名方法时执行外部类的方法

            System.out.println(value1);
        }

        private void outerMethod() {
            System.out.println("It's Method of Innerclass");
        }
    }

    class Father1 extends Father {
        public void say() {// 匿名内部类自定义的方法say
            System.out.println("say方法调用");
        }

        @Override
        public void eat() {

        }//匿名类必须继承或者实现一个   且最多只能一个

    }

    class Mother1 implements Mother{

        public void say() {// 匿名内部类自定义的方法say
            System.out.println("say方法调用");
        }

        @Override
        public void drink() {

        }
    }

    abstract class Father {//抽象类是不能直接new的
        public abstract void eat();

    }

    interface Mother{
        public void drink();
    }
}