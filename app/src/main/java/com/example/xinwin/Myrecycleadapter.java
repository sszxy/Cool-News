package com.example.xinwin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 张翔宇 on 2018/4/8.
 */

public class Myrecycleadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<news.ResultBean.ListBean> newsList;
    Context context;
    int viewtype;
    private boolean hasmore=true;
    public onItemClickListener listener;
    static class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,textView3;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.newsimg);
            textView1=(TextView) itemView.findViewById(R.id.titletext);
            textView2=(TextView)itemView.findViewById(R.id.contenttext);
            textView3=(TextView)itemView.findViewById(R.id.timetext);
        }
    }
    static class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView textview;
        public ViewHolder2(View itemView) {
            super(itemView);
            textview=itemView.findViewById(R.id.refreshtv);
        }
    }

    public Myrecycleadapter(List<news.ResultBean.ListBean> newses,Context context2){
        newsList=newses;
        context=context2;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==1){
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);
        return new ViewHolder1(itemview);
        }
        else if(viewType==0) {
            View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.refreshitem,parent,false);
            return new ViewHolder2(itemview);
        }
        return null;
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolder1){
            ViewHolder1 viewholder=(ViewHolder1) holder;
            news.ResultBean.ListBean n=newsList.get(position);
            Glide.with(context).load(n.getPic()).placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.defaultpic2).into(viewholder.imageView);
            viewholder.textView1.setText(n.getTitle());
            viewholder.textView2.setText(n.getContent());
            viewholder.textView3.setText(n.getTime());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemclick(holder.itemView,position);
            }
          });
        }else if(holder instanceof ViewHolder2){
            ViewHolder2 viewholder=(ViewHolder2) holder;
            //viewholder.itemView.setVisibility(View.VISIBLE);
            if(hasmore==true)
            viewholder.textview.setText("正在加载...");
            else {
                viewholder.textview.setText("没有更多");
            }
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size()+1;
    }
    public interface onItemClickListener{
        void onItemclick(View v,int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==newsList.size()){
            viewtype=0;
        }else
        {
            viewtype=1;
        }
        return viewtype;
    }
    public void sethasmore(boolean a){
        hasmore=a;
    }
}
