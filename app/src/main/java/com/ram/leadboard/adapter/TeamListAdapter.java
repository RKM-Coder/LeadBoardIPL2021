package com.ram.leadboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.ram.leadboard.R;

import java.util.ArrayList;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.DiseaseViewHolder> {

    private Context context;
    private ArrayList<String> listOfItems;
    private ItemOnClickListener itemOnClickListener;

    public TeamListAdapter(Context context, ArrayList<String> listOfItems){
        this.context = context;
        this.listOfItems = listOfItems;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_team, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        holder.txtTeamName.setText(listOfItems.get(position));

    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    static class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeamName;
        ConstraintLayout diseaseMain;
        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
           txtTeamName = itemView.findViewById(R.id.txt_teamname);

        }
    }

    public interface ItemOnClickListener{
        void onClickItem(View v, int position, String item, ArrayList<String> listOfItem);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public void setValue(ArrayList<String> listOfItems){
        this.listOfItems.clear();
        this.listOfItems.addAll(listOfItems);
        notifyDataSetChanged();
    }

}
