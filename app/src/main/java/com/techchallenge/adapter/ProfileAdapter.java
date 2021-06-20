package com.techchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techchallenge.R;
import com.techchallenge.model.InviteModel;

import java.util.ArrayList;

public class ProfileAdapter extends  RecyclerView.Adapter<ProfileAdapter.holder> {

    private Context context;
    private ArrayList<InviteModel.Profile> list;



    public ProfileAdapter(Context context, ArrayList<InviteModel.Profile> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ProfileAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, null);
        return new ProfileAdapter.holder(view);

    }

    @Override
    public void onBindViewHolder(ProfileAdapter.holder holder, int position) {

        if(list.get(position).getGeneral_information().getFirst_name()!=null)
        {

            holder.textView2.setText(list.get(position).getGeneral_information().getFirst_name());
        }
        if(list.get(position).getGeneral_information().getAge()!=0)
        {

            holder.textView3.setText(", "+list.get(position).getGeneral_information().getAge());
        }
        if(list.get(position).getPhotos().get(0).photo!=null)
        {

            Glide.with(context).load(list.get(position).getPhotos().get(0).photo).placeholder(R.mipmap.photo_1).into(holder.ivProfile);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;
        ImageView ivProfile;

        public holder( View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            ivProfile = itemView.findViewById(R.id.ivProfile);

        }
    }

}

