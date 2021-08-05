package net.smallacademy.authenticatorapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RestsAdapter extends RecyclerView.Adapter<RestsAdapter.ViewHolder>{
    Context context;
    ArrayList<Restaurant> items;

    public RestsAdapter( ) {
        items=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant,parent,false);
        return new ViewHolder(v);
    }
    public void setList(ArrayList<Restaurant> list){
        this.items=list;
        Log.e("Received Bookings",list.size()+"");
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant item=items.get(position);
        holder.rest_name.setText(item.getName());
        holder.rest_food.setText(item.getFood());
        holder.rest_time.setText(item.getWait_time());
        Picasso.get().load(item.getLogo()).placeholder(R.mipmap.ic_launcher).into(holder.iv_logo);
    }

    @Override
    public int getItemCount() {
        if(items==null){
            items=new ArrayList<>();
        }
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rest_name,rest_food,rest_time;
        ImageView iv_logo;
        LinearLayout ll_admin_edit;
        public ViewHolder(@NonNull View v) {
            super(v);
            iv_logo=v.findViewById(R.id.iv_logo);
            rest_name=v.findViewById(R.id.rest_name);
            rest_food=v.findViewById(R.id.rest_food);
            rest_time=v.findViewById(R.id.rest_time);
        }
    }
}
