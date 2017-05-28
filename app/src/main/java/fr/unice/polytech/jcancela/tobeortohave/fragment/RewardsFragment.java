package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.adapter.RecyclerViewRewardsAdapter;
import fr.unice.polytech.jcancela.tobeortohave.database.RewardsTask;
import fr.unice.polytech.jcancela.tobeortohave.model.Reward;

/**
 * Created by Joel CANCELA VAZ on 22/05/2017.
 */

public class RewardsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
        int userPoints = sharedPreferences.getInt("user_points",0);

        TextView points = (TextView) view.findViewById(R.id.points);
        points.setText(String.valueOf(userPoints)+" points");

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rewards_view);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        List<Reward> rewardsList = new ArrayList();

        try {
            rewardsList = new RewardsTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final RecyclerViewRewardsAdapter recyclerViewRewardsAdapter = new RecyclerViewRewardsAdapter(rewardsList,userPoints);
        recyclerView.setAdapter(recyclerViewRewardsAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
