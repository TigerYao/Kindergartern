package com.junbaole.kindergartern.presentation.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.CommentModel;
import com.junbaole.kindergartern.databinding.CommentsAdaperLayoutBinding;

import java.util.ArrayList;

/**
 * Created by yaohu on 16/8/29.
 */
public class CommentsAdaper extends RecyclerView.Adapter<CommentsAdaper.CommentsViewHolder> {

    ArrayList<CommentModel> commentModelArrayList;

    public CommentsAdaper(ArrayList<CommentModel> commentModelArrayList) {
        setCommentModelArrayList(commentModelArrayList);
    }

    public void setCommentModelArrayList(ArrayList<CommentModel> commentModelArrayList) {
        if(commentModelArrayList==null){
            commentModelArrayList = new ArrayList<>();
        }
        this.commentModelArrayList = commentModelArrayList;
        notifyDataSetChanged();
    }

    public void addCommentsList(ArrayList<CommentModel> commentModelArrayList){
        if(commentModelArrayList==null||commentModelArrayList.size()==0){
            return;
        }
        this.commentModelArrayList.addAll(getItemCount(),commentModelArrayList);
        notifyItemRangeInserted(getItemCount(),commentModelArrayList.size());

    }
    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_adaper_layout,null));
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.bindData(commentModelArrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{
        CommentsAdaperLayoutBinding commentsAdaperLayoutBinding;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            commentsAdaperLayoutBinding = DataBindingUtil.bind(itemView);
        }
        public void bindData(CommentModel commentModel){
            commentsAdaperLayoutBinding.setComment(commentModel);
        }
    }
}
