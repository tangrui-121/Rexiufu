package com.text.rexiufu.behavior;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.text.rexiufu.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {

    private TextView mTvText;
    private String value;
    private RecyclerView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mTvText = view.findViewById(R.id.mTvText);
        listview = view.findViewById(R.id.listview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        if (value != null) {
            mTvText.setText(value);
        }
        List<String> aaa = new ArrayList<>();
        for (int a = 0; a < 100; a++) {
            aaa.add(a + "个");
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),aaa);
        listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        listview.setAdapter(adapter);
//        listview.setAdapter(new ListAdapter(aaa, getActivity()));
    }

    public static TabFragment newInstance(String data) {
        TabFragment myFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("myFragment", data);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            value = bundle.getString("myFragment");
        }
    }
}