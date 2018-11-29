package com.example.nitin.finlarkapidemo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nitin.finlarkapidemo.R;
import com.example.nitin.finlarkapidemo.model.MyPojo.Event;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Event> mlist;

    public CustomPagerAdapter(List<Event> mlist, Context context) {
        mContext = context;
        this.mlist = mlist;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((CardView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.event_imageview);
        TextView eventname = itemView.findViewById(R.id.event_name);
        eventname.setText(mlist.get(position).getName());
        Glide.with(mContext).
                load(mlist.get(position).getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(imageView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }
}
