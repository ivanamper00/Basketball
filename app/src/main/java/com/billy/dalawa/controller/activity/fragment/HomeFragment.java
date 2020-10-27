package com.billy.dalawa.controller.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.billy.dalawa.R;
import com.billy.dalawa.adapter.LeaguesAdapter;
import com.billy.dalawa.adapter.LiveMatchesAdapter;
import com.billy.dalawa.adapter.PlayersAdapter;
import com.billy.dalawa.controller.MainController;
import com.billy.dalawa.controller.activity.MainActivity;
import com.billy.dalawa.model.Events;
import com.billy.dalawa.model.Leagues;
import com.billy.dalawa.model.Players;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    MainController controller;
    RecyclerView playerRecycler;
    RecyclerView liveGamesRecycler;
    RecyclerView leaguesRecycler;
    CardView playerButton;
    CardView matchesButton;
    CardView leaguesButton;
    TextView matchesText;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_home, container, false);
        main();
        dataDisplay();
        buttonTracker();
        return view;
    }

    public void main(){
        controller = new MainController(getContext());
        playerButton = view.findViewById(R.id.dashboard_player_button);
        matchesButton = view.findViewById(R.id.dashboard_matches_button);
        leaguesButton = view.findViewById(R.id.dashboard_leagues_button);

        matchesText = view.findViewById(R.id.dashboard_matches_text);
        playerRecycler = view.findViewById(R.id.dashboard_player_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        playerRecycler.setLayoutManager(llm);

        liveGamesRecycler = view.findViewById(R.id.dashboard_mathces_recycler);
        LinearLayoutManager llm1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        liveGamesRecycler.setLayoutManager(llm1);

        leaguesRecycler = view.findViewById(R.id.dashboard_leagues_recycler);
        GridLayoutManager grid = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL, false);
        leaguesRecycler.setLayoutManager(grid);
    }

    public void buttonTracker(){

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.players);
            }
        });
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controller.retrieveLiveMatches().size() > 0){
                    MainActivity.bottomNavigationView.setSelectedItemId(R.id.live);
                }else{
                    MainActivity.bottomNavigationView.setSelectedItemId(R.id.matches);
                }
            }
        });
        leaguesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.leagues);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void dataDisplay(){
        List<Players.Datum> playerList = controller.retrievePlayers();
        List<Events.Datum> liveGames = controller.retrieveLiveMatches();
        List<Events.Datum> matches = controller.retrieveEvents();
        List<Leagues.Datum> leaguesList = controller.retrieveLeagues();

        PlayersAdapter adapter = new PlayersAdapter(getContext(), playerList, 0);
        playerRecycler.setAdapter(adapter);

        LeaguesAdapter leaguesAdapter = new LeaguesAdapter(getContext(), leaguesList, 0);
        leaguesRecycler.setAdapter(leaguesAdapter);


        LiveMatchesAdapter liveAdapter;
        if(liveGames.size() != 0){
            matchesText.setText("Ongoing Matches");
            liveAdapter = new LiveMatchesAdapter(getContext(),liveGames, 0);
        }else{
            matchesText.setText("Matches");
            liveAdapter = new LiveMatchesAdapter(getContext(),matches, 0);
        }
        liveGamesRecycler.setAdapter(liveAdapter);
    }
}