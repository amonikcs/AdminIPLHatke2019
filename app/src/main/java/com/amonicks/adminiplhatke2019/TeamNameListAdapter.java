package com.amonicks.adminiplhatke2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamNameListAdapter extends RecyclerView.Adapter<TeamNameListAdapter.ImageViewHolder> {
    private Context context;
    private List<AddTeamNameBeans> list;

    public TeamNameListAdapter(Context context, List<AddTeamNameBeans> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_name_list,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        AddTeamNameBeans addTeamNameBeans = list.get(position);
        Picasso.with(context)
                .load(addTeamNameBeans.getTeamLogo())
                .fit()
                .centerCrop()
                .into(holder.teamLogo);
        holder.teamName.setText(addTeamNameBeans.getTeamName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView teamName;
        public ImageView teamLogo;
        public ImageViewHolder(View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.teamName);
            teamLogo = itemView.findViewById(R.id.teamLogo);
        }
    }
}
