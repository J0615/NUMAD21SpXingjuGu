package edu.neu.madcourse.numad21sp_xingjugu;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemURL;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        itemName = itemView.findViewById(R.id.link_name);
        itemURL = itemView.findViewById(R.id.link_url);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}