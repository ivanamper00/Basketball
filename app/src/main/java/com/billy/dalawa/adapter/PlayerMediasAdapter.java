package com.billy.dalawa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.billy.dalawa.R;
import com.billy.dalawa.model.PlayerMedias;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerMediasAdapter extends RecyclerView.Adapter<PlayerMediasAdapter.PlayerMediasViewHolder> {

    Context context;
    List<PlayerMedias.Datum> playerMediaList;
    PlayerMedias.Datum playerMedias;
    public class PlayerMediasViewHolder extends RecyclerView.ViewHolder {
        WebView vids;
        TextView title;
        TextView subTitle;
        TextView source;
        WebSettings webSettings;
        @SuppressLint("SetJavaScriptEnabled")
        public PlayerMediasViewHolder(@NonNull View itemView) {
            super(itemView);
            vids = itemView.findViewById(R.id.player_media_video);
            title = itemView.findViewById(R.id.player_media_title);
            subTitle = itemView.findViewById(R.id.player_media_sub_title);
            source = itemView.findViewById(R.id.player_media_source);
            webSettings = vids.getSettings();
            webSettings.setJavaScriptEnabled(true);
            vids.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            vids.getSettings().setLoadWithOverviewMode(true);
            vids.getSettings().setUseWideViewPort(true);
        }
    }

    public PlayerMediasAdapter(Context context, List<PlayerMedias.Datum> playerMediaList){
        this.context = context;
        this.playerMediaList = playerMediaList;
    }

    @NonNull
    @Override
    public PlayerMediasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_media_list,parent,false);
        return new PlayerMediasViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlayerMediasViewHolder holder, int position) {
        playerMedias = playerMediaList.get(position);

        holder.title.setText(playerMedias.getTitle());
        holder.subTitle.setText(playerMedias.getSubTitle());
        if(playerMedias.getUrl() != null){

            String embeded = playerMedias.getSourceUrl().replace("watch?v=", "embed/");
//            String embed2 = playerMedias.getSourceUrl().replace("https://", "");
            holder.source.setText("youtube.com");
            holder.vids.loadUrl(embeded);
        }

    }

    @Override
    public int getItemCount() {
        return playerMediaList.size();
    }

}
