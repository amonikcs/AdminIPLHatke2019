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

import java.text.SimpleDateFormat;
import java.util.List;

public class ShowTeamPlayerDetailsListAdapter extends RecyclerView.Adapter<ShowTeamPlayerDetailsListAdapter.ImageViewHolder> {
    private Context context;
    private List<AddPlayerNameBeans> list;

    public ShowTeamPlayerDetailsListAdapter(Context context, List<AddPlayerNameBeans> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_player_list,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        AddPlayerNameBeans addPlayerNameBeans = list.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
//        holder.datetime.setText("DATETIME - "+addMatchResultsBeans.getDatetime());
        Picasso.with(context)
                .load(addPlayerNameBeans.getPlayerLogo())
                .fit()
                .centerCrop()
                .into(holder.playerLogo);
        holder.playerName.setText(addPlayerNameBeans.getPlayerName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView playerLogo;
        public TextView playerName;
//        public TextView datetime;
        public ImageViewHolder(View itemView) {
            super(itemView);
            playerLogo = itemView.findViewById(R.id.playerLogo);
            playerName = itemView.findViewById(R.id.playerName);

        }
    }
}
