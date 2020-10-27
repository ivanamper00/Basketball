package com.billy.dalawa.controller.activity.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.billy.dalawa.R;
import com.billy.dalawa.adapter.LiveMatchesAdapter;
import com.billy.dalawa.controller.MainController;
import com.billy.dalawa.model.Events;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LivesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LivesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LivesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LivesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LivesFragment newInstance(String param1, String param2) {
        LivesFragment fragment = new LivesFragment();
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
    LinearLayoutManager llm;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lives, container, false);
        main();
        dataDisplay();
        return view;

    }

    public void main(){
        controller = new MainController(getContext());
        recyclerView = view.findViewById(R.id.lives_recycler);
        llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
    }
    public void dataDisplay(){
        List<Events.Datum> liveGames = controller.retrieveLiveMatches();
        if(liveGames.size() != 0){
            LiveMatchesAdapter adapter = new LiveMatchesAdapter(getContext(),liveGames, 1);
            recyclerView.setAdapter(adapter);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
//                     int asd = llm.
                }
            });
        }

    }

}