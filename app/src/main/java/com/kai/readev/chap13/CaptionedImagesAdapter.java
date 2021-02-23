package com.kai.readev.chap13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kai.readev.R;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
        }
    }

    public interface OnTileClickListener {
        void onClick(int position);
    }

    private OnTileClickListener onTileClickListener;
    private final String[] captions;
    private final int[] imageIds;

    public void setOnTileClickListener(OnTileClickListener onTileClickListener) {
        this.onTileClickListener = onTileClickListener;
    }

    public CaptionedImagesAdapter(String[] captions, int[] images) {
        this.captions = captions;
        this.imageIds = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cv = (CardView) holder.itemView;

        ImageView iv = cv.findViewById(R.id.info_image);
        iv.setImageResource(imageIds[position]);
        TextView tv = cv.findViewById(R.id.info_text);
        tv.setText(captions[position]);

        cv.setOnClickListener(v -> {
            if (onTileClickListener != null) onTileClickListener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }


}
