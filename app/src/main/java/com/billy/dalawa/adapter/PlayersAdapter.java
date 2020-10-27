package com.billy.dalawa.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.billy.dalawa.R;
import com.billy.dalawa.database.MainApi;
import com.billy.dalawa.model.PlayerMedias;
import com.billy.dalawa.model.Players;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    Context context;
    List<Players.Datum> playerList;
    Players.Datum players;
    int noPhoto;
    int trigger;
    Dialog playerDialog;
    View view;
    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        ImageView playerPic;
        TextView playerId;
        TextView playerName;
        TextView playerJersey;
        TextView playerPosition;

        public PlayersViewHolder(@NonNull View itemView) {
            super(itemView);

            switch (trigger){
                case 0:
                    playerPic = itemView.findViewById(R.id.player_picture);
                    playerId = itemView.findViewById(R.id.player_picture_id);
                    break;
                case 1:
                    playerPic = itemView.findViewById(R.id.player_image);
                    playerId = itemView.findViewById(R.id.player_id);
                    playerName = itemView.findViewById(R.id.player_name);
                    playerJersey = itemView.findViewById(R.id.player_number);
                    playerPosition = itemView.findViewById(R.id.player_position);
                    break;
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newId = playerId.getText().toString();
                    playerDialog = new Dialog(context);
                    playerDialog.setContentView(R.layout.player_dialog_details);
                    playerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    playerDialog.show();
                    getPlayerMedias(newId);
                }
            });
        }
    }

    public PlayersAdapter(Context context,List playerList,int trigger){
        this.context = context;
        this.playerList = playerList;
        this.noPhoto = 0;
        this.trigger = trigger;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (trigger){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_pic,parent,false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list,parent,false);
                break;
        }
        return new PlayersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        players = playerList.get(position);

        switch (trigger){
            case 0:
                holder.playerId.setText(String.valueOf(players.getId()));
                if(!players.getHasPhoto()){
                    noPhoto++;
                }else{
                    Picasso.get().load(players.getPhoto()).into(holder.playerPic);
                }
                break;
            case 1:
                holder.playerId.setText(String.valueOf(players.getId()));
                holder.playerName.setText(players.getName());
                holder.playerJersey.setText("Jersey Number: "+ players.getShirtNumber());
                holder.playerPosition.setText("Position: "+nullable(players.getPosition()));
                Picasso.get().load(players.getPhoto()).into(holder.playerPic);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if(trigger == 0){
            return Math.min(playerList.size() - noPhoto, 15);
        }else{
            return playerList.size();
        }
    }

    public String nullable(String str){
        return str != null ? str : "N/A";
    }

    public void updateList(List<Players.Datum> playerList){
        this.playerList = playerList;
        notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    public void getPlayerMedias(String id){

        ImageView playerImage = playerDialog.findViewById(R.id.player_dialog_image);
        TextView playerName = playerDialog.findViewById(R.id.player_dialog_name);
        TextView playerPos = playerDialog.findViewById(R.id.player_dialog_position);
        TextView playerJersey = playerDialog.findViewById(R.id.player_dialog_number);
        ImageView close = playerDialog.findViewById(R.id.close_player_dialog);

        for(int i = 0; i < playerList.size();i++){
            if(playerList.get(i).getId().toString().equals(id)){
                Picasso.get().load(playerList.get(i).getPhoto()).into(playerImage);
                playerName.setText(playerList.get(i).getName());
                playerPos.setText("Position: "+playerList.get(i).getPosition());
                playerJersey.setText("Jersey: "+playerList.get(i).getShirtNumber().toString());

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playerDialog.dismiss();
                    }
                });
            }
        }


        final RecyclerView recyclerView = playerDialog.findViewById(R.id.player_dialog_medias);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainApi.MEDIA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MainApi api = retrofit.create(MainApi.class);

        Call<PlayerMedias> call = api.getPMedia(id);

        call.enqueue(new Callback<PlayerMedias>(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PlayerMedias> call, retrofit2.Response<PlayerMedias> response) {
                List<PlayerMedias.Datum> playersMedia= response.body().getData();

                PlayerMediasAdapter adapter = new PlayerMediasAdapter(context,playersMedia);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<PlayerMedias> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    };

}
