package com.q8intouch.ecovve.ui.home.slider;
import android.graphics.Bitmap;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;
import com.q8intouch.ecovve.R;

public class SliderCard extends RecyclerView.ViewHolder implements DecodeBitmapTask.Listener {

    private static int viewWidth = 0;
    private static int viewHeight = 0;

//    private final ImageView imageView;
    public TextView txtRate,txtItemName,txtItemPrice,txtFavCount,txtShopName,txtShopAddress;
  public   Button btnAddToCart;
  public   ImageView imgShopLogo;
  public   ImageView cover;

    private DecodeBitmapTask task;

    public SliderCard(View itemView) {
        super(itemView);
//        imageView = (ImageView) itemView.findViewById(R.id.image);
        txtRate =  itemView.findViewById(R.id.txtRate);
        imgShopLogo =  itemView.findViewById(R.id.imgShopLogo);
        txtShopName =  itemView.findViewById(R.id.txtShopName);
        txtShopAddress =  itemView.findViewById(R.id.txtShopAddress);
        txtItemName =  itemView.findViewById(R.id.txtItemName);
        txtItemPrice =  itemView.findViewById(R.id.txtItemPrice);
        txtFavCount =  itemView.findViewById(R.id.txtFavCount);
        btnAddToCart =  itemView.findViewById(R.id.btnAddToCart);
        cover =  itemView.findViewById(R.id.imageView3);
    }

    void setContent(@DrawableRes final int resId) {
//        if (viewWidth == 0) {
//            itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//                    viewWidth = itemView.getWidth();
//                    viewHeight = itemView.getHeight();
//                    loadBitmap(resId);
//                }
//            });
//        } else {
//            loadBitmap(resId);
//        }
    }

    void clearContent() {
        if (task != null) {
            task.cancel(true);
        }
    }

    @Override
    public void onPostExecuted(Bitmap bitmap) {

    }

//    private void loadBitmap(@DrawableRes int resId) {
//        task = new DecodeBitmapTask(itemView.getResources(), resId, viewWidth, viewHeight, this);
//        task.execute();
//    }
//
//    @Override
//    public void onPostExecuted(Bitmap bitmap) {
//        imageView.setImageBitmap(bitmap);
//    }

}