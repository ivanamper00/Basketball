package com.billy.dalawa.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.billy.dalawa.R;
import com.billy.dalawa.model.Events;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LiveMatchesAdapter extends RecyclerView.Adapter<LiveMatchesAdapter.LiveMatchesViewHolder> {
    Context context;
    List<Events.Datum> liveList;
    Events.Datum live;
    int trigger;
    View view;
    Integer winnerCode;
    Dialog matchesDialog;
    public class LiveMatchesViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeamName;
        TextView homeTeamScore;
        ImageView homeTeamLogo;
        TextView awayTeamName;
        TextView awayTeamScore;
        ImageView awayTeamLogo;
        TextView matchId;
        TextView scores;
        public LiveMatchesViewHolder(@NonNull View itemView) {
            super(itemView);

            if(trigger == 0){
                dashboard(itemView);
            }else{
                fragmentData(itemView);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newId = matchId.getText().toString();
                    matchesDialog = new Dialog(context);
                    matchesDialog.setContentView(R.layout.event_details_dialog);
                    matchesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    matchesDialog.show();
                    displayDialog(newId);
                }
            });

        }

        public void dashboard(View itemView){
            homeTeamName = itemView.findViewById(R.id.dashboard_matches_home_team_name);
            homeTeamScore = itemView.findViewById(R.id.dashboard_matches_home_team_score);
            homeTeamLogo = itemView.findViewById(R.id.dashboard_matches_home_team_logo);
            awayTeamName = itemView.findViewById(R.id.dashboard_matches_away_team_name);
            awayTeamScore = itemView.findViewById(R.id.dashboard_matches_away_team_score);
            awayTeamLogo = itemView.findViewById(R.id.dashboard_matches_away_team_logo);
            matchId = itemView.findViewById(R.id.dashboard_matches_id);
        }

        public void fragmentData(View itemView){
            homeTeamName = itemView.findViewById(R.id.event_team_home_name);
            homeTeamLogo = itemView.findViewById(R.id.event_team_home_logo);
            awayTeamName = itemView.findViewById(R.id.event_team_away_name);
            awayTeamLogo = itemView.findViewById(R.id.event_team_away_logo);
            scores  = itemView.findViewById(R.id.event_team_scores);
            matchId = itemView.findViewById(R.id.event_match_id);


        }
    }

    public LiveMatchesAdapter(Context context, List liveList, int trigger){
        this.context = context;
        this.liveList = liveList;
        this.trigger = trigger;
    }

    @NonNull
    @Override
    public LiveMatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(trigger == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_matches,parent,false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list,parent,false);
        }

        return new LiveMatchesViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LiveMatchesViewHolder holder, int position) {
        live = liveList.get(position);

        switch (trigger){
            case 0:
                holder.matchId.setText(String.valueOf(live.getId()));
                holder.homeTeamName.setText(removeParenthesis(live.getHomeTeam().getName()));
                holder.homeTeamScore.setText(live.getHomeScore().getDisplay());
                Picasso.get().load(live.getHomeTeam().getLogo()).into(holder.homeTeamLogo);
                holder.awayTeamName.setText(removeParenthesis(live.getAwayTeam().getName()));
                holder.awayTeamScore.setText(live.getAwayScore().getDisplay());
                Picasso.get().load(live.getAwayTeam().getLogo()).into(holder.awayTeamLogo);
                break;
            case 1:
                holder.matchId.setText(String.valueOf(live.getId()));
                holder.scores.setText(live.getHomeScore().getDisplay() + " - " + live.getAwayScore().getDisplay());
                holder.homeTeamName.setText(removeParenthesis(live.getHomeTeam().getName()));
                Picasso.get().load(live.getHomeTeam().getLogo()).into(holder.homeTeamLogo);
                holder.awayTeamName.setText(removeParenthesis(live.getAwayTeam().getName()));
                Picasso.get().load(live.getAwayTeam().getLogo()).into(holder.awayTeamLogo);

                break;
        }
    }
    @Override
    public int getItemCount() {
        if(trigger == 0){
            return Math.min(liveList.size(), 5);
        }else{
            return liveList.size();
        }
    }

    public Integer getWinnerCode() {
        return winnerCode;
    }

    public void setWinnerCode(Integer winnerCode) {
        this.winnerCode = winnerCode;
    }

    public String removeParenthesis(String string){
        return string.contains("(") ? string.substring(0,string.indexOf("(")) : string;
    }

    public void displayDialog(String id){

                TextView homeTeamName = matchesDialog.findViewById(R.id.dialog_event_team_home_name);
                ImageView homeTeamLogo = matchesDialog.findViewById(R.id.dialog_event_team_home_logo);
                TextView awayTeamName = matchesDialog.findViewById(R.id.dialog_event_team_away_name);
                ImageView awayTeamLogo = matchesDialog.findViewById(R.id.dialog_event_team_away_logo);
                TextView scores = matchesDialog.findViewById(R.id.dialog_event_team_scores);
                TextView homeFirst = matchesDialog.findViewById(R.id.dialog_event_home_first);
                TextView homeSecond = matchesDialog.findViewById(R.id.dialog_event_home_second);
                TextView homeThird = matchesDialog.findViewById(R.id.dialog_event_home_third);
                TextView homeFourth = matchesDialog.findViewById(R.id.dialog_event_home_fourth);
                TextView awayFirst = matchesDialog.findViewById(R.id.dialog_event_away_first);
                TextView awaySecond = matchesDialog.findViewById(R.id.dialog_event_away_second);
                TextView awayThird = matchesDialog.findViewById(R.id.dialog_event_away_third);
                TextView awayFourth = matchesDialog.findViewById(R.id.dialog_event_away_fourth);
                CardView close =  matchesDialog.findViewById(R.id.dialog_event_close);

                TextView homeFt = matchesDialog.findViewById(R.id.dialog_event_home_ft);
                TextView homeFg = matchesDialog.findViewById(R.id.dialog_event_home_fg);
                TextView home2pt = matchesDialog.findViewById(R.id.dialog_event_home_two);
                TextView home3pt = matchesDialog.findViewById(R.id.dialog_event_home_three);
                TextView homeFouls = matchesDialog.findViewById(R.id.dialog_event_home_fouls);
                TextView awayFt = matchesDialog.findViewById(R.id.dialog_event_away_ft);
                TextView awayFg = matchesDialog.findViewById(R.id.dialog_event_away_fg);
                TextView away2pt = matchesDialog.findViewById(R.id.dialog_event_away_two);
                TextView away3pt = matchesDialog.findViewById(R.id.dialog_event_away_three);
                TextView awayFouls = matchesDialog.findViewById(R.id.dialog_event_away_fouls);



                for(int i = 0; i < liveList.size(); i++){
                    if(liveList.get(i).getId().equals(id)){
                        Events.Datum lives = liveList.get(i);
                        Log.d("asdasdasd", id);
                        Log.d("asdasdasd", lives.getId());
                        scores.setText(lives.getHomeScore().getDisplay() + " - " + lives.getAwayScore().getDisplay());
                        homeTeamName.setText(removeParenthesis(lives.getHomeTeam().getName()));
                        Picasso.get().load(lives.getHomeTeam().getLogo()).into(homeTeamLogo);
                        awayTeamName.setText(removeParenthesis(lives.getAwayTeam().getName()));
                        Picasso.get().load(lives.getAwayTeam().getLogo()).into(awayTeamLogo);
                        homeFirst.setText(nullable(lives.getHomeScore().getPeriod1()));
                        homeSecond.setText(nullable(lives.getHomeScore().getPeriod2()));
                        homeThird.setText(nullable(lives.getHomeScore().getPeriod3()));
                        homeFourth.setText(nullable(lives.getHomeScore().getPeriod4()));
                        awayFirst.setText(nullable(lives.getAwayScore().getPeriod1()));
                        awaySecond.setText(nullable(lives.getAwayScore().getPeriod2()));
                        awayThird.setText(nullable(lives.getAwayScore().getPeriod3()));
                        awayFourth.setText(nullable(lives.getAwayScore().getPeriod4()));

                        if(lives.getMain_stat() != null){
                            if (lives.getMain_stat().getFreeThrows() != null) {
                                homeFt.setText(nullable(lives.getMain_stat().getFreeThrows().getHome()));
                                awayFt.setText(nullable(lives.getMain_stat().getFreeThrows().getAway()));
                            }
                            if (lives.getMain_stat().getFieldGoals() != null) {
                                homeFg.setText(nullable(lives.getMain_stat().getFieldGoals().getHome()));
                                awayFg.setText(nullable(lives.getMain_stat().getFieldGoals().getAway()));
                            }
                            if (lives.getMain_stat().get_2Pointers() != null) {
                                home2pt.setText(nullable(lives.getMain_stat().get_2Pointers().getHome()));
                                away2pt.setText(nullable(lives.getMain_stat().get_2Pointers().getAway()));
                            }
                            if (lives.getMain_stat().get_3Pointers() != null) {
                                home3pt.setText(nullable(lives.getMain_stat().get_3Pointers().getHome()));
                                away3pt.setText(nullable(lives.getMain_stat().get_3Pointers().getAway()));
                            }
                            if (lives.getMain_stat().getFouls() != null) {
                                homeFouls.setText(nullable(lives.getMain_stat().getFouls().getHome()));
                                awayFouls.setText(nullable(lives.getMain_stat().getFouls().getAway()));
                            }
                        }

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                matchesDialog.dismiss();
                            }
                        });
                    }
                }
    }

    public String nullable(String string){
        return string != null ? string : "-";
    }

    public String nullable(Integer string){
        return string == null ? "-" :  String.valueOf(string);
    }
}
