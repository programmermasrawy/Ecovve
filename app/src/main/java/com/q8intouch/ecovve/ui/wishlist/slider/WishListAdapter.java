package com.q8intouch.ecovve.ui.wishlist.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.q8intouch.ecovve.R;
import com.q8intouch.ecovve.network.model.EcovveUserFavorites;
import com.q8intouch.ecovve.util.URLs;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<SliderCard> {

    private final int count;
    private List<EcovveUserFavorites.Data.Favorite> suborders;
//    private final View.OnClickListener listener;
    Context context;
    ControlsListeners listeners;
    public WishListAdapter(List<EcovveUserFavorites.Data.Favorite> suborders, int count, Context context, ControlsListeners listeners) {
        this.suborders = suborders;
        this.count = count;
        this.listeners=listeners;
        this.context = context;
    }

    public WishListAdapter(int count, ControlsListeners listeners) {
        this.count = count;
        this.listeners=listeners;
    }
    public interface ControlsListeners {
        void click(int postion);
    }
    @Override
    public SliderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_slider_card, parent, false);

//        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
//        }
        return new SliderCard(view);
    }

    @Override
    public void onBindViewHolder(SliderCard holder, final int position) {
        if (suborders != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listeners.click(position);
                }
            });
            holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listeners.click(position);
                }
            });
            holder.txtRate.setText(suborders.get(position).getReviewsCount() + "");
            holder.txtItemName.setText(suborders.get(position).getName() + "");
            holder.txtItemPrice.setText(suborders.get(position).getPrice() + "");
            holder.txtItemPrice.setText(suborders.get(position).getPrice() + "");
            holder.txtShopName.setText(suborders.get(position).getBrand().getName() + "");
            holder.txtShopAddress.setText(suborders.get(position).getBrand().getAddress() + "");
            if (!suborders.get(position).getBrand().getLogo().isEmpty())
            if (!suborders.get(position).getBrand().getLogo().contains("http"))
            Glide.with(context).load(URLs.IMAGES_URL+ suborders.get(position).getBrand().getLogo()).into(holder.imgShopLogo);
            else
            Glide.with(context).load(suborders.get(position).getBrand().getLogo()).into(holder.imgShopLogo);

            if (suborders.get(position).getImage().size()!=0)
                if (!suborders.get(position).getImage().get(0).contains("http"))
                    Glide.with(context).load(URLs.IMAGES_URL+ suborders.get(position).getImage().get(0)).into(holder.cover);
                else
                    Glide.with(context).load(suborders.get(position).getImage().get(0)).into(holder.cover);


        }
    }

    @Override
    public void onViewRecycled(SliderCard holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return count;
    }

}
