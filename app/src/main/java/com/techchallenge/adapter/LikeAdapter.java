package com.techchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techchallenge.R;
import com.techchallenge.model.LikeResponseModel;

import java.util.ArrayList;

public class LikeAdapter extends  RecyclerView.Adapter<LikeAdapter.holder> {

    private Context context;
    private ArrayList<LikeResponseModel.Profiles> list;



    public LikeAdapter(Context context, ArrayList<LikeResponseModel.Profiles> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public holder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite, null);
        return new holder(view);

    }

    @Override
    public void onBindViewHolder( holder holder, int position) {

        if(list.get(position).getFirst_name()!=null)
        {

            holder.textView1.setText(list.get(position).getFirst_name());
        }
        if(list.get(position).getAvatar()!=null)
        {

            Glide.with(context).load(list.get(position).getAvatar()).placeholder(R.mipmap.photo_1).into(holder.ivProfile);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView ivProfile;

        public holder( View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            ivProfile = itemView.findViewById(R.id.ivProfile);

        }
    }

}
