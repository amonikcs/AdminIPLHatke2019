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

public class ShowMatchResultListAdapter extends RecyclerView.Adapter<ShowMatchResultListAdapter.ImageViewHolder> {
    private Context context;
    private List<AddMatchResultsBeans> list;

    public ShowMatchResultListAdapter(Context context, List<AddMatchResultsBeans> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_match_result_list,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        AddMatchResultsBeans addMatchResultsBeans = list.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
//        holder.datetime.setText("DATETIME - "+addMatchResultsBeans.getDatetime());
        Picasso.with(context)
                .load(addMatchResultsBeans.getTeamLogo1())
                .fit()
                .centerCrop()
                .into(holder.teamLogo1);
        Picasso.with(context)
                .load(addMatchResultsBeans.getTeamLogo2())
                .fit()
                .centerCrop()
                .into(holder.teamLogo2);
        holder.teamName1.setText(addMatchResultsBeans.getTeamName1());
        holder.teamName2.setText(addMatchResultsBeans.getTeamName2());

        holder.team1Score.setText("Score "+addMatchResultsBeans.getTeam1Score());
        holder.team2Score.setText("Score "+addMatchResultsBeans.getTeam2Score());

        holder.team1Overs.setText("Overs "+addMatchResultsBeans.getTeam1overs());
        holder.team2Overs.setText("Overs "+addMatchResultsBeans.getTeam2overs());

        holder.result.setText("Result - "+addMatchResultsBeans.getResults());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView teamName1;
        public TextView teamName2;
        public ImageView teamLogo1;
        public ImageView teamLogo2;
        public TextView team1Score;
        public TextView team2Score;
        public TextView team1Overs;
        public TextView team2Overs;
        public TextView result;
//        public TextView datetime;
        public ImageViewHolder(View itemView) {
            super(itemView);
            teamName1 = itemView.findViewById(R.id.teamName1);
            teamName2 = itemView.findViewById(R.id.teamName2);
            teamLogo1 = itemView.findViewById(R.id.teamLogo1);
            teamLogo2 = itemView.findViewById(R.id.teamLogo2);

            team1Score = itemView.findViewById(R.id.team1Score);
            team2Score = itemView.findViewById(R.id.team2Score);
            team1Overs = itemView.findViewById(R.id.team1Overs);
            team2Overs = itemView.findViewById(R.id.team2Overs);
//            datetime = itemView.findViewById(R.id.datetime);
            result = itemView.findViewById(R.id.result);
        }
    }
}
