package com.text.rexiufu;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

public class edittext extends Activity {

    private AlertDialog espDialog;
    private EditText ededed;

    private ImageView button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        initEdit();
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEspPoppup();
            }
        });

    }

    private void initEspPoppup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.popuplayout, null);
        ListView listView = view.findViewById(R.id.server_listview);
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.proposals_esp));
        VpnProposalsAdapter adapter = new VpnProposalsAdapter(this, list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                espDialog.dismiss();
            }
        });
        listView.setAdapter(adapter);
        espDialog = builder.create();
        espDialog.show();
        espDialog.getWindow().setContentView(view);
    }

    private void initEdit() {
        ededed = findViewById(R.id.ededed);


        ededed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.length() > 0) {
//                    for (int i = 0; i < s.length(); i++) {
//                        char c = s.charAt(i);
//                        if (c >= 0x4e00 && c <= 0X9fff) {
//                            s.delete(i, i + 1);
//                        }
//                    }
//                }

//                常用几个空格的 Unicode 码
                String editable = ededed.getText().toString();
                String regEx = "[^!-~\\s]";
//                String regEx = "[^a-zA-Z\\u2002\\u2003\\u00a0 ]";
//
//                \u2002',
//                'emsp': '\u2003',
//                        'nbsp': '\u00a0'
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("");    //删掉不是字母或数字的字符
                if (!editable.equals(str)) {
                    ededed.setText(str);  //设置EditText的字符
                    ededed.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
                }



//                String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//                Pattern pattern = Pattern.compile(speChat);
//                Matcher matcher = pattern.matcher(source.toString());
//                if(matcher.find())return "";
//                else return null;

            }
        });
    }
}