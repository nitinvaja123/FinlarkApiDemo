package com.example.nitin.finlarkapidemo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nitin.finlarkapidemo.R;
import com.example.nitin.finlarkapidemo.model.MyPojo;

import java.util.ArrayList;
import java.util.List;

import com.example.nitin.finlarkapidemo.model.MyPojo.Category;
import com.example.nitin.finlarkapidemo.model.MyPojo.Category;
import com.example.nitin.finlarkapidemo.model.MyPojo.Event;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    List<Category> itemList;
    List<Event> eventList;
    private Context context;

    public ItemAdapter(Context context, List<Category> itemList, List<Event> eventList) {
        this.context = context;
        this.itemList = new ArrayList<>();

        this.itemList.add(null);
        this.itemList.addAll(itemList);
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
            return new ViewHolderTwo(view);
        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ONE:
                initLayoutOne((ViewHolderOne) holder, position);
                break;
            case TYPE_TWO:
                initLayoutTwo((ViewHolderTwo) holder, position);
                break;
            default:
                break;
        }
    }

    private void initLayoutTwo(ViewHolderTwo holder, int position) {

        final Category item = itemList.get(position);
        holder.cname.setText(item.getName());
        Glide.with(context).
                load(item.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(holder.cimage);
    }

    private void initLayoutOne(ViewHolderOne holder, int position) {
        holder.viewpager.setAdapter(new CustomPagerAdapter(eventList, context));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }

    }

    private class ViewHolderOne extends RecyclerView.ViewHolder {
        ViewPager viewpager;

        public ViewHolderOne(View view) {
            super(view);
            viewpager = view.findViewById(R.id.viewpager);
        }
    }

    private class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView cname;
        ImageView cimage;

        public ViewHolderTwo(View view) {
            super(view);

            cname = (TextView) view.findViewById(R.id.category_name);
            cimage = (ImageView) view.findViewById(R.id.category_imageview);
        }
    }
}
