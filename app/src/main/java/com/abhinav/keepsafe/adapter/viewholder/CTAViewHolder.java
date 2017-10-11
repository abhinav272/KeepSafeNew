package com.abhinav.keepsafe.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhinav.keepsafe.R;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class CTAViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivCTAImage;
    public TextView tvCTATitle;

    public CTAViewHolder(View itemView) {
        super(itemView);
        ivCTAImage = itemView.findViewById(R.id.iv_cta_image);
        tvCTATitle = itemView.findViewById(R.id.tv_cta_title);
    }



}
