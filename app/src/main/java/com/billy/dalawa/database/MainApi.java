package com.billy.dalawa.database;

import com.billy.dalawa.model.Events;
import com.billy.dalawa.model.Leagues;
import com.billy.dalawa.model.PlayerMedias;
import com.billy.dalawa.model.Players;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MainApi {

    String BASKETBALL = "https://rapidapi.p.rapidapi.com/sports/3/";

    String MEDIA = "https://rapidapi.p.rapidapi.com/players/";


    //BASKETBALL
    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("events")
    Call<Events> getEvent();

    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("players")
    Call<Players> getPlayers();

    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("leagues")
    Call<Leagues> getLeagues();

    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("events/live")
    Call<Events> getLive();


    //MEDIA
    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("{id}/medias")
    Call<PlayerMedias> getPMedia(@Path("id") String id);
}
