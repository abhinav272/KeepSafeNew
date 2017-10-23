package com.abhinav.keepsafe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.viewholder.ECommerceViewHolder;
import com.abhinav.keepsafe.entities.ECommerce;

import java.util.List;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class ECommerceAdapter extends RecyclerView.Adapter<ECommerceViewHolder> {

    private List<ECommerce> eCommerces;
    private LayoutInflater inflater;
    private AdapterItemClickListener listener;

    public ECommerceAdapter(Context context, List<ECommerce> eCommerces, AdapterItemClickListener listener) {
        this.eCommerces = eCommerces;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ECommerceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_single_text_view, parent, false);
        return new ECommerceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ECommerceViewHolder holder, int position) {
        holder.tvName.setText(getItem(position).getPlatformName());
        holder.tvName.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eCommerces.size();
    }

    private ECommerce getItem(int position) {
        return eCommerces.get(position);
    }
}
