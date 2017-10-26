package com.longyuan.zhihuretrofitvolley.utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.zhihuretrofitvolley.R;
import com.longyuan.zhihuretrofitvolley.pojo.CommentItem;
import com.longyuan.zhihuretrofitvolley.pojo.Story;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by loxu on 19/10/2017.
 */


public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentsListAdapterVewHolder> {


    List<CommentItem> mCommentList;

    Context mContext;

    private OnItemClickListener.OnCommentItemClickListener mOnItemClickListener;

    public CommentsListAdapter(Context context, List<CommentItem> storyList){

        mContext = context;
        mCommentList = storyList;

    }

    @Override
    public CommentsListAdapterVewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);

        return new CommentsListAdapterVewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsListAdapterVewHolder holder, int position) {

        CommentItem commentItem = mCommentList.get(position);

        holder.textViewTitle.setText(commentItem.getContent());

        holder.textViewAuthor.setText(commentItem.getAuthor());

        holder.textViewDate.setText(DateFormat.format("dd-MM-yyyy hh:mm:ss", commentItem.getTime()*1000L).toString());

        Glide.with(mContext).load(commentItem.getAvatar()).into(holder.imageView);


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(commentItem);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public static class CommentsListAdapterVewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.comment_content)
        TextView textViewTitle;

        @BindView(R.id.comment_author)
        TextView textViewAuthor;

        @BindView(R.id.comment_avatar)
        ImageView imageView;


        @BindView(R.id.comment_date)
        TextView textViewDate;


        public CommentsListAdapterVewHolder(View itemView) {
            super(itemView);

            //textViewTitle = (TextView) itemView.findViewById(R.id.news_title);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateData(List<CommentItem> comments){

        mCommentList = comments;

        notifyDataSetChanged();
    }

    public void cleanData(){

        mCommentList.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener.OnCommentItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

