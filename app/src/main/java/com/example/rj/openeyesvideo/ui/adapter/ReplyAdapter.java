package com.example.rj.openeyesvideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.rj.openeyesvideo.model.bean.ReplyBean;
import com.example.rj.openeyesvideo.ui.view.ItemReplyTitleView;
import com.example.rj.openeyesvideo.ui.view.ItemReplyView;
import com.example.rj.openeyesvideo.ui.view.ListEndView;

import java.util.List;

/**
 * Created by rj on 2018/1/11.
 */

public class ReplyAdapter extends BaseRecyclerAdapter<ReplyBean.ItemListBean> {


    public enum ITEM_TYPE{
        TYPE_TITLE,
        TYPE_END,
        TYPE_REPLY,
    }


    public ReplyAdapter(Context context, List<ReplyBean.ItemListBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        if(position==datas.size()){
            return ITEM_TYPE.TYPE_END.ordinal();
        }
        switch (datas.get(position).getType()){
            case "reply":
                return ITEM_TYPE.TYPE_REPLY.ordinal();

            case "leftAlignTextHeader":
                return ITEM_TYPE.TYPE_TITLE.ordinal();

            default:
                return 0;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return super.onCreateViewHolder(parent, viewType);
        View itemView;
        if(viewType==ITEM_TYPE.TYPE_END.ordinal()){
            itemView=new ListEndView(mContext);
            itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        }else if(viewType==ITEM_TYPE.TYPE_TITLE.ordinal()){
            itemView=new ItemReplyTitleView(mContext);
        }else {
            itemView=new ItemReplyView(mContext);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void convert(RecyclerView.ViewHolder holder, int position) {
        View itemView=holder.itemView;
        if(itemView instanceof ItemReplyView){
            ((ItemReplyView) itemView).setData(datas.get(position));
        }else if(itemView instanceof ItemReplyTitleView){
            ((ItemReplyTitleView) itemView).setData(datas.get(position));
        }else if (itemView instanceof ListEndView){

        }
    }


    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void getData(ReplyBean replyBean){
        for (ReplyBean.ItemListBean itemListBean :replyBean.getItemList()){
            if (itemListBean.getType().equals("videoSmallCard")){
                replyBean.getItemList().remove(itemListBean);
            }
        }
        datas=replyBean.getItemList();
    }

    public void getMoreData(ReplyBean replyBean){
        for (ReplyBean.ItemListBean itemListBean :replyBean.getItemList()){
            if (itemListBean.getType().equals("videoSmallCard")){
                replyBean.getItemList().remove(itemListBean);
            }
        }
        Log.d("hzj", "getMoreData: "+datas.size());
        datas.addAll(replyBean.getItemList());
        Log.d("hzj", "getMoreData: "+datas.size());
        notifyDataSetChanged();
    }
}