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

public class TeamTimeTableListAdapter extends RecyclerView.Adapter<TeamTimeTableListAdapter.ImageViewHolder> {
    private Context context;
    private List<AddTimeTableBeans> list;

    public TeamTimeTableListAdapter(Context context, List<AddTimeTableBeans> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.timetable,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        AddTimeTableBeans addTimeTableBeans = list.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        holder.datetime.setText("DATETIME - "+addTimeTableBeans.getDatetime());
        holder.teamName1.setText(addTimeTableBeans.getTeamName1());
        Picasso.with(context)
                .load(addTimeTableBeans.getTeamLogo1())
                .fit()
                .centerCrop()
                .into(holder.teamLogo1);
        holder.teamName2.setText(addTimeTableBeans.getTeamName2());
        Picasso.with(context)
                .load(addTimeTableBeans.getTeamLogo2())
                .fit()
                .centerCrop()
                .into(holder.teamLogo2);

        holder.result.setText("Result - "+addTimeTableBeans.getResult());
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
        public TextView datetime;
        public TextView result;
        public ImageViewHolder(View itemView) {
            super(itemView);
            teamName1 = itemView.findViewById(R.id.teamName1);
            teamName2 = itemView.findViewById(R.id.teamName2);
            teamLogo1 = itemView.findViewById(R.id.teamLogo1);
            teamLogo2 = itemView.findViewById(R.id.teamLogo2);
            datetime = itemView.findViewById(R.id.datetime);
            result = itemView.findViewById(R.id.result);
        }
    }
}
