package com.text.rexiufu.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.text.rexiufu.R;

import java.util.List;


//关于在外部拿到RecyclerView的viewholder的几种方式
//RecyclerView跳转到指定位置的三种方式
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    List<String> datas;

    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_recyclerview, null);
        //返回MyViewHolder的对象
        return new MyViewHolder(v);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.textView.setText(datas.get(position));

        //直接对控件做点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,holder.textView.getText()+"被点击了",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //通过接口回调响应点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,position);
                }
            }
        });
        //通过接口回调响应长按事件
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onItemLongClickListener != null){
                    return onItemLongClickListener.onItemLongClick(view,position);
                }
                return false;
            }
        });
    }

    //返回Item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }




    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /**
     * 设置回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    /**
     * 设置长按回调接口
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
