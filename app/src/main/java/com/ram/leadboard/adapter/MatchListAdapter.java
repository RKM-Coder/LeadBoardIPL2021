package com.ram.leadboard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ram.leadboard.R;
import com.ram.leadboard.utils.PairData;

import java.util.ArrayList;
import java.util.Random;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.DiseaseViewHolder> {

    private Context context;
    private ArrayList<PairData<String, String>> listOfItems;
    private ItemOnClickListener itemOnClickListener;
    private boolean flag;

    public MatchListAdapter(Context context, ArrayList<PairData<String, String>> listOfItems){
        this.context = context;
        this.listOfItems = listOfItems;
    }
    public void setResult(boolean flag){
        this.flag=flag;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_match, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        PairData<String, String> pairData=listOfItems.get(position);
        holder.txtTeamName1.setText(pairData.first);
        holder.txtTeamName2.setText(pairData.second);
        if (flag){
            Random rn=new Random();
            int n=rn.nextInt(2);
            //PairData<String, String> pair = result.get(i);
            if (n == 0) {
                  holder.txtTeamNameresult1.setText("WIN");
                  holder.txtTeamNameresult1.setTextColor(Color.GREEN);
                  holder.txtTeamNameresult2.setText("LOSS");
                  holder.txtTeamNameresult2.setTextColor(Color.RED);
            } else if (n == 1) {
                holder.txtTeamNameresult1.setText("LOSS");
                holder.txtTeamNameresult1.setTextColor(Color.RED);
                holder.txtTeamNameresult2.setText("WIN");
                holder.txtTeamNameresult2.setTextColor(Color.GREEN);
            }
        }
      //  holder.txtTeamNameresult1.setText(listOfItems.get(position));
        //holder.txtTeamNameresult2.setText(listOfItems.get(position));

    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    static class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeamName1,txtTeamName2,txtTeamNameresult1,txtTeamNameresult2;
        ConstraintLayout diseaseMain;
        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
           txtTeamName1 = itemView.findViewById(R.id.txt_team_first);
           txtTeamName2 = itemView.findViewById(R.id.txt_team2);
           txtTeamNameresult1 = itemView.findViewById(R.id.txt_result1);
           txtTeamNameresult2 = itemView.findViewById(R.id.txt_result2);

        }
    }

    public interface ItemOnClickListener{
        void onClickItem(View v, int position, String item, ArrayList<String> listOfItem);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public void setValue(ArrayList<PairData<String, String>> listOfItems){
        this.listOfItems.clear();
        this.listOfItems.addAll(listOfItems);
        notifyDataSetChanged();
    }

}
