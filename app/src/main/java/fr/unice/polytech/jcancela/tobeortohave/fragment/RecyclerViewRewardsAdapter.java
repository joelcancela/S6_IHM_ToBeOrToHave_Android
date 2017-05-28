package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.model.Reward;

/**
 * Created by Joel CANCELA VAZ on 28/05/2017.
 */

public class RecyclerViewRewardsAdapter extends RecyclerView.Adapter<RecyclerViewRewardsAdapter.RewardsHolder> {

    private final int currentPoints;
    List<Reward> rewardList;

    public RecyclerViewRewardsAdapter(List<Reward> rewardList, int currentPoints) {
        this.rewardList = rewardList;
        this.currentPoints = currentPoints;
    }

    @Override
    public RecyclerViewRewardsAdapter.RewardsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RewardsHolder viewHolder = null;
        CardView layoutView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_reward_item, parent, false);
        viewHolder = new RewardsHolder(layoutView, rewardList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewRewardsAdapter.RewardsHolder holder, int position) {
        //CARDVIEW
        TextView reward_name = (TextView) holder.cardview.findViewById(R.id.reward_name);
        TextView reward_description = (TextView) holder.cardview.findViewById(R.id.reward_description);
        TextView reward_points = (TextView) holder.cardview.findViewById(R.id.points_to_reach);
        LinearLayout locked = (LinearLayout) holder.cardview.findViewById(R.id.locked);

        Reward reward = this.rewardList.get(position);
        System.out.println(rewardList);
        reward_name.setText(reward.getName());
        reward_description.setText(reward.getDescription());
        reward_points.setText(reward.getPointsToReach()+" points");
        if(currentPoints>=reward.getPointsToReach()){
            locked.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return rewardList.size();
    }

    public class RewardsHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public RewardsHolder(CardView itemView, List<Reward> rewardList) {
            super(itemView);
            cardview=itemView;
        }
    }
}
