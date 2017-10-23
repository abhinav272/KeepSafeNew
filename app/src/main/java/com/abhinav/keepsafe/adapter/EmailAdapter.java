package com.abhinav.keepsafe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.keepsafe.R;
import com.abhinav.keepsafe.adapter.viewholder.EmailViewHolder;
import com.abhinav.keepsafe.entities.Email;

import java.util.List;

/**
 * Created by abhinav.sharma on 13/10/17.
 */

public class EmailAdapter extends RecyclerView.Adapter<EmailViewHolder> {

    private LayoutInflater inflater;
    private List<Email> emails;
    private AdapterItemClickListener listener;

    public EmailAdapter(Context context, List<Email> emails, AdapterItemClickListener listener) {
        this.emails = emails;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EmailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_single_text_view, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmailViewHolder holder, int position) {
        holder.tvName.setText(getItem(position).getEmailId());
        holder.tvName.setOnClickListener(v -> {
            if (listener != null)
                listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    private Email getItem(int position) {
        return emails.get(position);
    }
}
