package com.example.myretrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myretrofit.Model.DataModel;
import com.example.myretrofit.R;

import java.util.List;
//memanggil data dari database
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{

    private Context ctx;
    private List<DataModel> listData;

    public AdapterData(Context ctx, List<DataModel> listData) {

        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item , parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);
        holder.tvid.setText(String.valueOf(dm.getId()));
        holder.tvnama.setText(dm.getUser_fullname());
        holder.tvemail.setText(dm.getUser_email());
        holder.tvpassword.setText(dm.getUser_password());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvnama,tvemail,tvid,tvpassword;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvemail = itemView.findViewById(R.id.tv_email);
            tvpassword = itemView.findViewById(R.id.tv_password);
        }
    }
//    @NonNull
//    @Override
//    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item , parent, false);
//        HolderData holder = new HolderData(layout);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HolderData holder, int position) {
//        DataModel dm = listData.get(position);
//        holder.tvid.setText(String.valueOf(dm.getId()));
//        holder.tvnama.setText(dm.getUser_fullname());
//        holder.tvemail.setText(dm.getUser_email());
//        holder.tvpassword.setText(dm.getUser_password());
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }

}
