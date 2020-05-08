package com.text.rexiufu.mvvm;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.text.rexiufu.R;
import com.text.rexiufu.databinding.ActivityMainMvvmBinding;

public class MvvmMainActivity extends AppCompatActivity {

    private ActivityMainMvvmBinding activityMain2Binding;
    private MvvmUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main_mvvm);
        user = new MvvmUser("用户", "123456", "我是用户");
        activityMain2Binding.setUserInfo(user);
        activityMain2Binding.setClientInfo(new MvvmUser("客户", "654321", "我是客户"));
        activityMain2Binding.clientName.setText("我是改变后的客户");//直接获取控件设置,这种情况下在xml里面不能设置text的值为@{clientInfo.password}

        //要想触发点击事件 这句话不能少
        activityMain2Binding.setUserPresenter(new UserPresenter());

        //对象实现了BaseObservable后可以加这个监听 某个属性是否发生了变化
        //只有notifyPropertyChanged方法才会监听到，看源码只有这个刷新才调用了这个callback
        user.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                switch (propertyId) {
                    case com.text.rexiufu.BR.name:
                        Log.e("111", "name");
                        break;
                    case com.text.rexiufu.BR.password:
                        Log.e("111", "password");
                        break;
                    case com.text.rexiufu.BR.desc:
                        Log.e("111", "desc");
                        break;
                    default:
                        Log.e("111", "default");
                        break;
                }
            }
        });


        //双向绑定 edittext输入即刷新
        ObservableUser goods = new ObservableUser("code", 23, "hi");
        activityMain2Binding.setEditBind(goods);
    }

    public void name_click(View view) {
        user.setName("失心疯");
        user.setDesc("啦啦啦");
    }

    public void password_click(View view) {
        user.setPassword("小猫咪");
        user.setDesc("噗噗噗");
    }

    public void desc_click(View view) {
        user.setDesc("滴滴滴");
    }


    //用于点击事件
    public class UserPresenter {
        public void onUserNameClick(MvvmUser user) {
            Log.e("111", "onUserNameClick");
            Toast.makeText(MvvmMainActivity.this, "用户名：" + user.getName(), Toast.LENGTH_SHORT).show();
        }

        public void afterTextChanged(Editable s) {
            Log.e("111", "afterTextChanged");
            user.setName(s.toString());
        }
    }
}