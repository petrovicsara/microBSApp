package com.example.microbsentryapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.microbsentryapp.R;
import com.example.microbsentryapp.model.Zaposleni;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListWorkersAdapter extends RecyclerView.Adapter<ListWorkersAdapter.ViewHolder>{
    Context context;
    private ArrayList<Zaposleni> zaposleniArrayList;

    public ListWorkersAdapter() {

    }

    public ListWorkersAdapter(Context context, ArrayList<Zaposleni> zaposleniArrayList) {
        this.context = context;
        this.zaposleniArrayList = zaposleniArrayList;
    }

    public void setZaposleniData(ArrayList<Zaposleni> zaposleniData){
        this.zaposleniArrayList = zaposleniData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.itemView.setTag(zaposleniArrayList.get(position));
        holder.tvWorkerName.setText(zaposleniArrayList.get(position).getName());
        holder.tvWorkerSurname.setText(zaposleniArrayList.get(position).getSurname());
        holder.tvStorages.setText(zaposleniArrayList.get(position).getStorageUnits());
    }

    @Override
    public int getItemCount() {
        if (zaposleniArrayList == null) {
            return 0;
        } else return zaposleniArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWorkerName, tvWorkerSurname, tvStorages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWorkerName = itemView.findViewById(R.id.tvWorkerName);
            tvWorkerSurname = itemView.findViewById(R.id.etSurname);
            tvStorages = itemView.findViewById(R.id.tvStorages);
        }
    }
}
