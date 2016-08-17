package com.junbaole.kindergartern.presentation.adapter;

import java.util.ArrayList;

import com.amap.api.services.core.PoiItem;
import com.junbaole.kindergartern.data.model.ShooleInfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class ShooleListAdapter extends RecyclerView.Adapter<ShooleViewHolder> {

    ArrayList<PoiItem> mList;

    public ShooleListAdapter(ArrayList<PoiItem> list) {
        this.mList = list;
    }

    @Override
    public ShooleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new ShooleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShooleViewHolder holder, final int position) {
        holder.textView.setText(getItem(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem(getItem(position));
            }
        });
    }

    public PoiItem getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

   public  abstract void clickItem(PoiItem poiItem);
}

class ShooleViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public ShooleViewHolder(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(android.R.id.text1);
    }

}
