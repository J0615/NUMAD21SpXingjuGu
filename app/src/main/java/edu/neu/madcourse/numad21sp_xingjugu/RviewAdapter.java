package edu.neu.madcourse.numad21sp_xingjugu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<URLItem> itemList;
    private ItemClickListener listener;

    //Constructor
    public RviewAdapter(ArrayList<URLItem> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.url_item, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        URLItem currentItem = itemList.get(position);

        holder.itemName.setText(currentItem.getURLName());
        holder.itemURL.setText(currentItem.getURLLink());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
