package com.text.rexiufu.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.io.Serializable;

public class MvvmUser extends BaseObservable implements Serializable {
    //单向数据绑定
    //1.BaseObservable
    //2.ObservableField  对基本类型进行了封装，当属性发生改变UI立即刷新
    //3.ObservableCollection 同样封装了map和list

    //双向数据绑定 视图改变后同样更新到对象，如edittext输入改变时更新至对象
    //见ObservableUser
//     当导入的类名一样是  使用alias来区分
//     <data>
//        <import type="com.leavesc.databinding_demo.model.User" />
//        <import
//                alias="TempUser"
//                type="com.leavesc.databinding_demo.model2.User" />
//        <variable
//                name="userInfo"
//                type="User" />
//        <variable
//                name="tempUserInfo"
//                type="TempUser" />
//    </data>


    private static final long serialVersionUID = -8987408018948883178L;
    //如果是public的话 直接在字段上面加修饰符
    //如果是private的话 在get方法上加修饰符
    @Bindable
    public String desc;
    private String name;
    private String password;

    public MvvmUser(String name, String password, String desc) {
        this.name = name;
        this.password = password;
        this.desc = desc;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //只更新本字段
        notifyPropertyChanged(com.text.rexiufu.BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //更新所有
        notifyChange();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}