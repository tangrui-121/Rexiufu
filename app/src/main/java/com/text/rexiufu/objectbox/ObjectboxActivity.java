package com.text.rexiufu.objectbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.text.rexiufu.R;

import java.util.List;

public class ObjectboxActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginName, passWord, desc;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectbox);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.getAll).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.deleteAll).setOnClickListener(this);
        content = findViewById(R.id.content);
        loginName = findViewById(R.id.loginName);
        passWord = findViewById(R.id.passWord);
        desc = findViewById(R.id.desc);

        CacheData.getInstance().deleteAllData(LoginBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (TextUtils.isEmpty(loginName.getText().toString())) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passWord.getText().toString())) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(desc.getText().toString())) {
                    Toast.makeText(this, "请输入描述", Toast.LENGTH_SHORT).show();
                } else {
                    LoginBean loginBean = new LoginBean();
                    loginBean.setLoginName(loginName.getText().toString());
                    loginBean.setPassWord(passWord.getText().toString());
                    loginBean.setDesc(desc.getText().toString());
                    ObjectBoxUtils.addData(loginBean, LoginBean.class);
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getAll:
                getLoginInfo();
                break;
            case R.id.update:
                LoginBean loginBean = new LoginBean();
                loginBean.uid = 1;
                loginBean.setLoginName("张三");
                loginBean.setPassWord("1234qwer");
                loginBean.setDesc("啦啦啦");
                upData(loginBean);
                break;
            case R.id.delete:
                LoginBean delete = new LoginBean();
                delete.uid = 1;
                ObjectBoxUtils.deleteData(delete, LoginBean.class);
                break;
            case R.id.deleteAll:
                ObjectBoxUtils.removeAllData(LoginBean.class);
                break;
        }
    }

    private void getLoginInfo() {
        List<LoginBean> beanList = ObjectBoxUtils.getAllData(LoginBean.class);
        StringBuffer buffer = new StringBuffer();
        if (!beanList.isEmpty()) {
            for (LoginBean loginBean : beanList) {
                buffer.append("loginName:").append(loginBean.getLoginName()).append("\n");
                buffer.append("password:").append(loginBean.getPassWord()).append("\n");
                buffer.append("desc:").append(loginBean.getDesc()).append("\n");
            }
        } else {
            buffer.append("暂无数据");
        }
        content.setText(buffer.toString());
    }

    private void upData(LoginBean loginBean) {
        ObjectBoxUtils.addData(loginBean, LoginBean.class);
    }
}