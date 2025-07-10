package com.kasihinapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.ViewHolder> {

    private List<MetodePembayaran> list;
    private int lastSelectedPosition = -1;

    public PembayaranAdapter(List<MetodePembayaran> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pembayaran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MetodePembayaran metode = list.get(position);
        holder.logo.setImageResource(metode.getLogoResId());
        holder.title.setText(metode.getNama());
        holder.subtitle.setText(metode.getDeskripsi());
        holder.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // --- METHOD BARU DITAMBAHKAN DI SINI ---
    /**
     * Mengembalikan item yang sedang dipilih di dalam adapter.
     * @return MetodePembayaran yang dipilih, atau null jika tidak ada yang dipilih.
     */
    public MetodePembayaran getSelectedItem() {
        if (lastSelectedPosition != -1) {
            return list.get(lastSelectedPosition);
        }
        return null;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView title, subtitle;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logoImageView);
            title = itemView.findViewById(R.id.titleTextView);
            subtitle = itemView.findViewById(R.id.subtitleTextView);
            radioButton = itemView.findViewById(R.id.radioButton);

            View.OnClickListener listener = v -> {
                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
            };
            itemView.setOnClickListener(listener);
            radioButton.setOnClickListener(listener);
        }
    }
}
