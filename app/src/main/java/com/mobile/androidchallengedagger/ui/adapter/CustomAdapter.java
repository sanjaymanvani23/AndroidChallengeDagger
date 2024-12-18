package com.mobile.androidchallengedagger.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.androidchallengedagger.R;
import com.mobile.androidchallengedagger.Utils.Util;
import com.mobile.androidchallengedagger.data.model.Article;

import com.mobile.androidchallengedagger.ui.interfaces.OnItemClickListener;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    List<Article> list;

    Context mContext;

    OnItemClickListener mClickListiner;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDate,textTitle,textDesc;
        ImageView mImageView;

        CardView mCardViewMain;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textViewDate = (TextView) view.findViewById(R.id.textDate);
            textDesc = (TextView) view.findViewById(R.id.textDesc);
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            mImageView = view.findViewById(R.id.imageview);

            mCardViewMain = view.findViewById(R.id.main);

        }


    }

    public CustomAdapter(List<Article> list, Context mContext, OnItemClickListener mClickListiner) {
        this.list = list;
        this.mContext = mContext;
        this.mClickListiner = mClickListiner;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewDate.setText(Util.getDateFormat(list.get(position).getPublishedAt()));
        viewHolder.textTitle.setText(list.get(position).getTitle());
        viewHolder.textDesc.setText(list.get(position).getDescription());
        Glide.with(mContext).load(list.get(position).getUrlToImage()).into(viewHolder.mImageView);

        viewHolder.mCardViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListiner.onItemClick(list.get(position));
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}