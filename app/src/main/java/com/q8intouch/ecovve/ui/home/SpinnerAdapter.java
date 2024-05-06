package com.q8intouch.ecovve.ui.home;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.q8intouch.ecovve.R;
import com.q8intouch.ecovve.network.model.EcovveAllCategoryResponse;
import com.q8intouch.ecovve.util.URLs;


import java.util.List;


public class SpinnerAdapter extends BaseAdapter {
    LayoutInflater inflator;
    List<String> mCounting;
    Context context;
    List<EcovveAllCategoryResponse.Data> myAds;
    public SpinnerAdapter(Context context, List<String> counting, List<EcovveAllCategoryResponse.Data> my_ads) {
        inflator = LayoutInflater.from(context);
        mCounting = counting;
        this.context = context;
        this.myAds = my_ads;
    }
    public SpinnerAdapter(Context context, List<String> counting) {
        inflator = LayoutInflater.from(context);
        mCounting = counting;
        this.context = context;
    }
    public interface ControlsListeners {
        void click(int postion);
    }
    @Override
    public int getCount() {
        return mCounting.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.my_custome_spinner, null);
        TextView tv = (TextView) convertView.findViewById(R.id.spin_item);
        ImageView icon =  convertView.findViewById(R.id.icon);
        if (position < 2 ){
            tv.setText(context.getResources().getString(R.string.select_your_cafe));
            tv.setCompoundDrawables(context.getResources().getDrawable(R.drawable.ic_coffee_cup),null,null,null);
        }
        else if (position!= mCounting.size()){
            if (position != 0 && myAds != null && myAds.size() != 0)
                Glide.with(context).load(URLs.IMAGES_URL + myAds.get(position-2).getImage())/*.error(R.color.colorGreen)*/
                        .into(new SimpleTarget<Drawable>() {//RestClient.IMAGE_IP + "/resize?w=150&h=100&c=1&src=media/companies/  timeLineItem.getPhoto()" +

                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                if (icon != null) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                                }
                                if (resource != null)
                                    icon.setImageDrawable(resource);
                                else
                                    icon.setImageResource(R.drawable.ic_coffee_cup);
                            }
                        });
            if (mCounting.get(position) != null)
                tv.setText(mCounting.get(position));
            else
                tv.setText(R.string.select_your_cafe);
        }
        return convertView;
    }
}