package com.marco.marplex.schoolbook.fragments.tabs;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marco.marplex.schoolbook.R;
import com.marco.marplex.schoolbook.adapters.NotificationAdapter;
import com.marco.marplex.schoolbook.fragments.custom.PagerFragment;
import com.marco.marplex.schoolbook.models.Voto;
import com.marco.marplex.schoolbook.utilities.Votes;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home extends PagerFragment{

    @Bind(R.id.listaNotifiche) RecyclerView mLastVotesList;
    @Bind(R.id.nothingHere) View nothingHere;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);

        ButterKnife.bind(this, rootView);
        mLastVotesList.setHasFixedSize(true);
        mLastVotesList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Voto> votes = Votes.getVotesByRecentDate(getContext(), 5);
        if(votes == null || votes.size() == 0){
            nothingHere.setVisibility(View.VISIBLE);
        }else{
            nothingHere.setVisibility(View.GONE);

            //Fill the list with votes recent of 5 days
            if(Votes.isThereAnyVotes(getContext())) {
                mLastVotesList.setAdapter(new NotificationAdapter(getContext(), votes));
            }
        }

        return rootView;
    }

    @Override
    public String getPageTitle() {
        return "Home";
    }
}
