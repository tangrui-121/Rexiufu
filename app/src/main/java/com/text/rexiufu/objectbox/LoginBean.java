package com.text.rexiufu.objectbox;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Unique;

//序列化实体类 Entity必加
@Entity
public class LoginBean {

    //id字段是必要的字段，不可忽略，不可用修饰符修饰
    @Id
    long uid;

    //索引，查询大量数据可加快查询速度
    @Index
    String loginName;

    //存储数据 可看做表字段
    String passWord;

    String desc;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}