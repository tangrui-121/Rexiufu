package com.text.rexiufu.banner;

import org.json.JSONObject;

public class Test {

    private String username, city, sign, classify;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public static Test parseFromJson(String str) {
        try {
            //为了处理使用windows编辑json文件而带了bom头而导致的string无法转化成jsonobject
            if (str != null && str.startsWith("\ufeff")) {
                str = str.substring(1);
            }
            JSONObject jsonObj = new JSONObject(str);
            Test user = new Test();
            user.setUsername(jsonObj.optString("username"));
            user.setCity(jsonObj.optString("city"));
            user.setSign(jsonObj.optString("sign"));
            user.setClassify(jsonObj.optString("classify"));
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    { "code": 200,
//            "msg": "OK",
//            "muser": [
//        {"name": "zhangsan","age": "10","phone": "11111","email":"11111@11.com"},
//        {"name": "lisi","age": "20","phone": "22222","email": "22222@22.com"},
//        ...]
//    }
}