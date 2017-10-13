package com.abhinav.keepsafe.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhinav.keepsafe.R;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class EmailViewHolder extends RecyclerView.ViewHolder{

    public TextView tvName;

    public EmailViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }
}
