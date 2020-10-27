package com.billy.dalawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.billy.dalawa.R;
import com.billy.dalawa.model.Leagues;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {
    Context context;
    List<Leagues.Datum> leaguesList;
    Leagues.Datum leagues;
    View view;
    int trigger;
    public class LeaguesViewHolder extends RecyclerView.ViewHolder {
        TextView leagueName;
        TextView leagueId;
        ImageView leagueLogo;
        public LeaguesViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.leagues_name);
            leagueId = itemView.findViewById(R.id.leagues_id);
            leagueLogo = itemView.findViewById(R.id.leagues_logo);

        }
    }

    public LeaguesAdapter(Context context, List leaguesList, int trigger){
        this.context = context;
        this.leaguesList = leaguesList;
        this.trigger = trigger;
    }

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(trigger == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_leagues_main, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_leagues, parent, false);
        }

        return new LeaguesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        leagues = leaguesList.get(position);

        holder.leagueName.setText(leagues.getName());
        holder.leagueId.setText(String.valueOf(leagues.getId()));
        Picasso.get().load(leagues.getLogo()).into(holder.leagueLogo);

    }

    @Override
    public int getItemCount() {
        return Math.min(leaguesList.size(), 20);
    }


}
