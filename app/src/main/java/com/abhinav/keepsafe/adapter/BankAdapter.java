package com.abhinav.keepsafe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.viewholder.BankViewHolder;
import com.abhinav.keepsafe.entities.Bank;

import java.util.List;

/**
 * Created by abhinav.sharma on 11/10/17.
 */

public class BankAdapter extends RecyclerView.Adapter<BankViewHolder> {

    private LayoutInflater inflater;
    private List<Bank> bankList;
    private AdapterItemClickListener listener;

    public BankAdapter(Context context, List<Bank> bankList, AdapterItemClickListener listener) {
        this.bankList = bankList;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_single_text_view, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BankViewHolder holder, int position) {
        holder.tvName.setText(getItem(position).getBankName());
        holder.tvName.setOnClickListener(v -> {
            if (listener != null)
                listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    public Bank getItem(int position) {
        return bankList.get(position);
    }
}
