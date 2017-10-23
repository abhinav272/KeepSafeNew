package com.abhinav.keepsafe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.viewholder.SocialNetworkViewHolder;
import com.abhinav.keepsafe.entities.SocialNetwork;

import java.util.List;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkViewHolder>{

    private List<SocialNetwork> socialNetworks;
    private LayoutInflater inflater;
    private AdapterItemClickListener listener;

    public SocialNetworkAdapter(Context context, List<SocialNetwork> socialNetworks, AdapterItemClickListener listener) {
        this.socialNetworks = socialNetworks;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SocialNetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_single_text_view, parent, false);
        return new SocialNetworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SocialNetworkViewHolder holder, int position) {
        holder.tvName.setText(getItem(position).getPlatformName());
        holder.tvName.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return socialNetworks.size();
    }

    private SocialNetwork getItem(int position) {
        return socialNetworks.get(position);
    }
}
