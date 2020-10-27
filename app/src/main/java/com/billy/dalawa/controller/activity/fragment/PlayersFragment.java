package com.billy.dalawa.controller.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.billy.dalawa.R;
import com.billy.dalawa.adapter.PlayersAdapter;
import com.billy.dalawa.controller.MainController;
import com.billy.dalawa.model.Players;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersFragment newInstance(String param1, String param2) {
        PlayersFragment fragment = new PlayersFragment();
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
    RecyclerView recyclerView;
    EditText search;
    List<Players.Datum> playersList;
    View view;
    ImageView closeBtn;
    PlayersAdapter adapter;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchQuery(s.toString());
            if(s.length() > 0){
                closeBtn.setVisibility(View.VISIBLE);
            }else{
                closeBtn.setVisibility(View.GONE);
            }
        }
    };

    private View.OnClickListener searchClose = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeBtn.setVisibility(View.GONE);
            searchQuery("");
            search.setText("");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_players, container, false);
        main();
        dataDisplay();
        return view;
    }

    public void main(){
        controller = new MainController(getContext());
        recyclerView = view.findViewById(R.id.players_recycler);
        search = view.findViewById(R.id.search_players);
        search.addTextChangedListener(textWatcher);
        closeBtn = view.findViewById(R.id.close_search);
        closeBtn.setVisibility(View.GONE);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
    }

    public void dataDisplay(){
        playersList = controller.retrievePlayers();
        adapter = new PlayersAdapter(getContext(),playersList,1);
        recyclerView.setAdapter(adapter);
    }

    public void searchQuery(String query){
        List<Players.Datum> players = new ArrayList<>();
        for(Players.Datum result : playersList){
            if(result.getName().toLowerCase().contains(query.toLowerCase())){
                players.add(result);
            }
        }
        adapter.updateList(players);
    }
}